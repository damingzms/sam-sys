package cn.sam.test.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

/**
 * JSON 工具类
 * 
 */
public final class JsonUtil {

	private static JsonFactory jsonFactory = new JsonFactory();

	private static ObjectMapper mapper = null;

	static {
		jsonFactory.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		jsonFactory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

		mapper = new ObjectMapper(jsonFactory);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 获取jackson json lib的ObjectMapper对象
	 * 
	 * @return ObjectMapper对象
	 */
	public static ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * 获取jackson json lib的JsonFactory对象
	 * 
	 * @return JsonFactory对象
	 */
	public static JsonFactory getJsonFactory() {
		return jsonFactory;
	}

	/**
	 * 将json转成java bean
	 * 
	 * @param <T>
	 *            多态类型
	 * @param json
	 *            json字符串
	 * @param clazz
	 *            java bean类型(Class)
	 * @return java bean对象
	 */
	public static <T> T toBean(String json, Class<T> clazz) {

		T rtv = null;
		try {
			rtv = mapper.readValue(json, clazz);
		} catch (Exception ex) {
			throw new IllegalArgumentException("json字符串转成java bean异常", ex);
		}
		return rtv;
	}

	/**
	 * 把字符串的集合对象转json
	 * 
	 * @param <A>
	 * 
	 * @param json
	 *            json字符串
	 * @param listClass
	 *            集合类
	 * @param beanClass
	 *            集合对象类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T, K> T toBean(String json, Class<T> listClass, Class<K> beanClass) {
		T rtv = null;
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(listClass, beanClass);
			rtv = mapper.readValue(json, javaType);
		} catch (JsonMappingException ex) {
			if (Collection.class.isAssignableFrom(listClass)) {
				try {
					K a = mapper.readValue(json, beanClass);
					List<K> list = new ArrayList<K>();
					list.add(a);
					rtv = (T) list;
				} catch (Exception e) {
					throw new IllegalArgumentException("json字符串转成java bean异常", e);
				}
			}
		} catch (Exception ex) {
			throw new IllegalArgumentException("json字符串转成java bean异常", ex);
		}
		return rtv;
	}

	/**
	 * 将java bean转成json
	 * 
	 * @param bean
	 *            java bean
	 * @return json 字符串
	 */
	public static String toJson(Object bean) {

		String rtv = null;
		try {
			rtv = mapper.writeValueAsString(bean);
		} catch (Exception ex) {
			throw new IllegalArgumentException("java bean转成json字符串异常", ex);
		}
		return rtv;
	}
}