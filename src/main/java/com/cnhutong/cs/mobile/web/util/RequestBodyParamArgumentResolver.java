/**
 *
 */
package com.cnhutong.cs.mobile.web.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author lute
 */
public class RequestBodyParamArgumentResolver extends AbstractMessageConverterMethodArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(RequestBodyParamArgumentResolver.class);

    private static final String REQUEST_BODY_ATTRIBUTE = "DEMO2DO_REQUEST_BODY";

    private ConversionService conversionService;

    /**
     * Basic constructor with converters only.
     */
    public RequestBodyParamArgumentResolver(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }

    /**
     * Constructor with converters and {@code Request~} and {@code ResponseBodyAdvice}.
     */
    public RequestBodyParamArgumentResolver(List<HttpMessageConverter<?>> messageConverters, List<Object> requestResponseBodyAdvice) {
        super(messageConverters, requestResponseBodyAdvice);
    }

    /**
     * @param messageConverters
     * @param conversionService
     */
    public RequestBodyParamArgumentResolver(List<HttpMessageConverter<?>> messageConverters, ConversionService conversionService) {
        super(messageConverters);
        this.conversionService = conversionService;
    }

    /**
     * @param conversionService the conversionService to set
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
     */
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBodyParam.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     */
    @SuppressWarnings("unchecked")
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        RequestBodyParam requestBodyParam = parameter.getParameterAnnotation(RequestBodyParam.class);
        String parameterName = requestBodyParam.value();
        Class<?> bodyType = requestBodyParam.bodyType();
        boolean required = requestBodyParam.required();

        Object arg = readWithMessageConverters(webRequest, parameter, bodyType);
        if (arg instanceof Map) {

            Map<String, Object> argMap = (Map<String, Object>) arg;
            if (!argMap.containsKey(parameterName)) {
                if (required) {
                    throw new MissingRequestBodyParameterException(parameterName);
                } else {
                    return null;
                }
            }

            Object argValue = argMap.get(parameterName);

            Class<?> argType = argValue.getClass();
            Class<?> parameterType = parameter.getParameterType();
            if (!parameterType.isAssignableFrom(argType)) {
                if (conversionService != null && conversionService.canConvert(argType, parameterType)) {
                    return conversionService.convert(argValue, parameterType);
                } else {
                    throw new ConversionNotSupportedException(argValue, parameterType, null);
                }
            }

            return argValue;
        } else {
            try {
                BeanWrapperImpl wrapper = new BeanWrapperImpl(arg);
                return wrapper.getPropertyValue(parameterName);
            } catch (BeansException e) {
                logger.error("RequestBodyParamArgumentResolver#resolveArgument - Retrieving property [{}] encounters beans exception: {}", parameterName, e);
                if (required) {
                    throw new HttpMessageNotReadableException("Required request body parameter [" + parameterName + "] is missing");
                }
                return null;
            }
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver#readWithMessageConverters(org.springframework.web.context.request.NativeWebRequest, org.springframework.core.MethodParameter, java.lang.reflect.Type)
     */
    @Override
    protected <T> Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter methodParam, Type paramType)
            throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        Object arg = servletRequest.getAttribute(REQUEST_BODY_ATTRIBUTE);
        if (arg == null) {
            ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
            arg = readWithMessageConverters(inputMessage, methodParam, paramType);
            if (arg == null) {
                if (methodParam.getParameterAnnotation(RequestBodyParam.class).required()) {
                    throw new HttpMessageNotReadableException("Required request body is missing: " +
                            methodParam.getMethod().toGenericString());
                }
            } else {
                // Store the converted request body object into request for further reference
                servletRequest.setAttribute(REQUEST_BODY_ATTRIBUTE, arg);
            }
        }
        return arg;
    }

}
