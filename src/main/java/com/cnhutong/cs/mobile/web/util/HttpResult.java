/**
 *
 */
package com.cnhutong.cs.mobile.web.util;

import com.alibaba.fastjson.annotation.JSONField;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author lute
 */
public class HttpResult extends Result {

    @JSONField(serialize = false)
    private int status;

    @JSONField(serialize = false)
    private String responseText;

    @JSONField(serialize = false)
    private String targetView;

    /**
     *
     */
    public HttpResult() {
        super();
    }

    /**
     * @param code
     */
    public HttpResult(String code) {
        super(code);
    }

    /* (non-Javadoc)
     * @see com.demo2do.core.support.Result#initialize(java.lang.String, java.lang.String)
     */
    @Override
    public HttpResult initialize(String code, String message) {
        super.initialize(code, message);
        return this;
    }

    /**
     * Initialize with code, status and message
     *
     * @param code
     * @param status
     * @param message
     * @return
     */
    public HttpResult initialize(String code, int status, String message) {
        this.status = status;
        return initialize(code, message);
    }

    /**
     * @param code
     * @param status
     */
    public HttpResult(String code, int status) {
        this(code);
        this.status = status;
    }

    /* (non-Javadoc)
     * @see com.demo2do.core.support.Result#success()
     */
    @Override
    public HttpResult success() {
        super.success();
        this.status = HttpServletResponse.SC_OK;
        return this;
    }

    /* (non-Javadoc)
     * @see com.demo2do.core.support.Result#fail()
     */
    @Override
    public HttpResult fail() {
        return fail("1");
    }

    /* (non-Javadoc)
     * @see com.demo2do.core.support.Result#fail(java.lang.String)
     */
    @Override
    public HttpResult fail(String code) {
        super.fail(code);
        this.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        return this;
    }

    /* (non-Javadoc)
     * @see com.demo2do.core.support.Result#message(java.lang.String)
     */
    @Override
    public HttpResult message(String message) {
        super.message(message);
        return this;
    }

    /* (non-Javadoc)
     * @see com.demo2do.core.support.Result#data(java.lang.String, java.lang.Object)
     */
    @Override
    public HttpResult data(String key, Object value) {
        super.data(key, value);
        return this;
    }

    /* (non-Javadoc)
     * @see com.demo2do.core.support.Result#data(java.util.Map)
     */
    @Override
    public HttpResult data(Map<String, ? extends Object> data) {
        super.data(data);
        return this;
    }

    /**
     * Mark with http status code
     *
     * @param status
     * @return
     */
    public HttpResult status(int status) {
        this.status = status;
        return this;
    }

    /**
     * Add response text for result
     *
     * @param responseText
     * @return
     */
    public HttpResult responseText(String responseText) {
        this.responseText = responseText;
        return this;
    }

    /**
     * Set target view of this result
     *
     * @param view
     * @return
     */
    public HttpResult targetView(String view) {
        this.targetView = view;
        return this;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the responseText
     */
    public String getResponseText() {
        return responseText;
    }

    /**
     * @return the targetView
     */
    public String getTargetView() {
        return targetView;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param responseText the responseText to set
     */
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

}
