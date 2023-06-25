package com.husu.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.husu.common.constant.ZoneConstant;
import com.husu.common.exceptions.BizException;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;

/**
 * 序列化/反序列工具
 *
 * @author ricardo
 */
@UtilityClass
public class SerializeUtil {
    private static final JsonMapper JSON_MAPPER;
    private static final XmlMapper XML_MAPPER;

    static {
        JSON_MAPPER = JsonMapper.builder()
                // 注册各项模块，比如jsr310(可以支持LocalDateTime)
                .findAndAddModules()
                // 遇见空对象不报异常
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 多余字段不报错
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 不允许0和1转换为Boolean类型
                .disable(MapperFeature.ALLOW_COERCION_OF_SCALARS)
                .build();

        XML_MAPPER = XmlMapper.builder()
                // 注册各项模块，比如jsr310(可以支持LocalDateTime)
                .findAndAddModules()
                // 列表数据不设置外围标签
                .defaultUseWrapper(false)
                // 遇见空对象不报异常
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                // 多余字段不报错
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                // 不允许0和1转换为Boolean类型
                .disable(MapperFeature.ALLOW_COERCION_OF_SCALARS)
                // 设置日期格式
                .defaultDateFormat(new SimpleDateFormat(ZoneConstant.ISO_8601))
                // 如果属性为nul则不转换为标签
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                // 按照@XmlElement注解进行解析
                .annotationIntrospector(AnnotationIntrospector.pair(
                        new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()),
                        new JacksonAnnotationIntrospector()))
                .build();
    }

    /**
     * 将json反序列化为对象
     */
    public static <T> T deserialization(String json, Class<T> t) {
        try {
            return JSON_MAPPER.readValue(json, t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Json convert to {0} object exception, json: {1}", t, json)
                    , ex);
        }
    }

    /**
     * JSON反序列化成泛型集合
     */
    public static <T> T deserialization(String json, Class<?> collectionClass, Class<?>... elementClasses) {
        try {
            JavaType javaType = JSON_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
            return JSON_MAPPER.readValue(json, javaType);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Json convert to {0} collection object exception, json: {1}", collectionClass, json)
                    , ex);
        }
    }

    /**
     * JSON反序列化成复杂的泛型类型
     *
     * @param <T>
     * @param json
     * @param types
     * @return
     * @author rf62
     * @date Dec 28, 2019 2:13:44 PM
     */
    public static <T> T deserialization(String json, TypeReference<T> types) {
        try {
            return JSON_MAPPER.readValue(json, types);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Json convert to {0} generic object exception, json: {1}", types, json)
                    , ex);
        }
    }

    /**
     * 序列化
     */
    public static <T> String serialize(T t) {
        try {
            return JSON_MAPPER.writeValueAsString(t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Json serialize exception, obj: {0}", t),
                    ex);
        }
    }

    /**
     * 序列化，并自定义序列化策略
     */
    public static <T> String serialize(T t, SerializationFeature... features) {
        try {
            ObjectWriter writer = null;
            if (features != null && features.length > 0) {
                writer = JSON_MAPPER.writer(features[0], Arrays.copyOfRange(features, 1, features.length));
            } else {
                writer = JSON_MAPPER.writer();
            }
            return writer.writeValueAsString(t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Json serialize (with features) exception, obj: {0}", t),
                    ex);
        }
    }

    /**
     * 将xmlString 转换为 指定的对象
     */
    public static <T> T xmlToObject(String xmlString, Class<T> t) {
        try {
            return XML_MAPPER.readValue(xmlString, t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml convert to {0} object exception, xml: {1}", t, xmlString)
                    , ex);
        }
    }

    public static <T> T xmlToObject(InputStream xmlInputStream, Class<T> t) {
        try {
            return XML_MAPPER.readValue(xmlInputStream, t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml stream convert to {0} object exception", t)
                    , ex);
        }
    }

    public static <T> T xmlToObject(byte[] xmlBytes, Class<T> t) {
        try {
            return XML_MAPPER.readValue(xmlBytes, t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml byte[] convert to {0} object exception", t)
                    , ex);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> T xmlToObject(String xmlString, Class<? extends Collection> collectionClass,
                                    Class<T> elementClass) {
        try {
            JavaType javaType = getCollectionJavaType(collectionClass, elementClass);
            return XML_MAPPER.readValue(xmlString, javaType);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml convert to {0} collection object exception, xml: {1}", collectionClass, xmlString)
                    , ex);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> T xmlToObject(InputStream xmlInputStream, Class<? extends Collection> collectionClass,
                                    Class<T> t) {
        try {
            JavaType javaType = getCollectionJavaType(collectionClass, t);
            return XML_MAPPER.readValue(xmlInputStream, javaType);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml stream convert to {0} collection object exception", t)
                    , ex);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> T xmlToObject(byte[] xmlBytes, Class<? extends Collection> collectionClass, Class<T> t) {
        try {
            JavaType javaType = getCollectionJavaType(collectionClass, t);
            return XML_MAPPER.readValue(xmlBytes, javaType);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml byte[] convert to {0} collection object exception", t)
                    , ex);
        }
    }

    /**
     * 将java bean序列化为String
     */
    public static <T> String objectToXml(T t) {
        try {
            return XML_MAPPER.writeValueAsString(t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml serialize exception, obj: {0}", t)
                    , ex);
        }
    }

    public static <T> String objectToXml(T t, SerializationFeature... features) {
        ObjectWriter writer;
        if (features != null && features.length > 0) {
            if (features.length == 1) {
                writer = XML_MAPPER.writer(features[0]);
            } else {
                writer = XML_MAPPER.writer(features[0], Arrays.copyOfRange(features, 1, features.length));
            }
        } else {
            writer = XML_MAPPER.writer();
        }

        try {
            return writer.writeValueAsString(t);
        } catch (Exception ex) {
            throw new BizException(MessageFormat.format("Xml serialize (with features) exception, obj: {0}", t)
                    , ex);
        }
    }

    public static ObjectMapper getMapper() {
        return JSON_MAPPER;
    }

    @SuppressWarnings("rawtypes")
    private static <T> JavaType getCollectionJavaType(Class<? extends Collection> collectionClass,
                                                      Class<T> elementClass) {
        return XML_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClass);
    }
}