package com.webDemo.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

public class BeanPropertyUtil {
	
	/**
	 * 将Bean对象转换成Map对象，将忽略掉值为null或size=0的属性
	 * @param obj 对象
	 * @return 若给定对象为null则返回size=0的map对象
	 */
	public static Map<String, Object> toMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		BeanMap beanMap = new BeanMap(obj);
		Iterator<String> it = beanMap.keyIterator();
		while (it.hasNext()) {
			String name = it.next();
			Object value = beanMap.get(name);
			// 转换时会将类名也转换成属性，此处去掉
			if (value != null && !name.equals("class")) {
				//map.put(name, value);
				map.put(name.toLowerCase(), value);
			}
		}
		return map;
	}

	/**
	 * 该方法将给定的所有对象参数列表转换合并生成一个Map，对于同名属性，依次由后面替换前面的对象属性
	 * @param objs 对象列表
	 * @return 对于值为null的对象将忽略掉
	 */
	public static Map<String, Object> toMap(Object... objs) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Object object : objs) {
			if (object != null) {
				map.putAll(toMap(object));
			}
		}
		return map;
	}

	/**
	 * 获取接口的泛型类型，如果不存在则返回null
	 * @param clazz
	 * @return
	 */
	public static Class<?> getGenericClass(Class<?> clazz) {
		Type t = clazz.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			return ((Class<?>) p[0]);
		}
		return null;
	}
	
	public static Object convertMapToBean(Object targetEntity, Map<String,Object> formMap) {
		String[] beanProperties = getBeanAllPropertyName(targetEntity);
		Map<String,Object> beanPropClassMap = getBeanPropClassMap(targetEntity);
		for (String property : beanProperties) {
			if ((formMap.containsKey(property))) {
				try {
					BeanUtils.setProperty(targetEntity, property, classConvert(formMap.get(property), beanPropClassMap.get(property).toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return targetEntity;
	}
	
	public static String[] getBeanAllPropertyName(Object bean) {
		List<String> resultList = new ArrayList<String>();
		try {
			Class<?> cls = bean.getClass();
			for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
				Field[] field = cls.getDeclaredFields();
				for (Field f : field) {
					resultList.add(f.getName());
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return (String[]) resultList.toArray(new String[resultList.size()]);
	}
	
	public static Map<String,Object> getBeanPropClassMap(Object bean) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			BeanInfo info = Introspector.getBeanInfo(bean.getClass());
			if (info != null) {
				PropertyDescriptor pd[] = info.getPropertyDescriptors();
				for (int i = 0; i < pd.length; i++) {
					String fieldName = pd[i].getName();
					if (fieldName != null && !fieldName.equals("class")) {
						resultMap.put(fieldName, pd[i].getPropertyType()
								.getName());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultMap;
	}
	
	private static Object classConvert(Object o, String className)throws Exception {
		Object returnObj = null;
		if (className.indexOf("[") == -1) { // 非数组类型
			if (className.equals("java.lang.String")) {
				return o;
			} else if (className.equals("java.lang.Integer")
					|| className.equals("int")) {
				if (StringUtil.isEmpty(o.toString())) {
					o = new Integer(0);
				}
				return new Integer(o.toString());
			} else if (className.equals("java.lang.Float")
					|| className.equals("float")) {
				if (StringUtil.isEmpty(o.toString())) {
					o = new Float(0);
				}
				return new Float(o.toString());
			} else if (className.equals("java.lang.Long")
					|| className.equals("long")) {
				if (StringUtil.isEmpty(o.toString())) {
					o = new Long(0);
				}
				return new Long(o.toString());
			} else if (className.equals("java.lang.Double")
					|| className.equals("double")) {
				if (StringUtil.isEmpty(o.toString())) {
					o = new Double(0);
				}
				return new Double(o.toString());
			} else if (className.equals("java.math.BigDecimal")) {
				if (StringUtil.isEmpty(o.toString())) {
					o = new BigDecimal(0);
				}
				return new BigDecimal(o.toString());
			} else if (className.equals("java.lang.Byte")) {
				return new Byte(o.toString());
			} else if (className.equals("java.util.Date")) {
				String strDateTime = o.toString();
				java.util.Date returnValue = null;
				if (strDateTime.length() != 0) {
					if (strDateTime.length() == 10) {
						try {
							returnValue = DateUtil.StringToDate(strDateTime,
									"yyyy-MM-dd");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (strDateTime.length() == 16) {
						try {
							returnValue = DateUtil.StringToDate(strDateTime,
									"yyyy-MM-dd HH:mm");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (strDateTime.length() == 19) {
						try {
							returnValue = DateUtil.StringToDate(strDateTime,
									"yyyy-MM-dd HH:mm:ss");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (strDateTime.length() == 23) {
						try {
							returnValue = DateUtil.StringToDate(strDateTime,
									"yyyy-MM-dd HH:mm:ss:SSS");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				return returnValue;
			}
		} else { // 数组类型
			String[] oArray = (String[]) o;
			if (className.equals("[java.lang.String;")) { // String 数组
				return oArray;
			} else if (className.equals("[java.lang.Integer;")) { // Integer 数组
				Integer[] temp = new Integer[oArray.length];
				for (int i = 0; i < oArray.length; i++) {
					temp[i] = new Integer(oArray[i]);
				}
				return temp;
			} else if (className.equals("[java.lang.Long;")) { // Long 数组
				Long[] temp = new Long[oArray.length];
				for (int i = 0; i < oArray.length; i++) {
					temp[i] = new Long(oArray[i]);
				}
				return temp;
			} else if (className.equals("[java.lang.Float;")) { // Float 数组
				Float[] temp = new Float[oArray.length];
				for (int i = 0; i < oArray.length; i++) {
					temp[i] = new Float(oArray[i]);
				}
				return temp;
			} else if (className.equals("[java.lang.Double;")) { // Double 数组
				Double[] temp = new Double[oArray.length];
				for (int i = 0; i < oArray.length; i++) {
					temp[i] = new Double(oArray[i]);
				}
				return temp;
			} else if (className.equals("[java.lang.BigDecimal;")) { // BigDecimal
																		// 数组
				BigDecimal[] temp = new BigDecimal[oArray.length];
				for (int i = 0; i < oArray.length; i++) {
					temp[i] = new BigDecimal(oArray[i]);
				}
				return temp;
			}
		}
		return returnObj;
	}

	public static Map<String, Object> ConvertObjToMap(Object obj) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				try {
					Field f = obj.getClass().getDeclaredField(fields[i].getName());
					f.setAccessible(true);
					Object o = f.get(obj);
					reMap.put(fields[i].getName(), o);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return reMap;
	}
	
}