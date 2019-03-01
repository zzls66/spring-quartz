/**
 *
 */
package com.cnhutong.cs.mobile.web.controller;


import com.cnhutong.cs.mobile.web.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

/**
 * @author Wilson
 */
@ControllerAdvice
public class GlobalExceptionAdviceController {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdviceController.class);

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public String handleBusinessException(BusinessException exception) {
        HttpResult result = new HttpResult();
        result.fail(exception.getCode()).message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthorizationException(AuthorizationException exception) {
        HttpResult result = new HttpResult();
        result.fail(exception.getCode()).message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        HttpResult result = new HttpResult();
        logger.error("handleHttpMessageNotReadableException exception{}", exception);
        result.fail("400").message("HTTP_MESSAGE_NOT_READABLE");
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        HttpResult result = new HttpResult();
        result.fail("400").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        HttpResult result = new HttpResult();

        // Always get the first error's default message
        BindingResult bindingResult = exception.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().get(0);

        String message = "";
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            message = "Field error on field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage();
        } else {
            message = error.getDefaultMessage();
        }

        result.fail("400").message(message);
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBindException(BindException exception) {
        HttpResult result = new HttpResult();

        // Always get the first error's default message
        BindingResult bindingResult = exception.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().get(0);

        String message = "";
        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            message = "Field error on field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage();
        } else {
            message = error.getDefaultMessage();
        }

        result.fail("400").message(message);
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingRequestBodyParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingRequestBodyParameterException(MissingRequestBodyParameterException exception) {
        HttpResult result = new HttpResult();
        result.fail("400").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchRequestHandlingMethodException(NoSuchRequestHandlingMethodException exception) {
        HttpResult result = new HttpResult();
        result.fail("404").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoHandlerFoundException(NoHandlerFoundException exception) {
        HttpResult result = new HttpResult();
        result.fail("404").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpResult result = new HttpResult();
        result.fail("405").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception) {
        HttpResult result = new HttpResult();
        result.fail("406").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public String handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        HttpResult result = new HttpResult();
        result.fail("415").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConversionNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleConversionNotSupportedException(ConversionNotSupportedException exception) {
        HttpResult result = new HttpResult();
        result.fail("500").message(exception.getMessage());
        return JsonUtils.toJsonString(result);
    }

    /**
     * @param exception
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception exception) {
    	if (exception.getClass().getName().endsWith("ClientAbortException")) {
    		logger.warn("ClientAbortException: ", exception);
		}else {
			logger.error("Unhandled Exception: ", exception);
		}
        HttpResult result = new HttpResult();
        result.fail("500").message("服务器未知异常");
        return JsonUtils.toJsonString(result);
    }

}
