/**
 * 
 */
package com.zzl.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Downpour
 * @author lute
 * 
 */
public abstract class JsonUtils {
    
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    static {
        // initially set default generate feature to empty
        JSON.DEFAULT_GENERATE_FEATURE = 0;
    }
    
    public static final SerializerFeature[] DEFAULT_SERIALIZE_FEATURES = {
                                        SerializerFeature.QuoteFieldNames,
                                        SerializerFeature.SkipTransientField,
                                        SerializerFeature.SortField,
                                        SerializerFeature.WriteEnumUsingToString,
                                        SerializerFeature.WriteMapNullValue,
                                        SerializerFeature.WriteNullStringAsEmpty,
                                        SerializerFeature.WriteNullNumberAsZero,
                                        SerializerFeature.DisableCircularReferenceDetect
                                    };

    /**
     * Serializes object to JSON string using default serializer features
     * 
     * @param object  the object to be serialized to JSON string
     * @return        JSON string of the object
     * @see           #DEFAULT_SERIALIZE_FEATURES
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object, DEFAULT_SERIALIZE_FEATURES);
    }

    /**
     * Serializes object to JSON string with serializer features
     * 
     * @param object    the object to be serialize to JSON string
     * @param features  serialize feature applied when parses
     * @return          JSON string of the object
     */
    public static String toJsonString(Object object, SerializerFeature... features) {
        return JSON.toJSONString(object, features);
    }

    /**
     * Configures fastjson's default generate feature with serializer feature
     * 
     * @param feature  the serializer feature to be configured
     * @param enable   {@code true} to enable feature, {@code false} to disable feature
     */
    public static void configDefaultGenerateFeature(SerializerFeature feature, boolean enable) {
        JSON.DEFAULT_GENERATE_FEATURE = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, feature, enable);
    }

    /**
     * Parses JSON string to a Map
     * 
     * @param jsonText  the JSON string to be parsed
     * @return          a Map whose key is String and value is Object
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parse(String jsonText) {
        try {
            Map<String, Object> object = (Map<String, Object>) JSON.parse(jsonText);
            return object;
        } catch (Exception e) {
            logger.error("#parse - ", e);
            return null;
        }
    }

    /**
     * Parses JSON string to a generic type by {@link TypeReference}
     * 
     * @param jsonText  the JSON string to be parsed
     * @param type      the {@link TypeReference} of desired type
     * @return          the parsed desired type
     */
    public static <T> T parse(String jsonText, TypeReference<T> type) {
        return JSON.parseObject(jsonText, type);
    }

    /**
     * Parses JSON string to a generic type by {@link TypeReference}
     * 
     * @param jsonText  the JSON string to be parsed
     * @param type      the {@link TypeReference} of desired type
     * @param features  the parse features to be applied when parsing
     * @return          the parsed desired type
     */
    public static <T> T parse(String jsonText, TypeReference<T> type, Feature... features) {
        return JSON.parseObject(jsonText, type, features);
    }

    /**
     * Parses JSON string to a specific class type
     * 
     * @param jsonText  the JSON string to be parsed
     * @param clazz     the type of desired class which is parsed to
     * @return          the specific type of class
     */
    public static <T> T parse(String jsonText, Class<T> clazz) {
        try {
            T object = JSON.parseObject(jsonText, clazz);
            return object;
        } catch (Exception e) {
            logger.error("#parse - ", e);
            return null;
        }
    }

    /**
     * Parses file string to a Map
     * 
     * @param file  the File contains JSON string
     * @return      a Map whose key is String and value is Object 
     */
    @SuppressWarnings({ "unchecked" })
    public static Map<String, Object> parse(File file) {
        try {
            String jsonText = FileUtils.readFileToString(file, "UTF-8");
            return (Map<String, Object>) JSON.parse(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Parses JSON string to a List of Map
     * 
     * @param jsonText  the JSON string to be parsed
     * @return          a List of Map
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<Map<String, Object>> parseArray(String jsonText) {
        try {
            List<Map<String, Object>> result = (List) JSON.parseArray(jsonText);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses JSON string in File to a List of Map
     * 
     * @param file  the File contains JSON string
     * @return      a List of Map
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List<Map<String, Object>> parseArray(File file) {
        try {
            String jsonText = FileUtils.readFileToString(file, "UTF-8");
            return (List) JSON.parseArray(jsonText);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses JSON string to a List of specific type of class
     * 
     * @param jsonText  the JSON string to be parsed
     * @param clazz     the type of desired class
     * @return          a List of specific class
     */
    public static <T> List<T> parseArray(String jsonText, Class<T> clazz) {
        try {
            List<T> result = JSON.parseArray(jsonText, clazz);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parses JSON string in a File to a list of specific typed class
     * 
     * @param file   the File contains JSON string
     * @param clazz  the type of desired class
     * @return       a List of specific class
     */
    public static <T> List<T> parseArray(File file, Class<T> clazz) {
        try {
            String jsonText = FileUtils.readFileToString(file, "UTF-8");
            return JSON.parseArray(jsonText, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
