/**
 * 
 */
package com.zzl.web.util;

import java.lang.annotation.*;

/**
 * 
 * @author Downpour
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersistentFormat {

    Class<?> key() default Long.class;

}
