/**
 * 
 */
package com.zzl.web.util;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @author lute
 *
 */
public class DecimalAnnotationFormatterFactory implements AnnotationFormatterFactory<DecimalFormat> {

    private static final Set<Class<?>> FIELD_TYPES;

    static {
        Set<Class<?>> fieldTypes = new HashSet<Class<?>>(8);
        fieldTypes.add(Integer.class);
        fieldTypes.add(Long.class);
        fieldTypes.add(BigDecimal.class);
        FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
    }

    /* (non-Javadoc)
     * @see org.springframework.format.AnnotationFormatterFactory#getFieldTypes()
     */
    public Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    /* (non-Javadoc)
     * @see org.springframework.format.AnnotationFormatterFactory#getPrinter(java.lang.annotation.Annotation, java.lang.Class)
     */
    public Printer<?> getPrinter(DecimalFormat annotation, Class<?> fieldType) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.format.AnnotationFormatterFactory#getParser(java.lang.annotation.Annotation, java.lang.Class)
     */
    public Parser<?> getParser(final DecimalFormat annotation, final Class<?> fieldType) {
        
        return new Parser<Number>() {
            
            /* (non-Javadoc)
             * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
             */
            public Number parse(String text, Locale locale) throws ParseException {
                BigDecimal number = new BigDecimal(text);
                if (fieldType.isAssignableFrom(Integer.class)) {
                    return number.multiply(BigDecimal.valueOf(annotation.scale())).intValue();
                } else if (fieldType.isAssignableFrom(Long.class)) {
                    return number.multiply(BigDecimal.valueOf(annotation.scale())).longValue();
                } else if (fieldType.isAssignableFrom(BigDecimal.class)) {
                    return number.multiply(BigDecimal.valueOf(annotation.scale()));
                } else {
                    return number;
                }
            }
            
        };
    }

}
