package com.zzl.exception;

/**
 *
 * @author Downpour
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2931549392530749973L;

    private String code;

    private String targetURL;

    /**
     *
     */
    public BusinessException() {
        super();
    }

    /**
     *
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * The full constructor
     *
     * @param message
     * @param targetURL
     */
    public BusinessException(String message, String targetURL) {
        super(message);
        this.targetURL = targetURL;
    }

    /**
     * Return a new business exception having specified code
     * @param code
     * @return
     */
    public static BusinessException codeOf(String code) {
        BusinessException businessException = new BusinessException();
        businessException.setCode(code);
        return businessException;
    }

    /**
     * Return a new business exception having specified code and message
     * @param code
     * @param message
     * @return
     */
    public static BusinessException codeAndMessageOf(String code, String message) {
        BusinessException businessException = new BusinessException(message);
        businessException.setCode(code);
        return businessException;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the targetURL
     */
    public String getTargetURL() {
        return targetURL;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
