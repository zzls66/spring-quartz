/**
 * 
 */
package com.cnhutong.cs.mobile.web.util;

import java.lang.annotation.*;

/**
 * @author lute
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecimalFormat {

    int scale() default 100;

}
