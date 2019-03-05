/**
 *
 */
package com.zzl.web.util;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import java.lang.annotation.*;
import java.util.Map;

/**
 * This annotation is used to extract parameters respectively in a request body.
 * Owing to Spring internal design, every request input message can be read once.
 * Therefore, this annotation can not be used with {@link RequestBody} together.
 * See {@link AbstractMessageConverterMethodArgumentResolver#readWithMessageConverters}
 * for Spring internal detail implementation.
 *
 * @author lute
 * @see AbstractMessageConverterMethodArgumentResolver#readWithMessageConverters
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestBodyParam {

    /**
     * The name of the parameter in request body
     */
    String value();

    /**
     * The class type of request body
     */
    Class<?> bodyType() default Map.class;

    /**
     * Whether or not the parameter is required in the request body.
     * Default is <code>false</code>.
     */
    boolean required() default true;

}
