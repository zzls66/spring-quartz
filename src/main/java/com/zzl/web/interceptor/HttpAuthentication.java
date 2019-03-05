/**
 * 
 */
package com.zzl.web.interceptor;

import java.lang.annotation.*;

/**
 * @author Wilson
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface HttpAuthentication {
	
	/**
     * Flag to determine whether to ignore current request or not
     * 
     * @return
     */
    boolean ignore() default false;

}
