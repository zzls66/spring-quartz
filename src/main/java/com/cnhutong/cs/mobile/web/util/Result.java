/**
 *
 */
package com.cnhutong.cs.mobile.web.util;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Downpour
 */
public class Result implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4279335105299306124L;

	private String code;

    private String message;

    private Map<String, Object> data;

    /**
     * The default constructor
     */
    public Result() {
        this.code = "1";
        this.data = new HashMap<String, Object>();
    }

    /**
     *
     * @param code
     */
    public Result(String code) {
        this.code = code;
        this.data = new HashMap<String, Object>();
    }

    /**
     * Initialize with code and message
     *
     * @param code
     * @param message
     * @return
     */
    public Result initialize(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    /**
     * 
     * @Title: initialize 
     * @Description: initialize with array of code and message 
     * @param codeAndMessage
     * @return
     */
    public Result initialize(String[] codeAndMessage) {
    	if(codeAndMessage != null && codeAndMessage.length > 1) {
    		this.code = codeAndMessage[0];
    		this.message = codeAndMessage[1];
    		return this;
    	} else {
    		throw new RuntimeException("Size of codeAndMessage array is not correct");
    	}
    }

    /**
     * Mark with success flag and status code
     *
     * @return
     */
    public Result success() {
        this.code = "0";
        return this;
    }

    /**
     * Mark with fail flag and status code
     *
     * @return
     */
    public Result fail() {
        this.code = "1";
        return this;
    }

    /**
     *
     * @param code
     * @return
     */
    public Result fail(String code) {
        this.code = code;
        return this;
    }

    /**
     * Add message for result
     *
     * @param message
     * @return
     */
    public Result message(String message) {
        this.message = message;
        return this;
    }

    /**
     * Add data for result
     *
     * @param key
     * @param value
     * @return
     */
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    /**
     * Add data for result
     *
     * @param data
     * @return
     */
    public Result data(Map<String, ? extends Object> data) {
        this.data.putAll(data);
        return this;
    }

    /**
     * Determine whether the status is valid
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isValid() {
        return StringUtils.equals(this.code, "0");
    }

    /**
     * Get data according to key
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return this.data.get(key);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
