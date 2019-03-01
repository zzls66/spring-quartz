/**
 * 
 */
package com.cnhutong.cs.mobile.web.resolver;

import java.lang.annotation.*;

/**
 * @author Wilson
 *
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Secure {

    String value() default "";
    
    String property() default "";

}
