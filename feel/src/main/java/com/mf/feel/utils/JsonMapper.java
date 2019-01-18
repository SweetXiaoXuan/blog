package com.mf.feel.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;


/**
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper.
 * <p>
 * 封装不同的输出风格, 使用不同的builder函数创建实例.
 *
 * @author mahc
 */
public class JsonMapper {

    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

    private static ObjectMapper mapper;


    public JsonMapper(JsonInclude.Include inclusion) {
        mapper = new ObjectMapper();
        //设置输出时包含属性的风格
        mapper.setSerializationInclusion(inclusion);
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
//		JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW
//		JsonFactory.Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING
//
//		mapper.configure(JsonFactory.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
//		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 创建输出全部属性到Json字符串的Mapper.
     */
    public static JsonMapper buildNormalMapper() {
        return new JsonMapper(JsonInclude.Include.ALWAYS);
    }

    /**
     * 创建只输出非空属性到Json字符串的Mapper.
     */
    public static JsonMapper buildNonNullMapper() {
        return new JsonMapper(JsonInclude.Include.NON_NULL);
    }

    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper.
     */
    public static JsonMapper buildNonDefaultMapper() {
        return new JsonMapper(JsonInclude.Include.NON_DEFAULT);
    }

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper.
     */
    public static JsonMapper buildNonEmptyMapper() {
        return new JsonMapper(JsonInclude.Include.NON_EMPTY);
    }

    public static JsonMapper buildMapper(JsonInclude.Include inclusion) {
        return new JsonMapper(inclusion);
    }

    public static void isNull() {
        if (mapper == null) {
            System.out.println("JsonMapper实例为空");
            buildNormalMapper();
        }
    }

    public static void printlns(Object object, JsonInclude.Include inclusion) {

        Print.println(object, buildMapper(inclusion));
    }

    public static void printlns(Object object) {
        Print.println(object, buildNormalMapper());
    }

    /**
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     */
    public String toJson(Object object) {
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator gen = new JsonFactory().createGenerator(sw);
            mapper.writeValue(gen, object);
            return sw.toString();
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }

    /**
     * 如果对象为Null, 返回"null".
     * 如果集合为空集合, 返回"[]".
     * 如果报错返回""
     */
    public String toJsonErrorToEmpty(Object object) {
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator gen = new JsonFactory().createGenerator(sw);
            mapper.writeValue(gen, object);
            return sw.toString();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     * <p>
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數 constructParametricType 构造类型.
     *
     * @param jsonString json字符串
     * @param clazz      转换的类型
     * @return
     * @see #constructParametricType(Class, Class...)
     */
    public <T> T readJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     * <p>
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型.
     *
     * @see #constructParametricType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public <T> T readJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 如果JSON字符串为Null或"null"字符串, 返回Null.
     * 如果JSON字符串为"[]", 返回空集合.
     * <p>
     * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型.
     *
     * @see #constructParametricType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public <T> T readJson(String jsonString, TypeReference typeReference) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) mapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum,
     * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
//	public void setEnumUseToString(boolean value) {
//		mapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, value);
//		mapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, value);
//	}

    /**
     * 構造泛型的Type如List<MyBean>, 则调用constructParametricType(ArrayList.class,MyBean.class)
     * Map<String,MyBean>则调用(HashMap.class,String.class, MyBean.class)
     */
    public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    /**
     * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
     */
    @SuppressWarnings("unchecked")
    public <T> T update(T object, String jsonString) {
        try {
            return (T) mapper.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        } catch (IOException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
        return null;
    }

    /**
     * 輸出JSONP格式數據.
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    public void println(Object object) {
        System.out.println(toJson(object));
    }

    private static class Print {
        public static void println(Object object, JsonMapper jsonMapper) {
            System.out.println(jsonMapper.toJson(object));
        }
    }

}
