/**
 * 
 */
package com.cnhutong.cs.mobile.web.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 
 * @author Downpour
 */
public class PersistentAnnotationFormatterFactory implements AnnotationFormatterFactory<PersistentFormat>, InitializingBean {

    private String entityPackage;

    private Set<Class<?>> fieldTypes;

    private PersistentFormatLoading persistentFormatLoading;

    /**
     * @param entityPackage the entityPackage to set
     */
    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    /**
     * @param persistentFormatLoading the persistentFormatLoading to set
     */
    public void setPersistentFormatLoading(PersistentFormatLoading persistentFormatLoading) {
        this.persistentFormatLoading = persistentFormatLoading;
    }

    /* 
     * (non-Javadoc)
     * 
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        this.fieldTypes = this.scanPersistentFormatterClass();
    }

    /**
     * @return
     */
    private Set<Class<?>> scanPersistentFormatterClass() {
        
        Set<Class<?>> persistentFormatterClass = new HashSet<Class<?>>();
        
        // scan entity package
        ClasspathScanner<Object> classpathScanner = new ClasspathScanner<Object>();
        classpathScanner.find(new ClasspathScanner.AnnotatedWith(PersistentFormat.class), new String[] { entityPackage });
        persistentFormatterClass.addAll(classpathScanner.getClasses());
        
        return persistentFormatterClass;
    }

    /* 
     * (non-Javadoc)
     * 
     * @see org.springframework.format.AnnotationFormatterFactory#getFieldTypes()
     */
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    /* 
     * (non-Javadoc)
     * 
     * @see org.springframework.format.AnnotationFormatterFactory#getPrinter(java.lang.annotation.Annotation, java.lang.Class)
     */
    public Printer<?> getPrinter(PersistentFormat annotation, Class<?> fieldType) {
        return null;
    }

    /* 
     * (non-Javadoc)
     * 
     * @see org.springframework.format.AnnotationFormatterFactory#getParser(java.lang.annotation.Annotation, java.lang.Class)
     */
    public Parser<?> getParser(final PersistentFormat annotation, final Class<?> fieldType) {
        
        return new Parser<Object>() {
            
            public Object parse(String text, Locale locale) throws ParseException {
                
                Class<?> clazz = annotation.key();
                
                Serializable id = SerializeUtils.serialize(clazz, text);
                
                return StringUtils.isNotEmpty(text) ? persistentFormatLoading.load(fieldType, id) : null;
            
            }
            
        };
    }
    
}
