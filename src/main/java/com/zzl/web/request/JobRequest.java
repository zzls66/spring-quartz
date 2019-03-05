package com.zzl.web.request;

/**
 * @ Author     ：zzl.
 * @ Date       ：Created in 16:40 2019/3/5
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public class JobRequest {

    private String jobName ;
    private String jobClassPath;
    private String jobGroup ;
    private String jobCron ;
    private String jobDescribe ;
    private String jobDataMap;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobClassPath() {
        return jobClassPath;
    }

    public void setJobClassPath(String jobClassPath) {
        this.jobClassPath = jobClassPath;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobCron() {
        return jobCron;
    }

    public void setJobCron(String jobCron) {
        this.jobCron = jobCron;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public String getJobDataMap() {
        return jobDataMap;
    }

    public void setJobDataMap(String jobDataMap) {
        this.jobDataMap = jobDataMap;
    }
}
