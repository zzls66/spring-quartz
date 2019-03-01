package com.cnhutong.cs.mobile.web.util;

import java.io.Serializable;

/**
 * 
 * @author downpour
 */
public interface PersistentFormatLoading {

    /**
     * Loads object by persistent class and primary key
     * 
     * @param persistentClass
     * @param id
     * @return
     */
    public <T> T load(Class<T> persistentClass, Serializable id);

}
