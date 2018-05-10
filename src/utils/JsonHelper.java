package utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class JsonHelper {

    private JsonHelper() {
        super();
    }

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JSR310Module());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String toJsonString(T t) throws JsonProcessingException {
        if (null == t) {
            return null;
        }
        return getInstance().writeValueAsString(t);
    }

    /**
     * java对象转换方法
     * List或Map类型的复杂对象，见
     * {@link #toJsonObject} (String jsonString, Class<?> collectionType, Class<?>... elementType)
     *
     * @param jsonString
     * @param clazz
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T toJsonObject(String jsonString, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        return getInstance().readValue(jsonString, clazz);
    }

    /**
     * 解析复杂对象方法
     * 可以解析List或Map的复杂对象
     *
     * @param jsonString
     * @param collectionType
     * @param elementType
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T toJsonObject(String jsonString, Class<?> collectionType, Class<?>... elementType) throws JsonParseException, JsonMappingException, IOException {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        JavaType javaType = constructParametricType(collectionType, elementType);
        return getInstance().readValue(jsonString, javaType);
    }

    private static JavaType constructParametricType(Class<?> collectionType, Class<?>... elementType) {
        return getInstance().getTypeFactory().constructParametrizedType(collectionType, collectionType, elementType);
    }

    private static ObjectMapper getInstance() {
        if (null == mapper) {
            mapper.registerModule(new JSR310Module());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return mapper;
    }

}
