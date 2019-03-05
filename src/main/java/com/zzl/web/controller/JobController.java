package com.zzl.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.zzl.domain.SysJob;
import com.zzl.dto.Result;
import com.zzl.exception.BizException;
import com.zzl.service.ISysJobService;
import com.zzl.util.Constant;
import com.zzl.util.LayuiData;
import com.zzl.util.SchedulerUtil;
import com.zzl.web.request.JobRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller()
@RequestMapping("/job")
public class JobController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISysJobService sysJobService;


	/**
	 * 打开详情界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toDetail")
	@ResponseBody
	public SysJob toDetail(Integer id) {
		SysJob job = sysJobService.selectByPrimaryKey(id);
		return job;
	}


	/**
	 * 查询任务列表（带分页）
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.GET)
	@ResponseBody
	public Result queryJobList(HttpServletRequest request, HttpServletResponse response) {
		String idStr = request.getParameter("id");
		String jobName = request.getParameter("jobName");
		String jobGroup = request.getParameter("jobGroup");
		String jobCron = request.getParameter("jobCron");
		String jobClassPath = request.getParameter("jobClassPath");
		String jobDescribe = request.getParameter("jobDescribe");

		HashMap<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(idStr)) {
			map.put("id", idStr);
		}
		if (StringUtils.isNotBlank(jobName)) {
			map.put("jobName", jobName);
		}
		if (StringUtils.isNotBlank(jobGroup)) {
			map.put("jobGroup", jobGroup);
		}
		if (StringUtils.isNotBlank(jobCron)) {
			map.put("jobCron", jobCron);
		}
		if (StringUtils.isNotBlank(jobClassPath)) {
			map.put("jobClassPath", jobClassPath);
		}
		if (StringUtils.isNotBlank(jobDescribe)) {
			map.put("jobDescribe", jobDescribe);
		}

//		int page = Integer.parseInt(request.getParameter("page"));
//		int limit = Integer.parseInt(request.getParameter("limit"));
//		if(page>=1){
//			page = (page-1)*limit;
//		}
		Result result = new Result();
		try {
			List<SysJob> jobList = sysJobService.querySysJobList(map);
			int count = sysJobService.getJobCount();
			return result.success().data("count",count).data("",jobList);
		} catch (Exception e) {
			throw new BizException("查询任务列表异常：" + e.getMessage());
		}
	}

	/**
	 * 添加定时任务
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/addJob", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public int addjob(@RequestBody JobRequest jobRequest) throws Exception {
        logger.info("添加任务开始... ...");
		int num =0;

		
		if (StringUtils.isBlank(jobRequest.getJobName())) {
			throw new BizException("任务名称不能为空");
		}
		if (StringUtils.isBlank(jobRequest.getJobGroup())) {
			throw new BizException("任务组别不能为空");
		}
		if (StringUtils.isBlank(jobRequest.getJobCron())) {
			throw new BizException("Cron表达式不能为空");
		}
		if (StringUtils.isBlank(jobRequest.getJobClassPath())) {
			throw new BizException("任务类路径不能为空");
		}
		
		// 参数不为空时校验格式
		if(StringUtils.isNotBlank(jobRequest.getJobDataMap())){
			try {
				JSONObject.parseObject(jobRequest.getJobDataMap());
			} catch (Exception e) {
				throw new BizException("参数JSON格式错误");
			}
		}
		
		// 已存在校验
		SysJob queryBean = new SysJob();
		queryBean.setJobName(jobRequest.getJobName());
		SysJob result = sysJobService.selectByBean(queryBean);
		if (null != result) {
			throw new BizException("任务名为" + jobRequest.getJobName() + "的任务已存在！");
		}

		SysJob bean = new SysJob();
		bean.setJobName(jobRequest.getJobName());
		bean.setJobClassPath(jobRequest.getJobClassPath());
		bean.setJobGroup(jobRequest.getJobGroup());
		bean.setJobCron(jobRequest.getJobCron());
		bean.setJobDescribe(jobRequest.getJobDescribe());
		bean.setJobDataMap(jobRequest.getJobDataMap());
		bean.setJobStatus(Constant.JOB_STATE.YES);
		try {
			num = sysJobService.insertSelective(bean);
		} catch (Exception e) {
			throw new BizException("新增定时任务失败");
		}
		
		SchedulerUtil.addJob(jobRequest.getJobClassPath(),jobRequest.getJobName(), jobRequest.getJobGroup(), jobRequest.getJobCron(),jobRequest.getJobDataMap());
		return num;
	}
	
	/**
	 * 变更定时任务执行状态
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/changeState")
	@ResponseBody
	public int changeState(@RequestParam(value = "id") String idStr)throws Exception{
        logger.info("变更定时任务状态开始... ...");
		if (StringUtils.isBlank(idStr)) {
			throw new BizException("任务ID不能为空");
		}
		int id = Integer.parseInt(idStr);

		// 校验
		SysJob queryBean = new SysJob();
		queryBean.setId(id);
		SysJob result = sysJobService.selectByBean(queryBean);
		if (null == result) {
			throw new BizException("任务ID为" + id + "的任务不存在！");
		}
		
		SysJob updateBean = new SysJob();
		updateBean.setId(id);
		//如果是现在是启用，则停用
		if(Constant.JOB_STATE.YES == result.getJobStatus()){
			updateBean.setJobStatus(Constant.JOB_STATE.NO);
			//SchedulerUtil.jobPause(result.getJobName(), result.getJobGroup());
		  Boolean b=SchedulerUtil.isResume(result.getJobName(), result.getJobGroup());
		  if (b) {
			  SchedulerUtil.jobdelete(result.getJobName(), result.getJobGroup());
		  }
		}
		
		//如果现在是停用，则启用
		if(Constant.JOB_STATE.NO == result.getJobStatus()){
			updateBean.setJobStatus(Constant.JOB_STATE.YES);
			//SchedulerUtil.jobresume(result.getJobName(), result.getJobGroup());
			 Boolean b=SchedulerUtil.isResume(result.getJobName(), result.getJobGroup());
			 //存在则激活，不存在则添加
			  if (b) {
				  SchedulerUtil.jobresume(result.getJobName(), result.getJobGroup());
			  }else {
				  SchedulerUtil.addJob(result.getJobClassPath(),result.getJobName(), result.getJobGroup(), result.getJobCron(),result.getJobDataMap());
			}
		}
		
		try {
			sysJobService.updateByPrimaryKeySelective(updateBean);
		} catch (Exception e) {
			throw new BizException("更新数据库的定时任务信息异常！");
		}
		// 1表示成功
		return 1;
		
	}

	/**
	 * 删除一个任务
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteJob")
	@ResponseBody
	public int deletejob(@RequestParam(value = "id") String idStr) throws Exception {
        logger.info("删除定时任务状态开始... ...");
		int num =0;
		if (StringUtils.isBlank(idStr)) {
			throw new BizException("任务ID不能为空");
		}
		int id = Integer.parseInt(idStr);
		
		// 存在性校验
		SysJob queryBean = new SysJob();
		queryBean.setId(id);
		SysJob result = sysJobService.selectByBean(queryBean);
		if (null == result) {
			throw new BizException("任务ID为" + idStr + "的任务不存在！");
		}
		
		try {
			num = sysJobService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new BizException("从数据库删除定时任务时发生异常！");
		}
		
		SchedulerUtil.jobdelete(result.getJobName(), result.getJobGroup());
		return num;
	}

	/**
	 * 修改定时表达式
	 */
	@RequestMapping("/reSchedulejob")
	@ResponseBody
	public int updateByBean(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("修改定时任务信息开始... ...");
		int num =0;
		String jobCron = request.getParameter("jobCron");
		String jobDescribe = request.getParameter("jobDescribe");
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		// 数据非空校验
		if (!StringUtils.isNotBlank(idStr)) {
			throw new BizException("任务ID不能为空");
		}
		SysJob result = sysJobService.selectByPrimaryKey(id);
		// 数据不存在
		if (null == result) {
			throw new BizException("根据任务ID[" + id + "]未查到相应的任务记录");
		}
		SysJob bean = new SysJob();
		bean.setId(id);
		bean.setJobCron(jobCron);
		bean.setJobDescribe(jobDescribe);
		try {
			num = sysJobService.updateByPrimaryKeySelective(bean);
		} catch (Exception e) {
			throw new BizException("变更任务表达式异常：" + e.getMessage());
		}
		//只有任务状态为启用，才开始运行
		// 如果先启动再手工插入数据，此处会报空指针异常
		if( result.getJobStatus() ==Constant.JOB_STATE.YES ){
			SchedulerUtil.jobReschedule(result.getJobName(), result.getJobGroup(),jobCron);
		}
		
		// 返回成功
		return num;
	}

}
