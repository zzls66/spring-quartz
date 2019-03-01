package com.cnhutong.cs.mobile.web.util;

import java.io.Serializable;

/**
 * 
 * @author downpour
 * @author lute
 */
public class SerializeUtils {

    /**
     * Serializes the string to a specific typed class
     * 
     * @param fieldType  the type of the desired class
     * @param text       the string to be serialized
     * @return           a {@link Serializable} class from the string
     */
    public static Serializable serialize(Class<?> fieldType, String text) {
        
        // TODO how to fix this ugly ??
        
        if(fieldType.equals(Long.class)) {
            return new Long(text);
        } else if(fieldType.equals(Integer.class)) {
            return new Integer(text);
        }
        
        return text;
    }

}
