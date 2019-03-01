/**
 *
 */
package com.cnhutong.cs.mobile.web.util;

import com.alibaba.fastjson.serializer.PropertyFilter;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

/**
 * @author lute
 */
public class ViewExcludingPropertyFilter implements PropertyFilter {

    private String excludingViewName;

    /**
     * @param excludingViewName
     */
    public ViewExcludingPropertyFilter(String excludingViewName) {
        this.excludingViewName = excludingViewName;
    }

    /* (non-Javadoc)
     * @see com.alibaba.fastjson.serializer.PropertyFilter#apply(java.lang.Object, java.lang.String, java.lang.Object)
     */
    public boolean apply(Object object, String name, Object value) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(name) && field.isAnnotationPresent(SerializeField.class)) {
                SerializeField serializeField = field.getAnnotation(SerializeField.class);
                String[] excludingViews = serializeField.excludingViews();
                if (ArrayUtils.contains(excludingViews, excludingViewName)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @return the excludingViewName
     */
    public String getExcludingViewName() {
        return excludingViewName;
    }

}
