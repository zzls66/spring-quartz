/**
 *
 */
package com.zzl.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author lute
 */
public class ExtendedFastJsonHttpMessageConverter4 extends FastJsonHttpMessageConverter4 implements InitializingBean {

    private static int DEFAULT_GENERATE_FEATURE;

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private static final SerializerFeature[] DEFAULT_SERIALIZER_FEATURES = {
            SerializerFeature.QuoteFieldNames,
            SerializerFeature.SkipTransientField,
            SerializerFeature.SortField,
            SerializerFeature.WriteEnumUsingToString,
            SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.DisableCircularReferenceDetect
    };

    private SerializerFeature[] serializerFeatures;

    /**
     * @param serializerFeatures the serializerFeatures to set
     */
    public void setSerializerFeatures(SerializerFeature[] serializerFeatures) {
        this.serializerFeatures = serializerFeatures;
    }

    /**
     *
     */
    public ExtendedFastJsonHttpMessageConverter4() {
        setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON_UTF8,
                new MediaType("application", "*+json", DEFAULT_CHARSET))
        );
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        int feature = 0;
        for (SerializerFeature serializerFeature : DEFAULT_SERIALIZER_FEATURES) {
            feature |= serializerFeature.getMask();
        }

        if (ArrayUtils.isNotEmpty(serializerFeatures)) {
            for (SerializerFeature serializerFeature : serializerFeatures) {
                feature |= serializerFeature.getMask();
            }
        }

        DEFAULT_GENERATE_FEATURE = feature;
    }

    /* (non-Javadoc)
     * @see com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4#writeInternal(java.lang.Object, java.lang.reflect.Type, org.springframework.http.HttpOutputMessage)
     */
    @Override
    protected void writeInternal(Object obj, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        FastJsonConfig fastJsonConfig = getFastJsonConfig();
        SerializeFilter[] serializeFilters = fastJsonConfig.getSerializeFilters();
        if (obj instanceof HttpResult) {
            HttpResult result = (HttpResult) obj;
            String targetView = result.getTargetView();
            if (StringUtils.isNotBlank(targetView)) {
                PropertyFilter filter = new ViewExcludingPropertyFilter(targetView);
                serializeFilters = ArrayUtils.add(serializeFilters, filter);
            }
        }

        HttpHeaders headers = outputMessage.getHeaders();
        ByteArrayOutputStream outnew = new ByteArrayOutputStream();
        int len = JSON.writeJSONString(outnew, //
                fastJsonConfig.getCharset(), //
                obj, //
                fastJsonConfig.getSerializeConfig(), //
                serializeFilters, //
                fastJsonConfig.getDateFormat(), //
                DEFAULT_GENERATE_FEATURE, //
                fastJsonConfig.getSerializerFeatures());
        headers.setContentLength(len);
        OutputStream out = outputMessage.getBody();
        outnew.writeTo(out);
        outnew.close();
    }

}
