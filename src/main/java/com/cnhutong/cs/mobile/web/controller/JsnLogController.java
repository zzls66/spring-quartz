/**
 * 
 */
package com.cnhutong.cs.mobile.web.controller;

import com.cnhutong.cs.mobile.web.interceptor.HttpAuthentication;
import com.cnhutong.cs.mobile.web.request.JsnLog;
import com.cnhutong.cs.mobile.web.util.DateFormatUtils;
import com.cnhutong.cs.mobile.web.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 前端错误日志
 * @author Wilson
 *
 */
@Controller
@RequestMapping("")
public class JsnLogController {

    private static final Logger logger = LoggerFactory.getLogger(JsnLogController.class);

    /**
     * 前端日志收集
     * 
     * @param jsnLog
     * @return
     */
    @RequestMapping(value = "/log-frontend", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @HttpAuthentication(ignore = true)
	public @ResponseBody
	Result logFrontend(@RequestBody JsnLog jsnLog) {

    	for(JsnLog.Log log : jsnLog.getLogs()) {
    		
    		if(log.isErrorLevel()) {
    			logger.error("JSNLOG - level[{}], time[{}], message[{}], name[{}], eventId[{}], reuqestId[{}]", 
    					log.getLevel(), DateFormatUtils.format(new Date(log.getTimestamp()), "yyyy-MM-dd HH:mm:ss"), log.getMessage(), log.getName(), log.getEventId(), jsnLog.getRequestId());
    		} else {
    			logger.info("JSNLOG - level[{}], time[{}], message[{}], name[{}], eventId[{}], reuqestId[{}]", 
    					log.getLevel(), DateFormatUtils.format(new Date(log.getTimestamp()), "yyyy-MM-dd HH:mm:ss"), log.getMessage(), log.getName(), log.getEventId(), jsnLog.getRequestId());
    		}
    	}

		return new Result().success();
	}

}
