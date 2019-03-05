/**
 *
 */
package com.zzl.web.util;

import java.lang.annotation.*;

/**
 * @author lute
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SerializeField {

    /**
     * The view names that serialization will not be applied. Use this with
     * HttpResult. See HttpResult#targetView and ExtendedFastJsonHttpMessageConverter4.
     */
    String[] excludingViews() default {};

}
