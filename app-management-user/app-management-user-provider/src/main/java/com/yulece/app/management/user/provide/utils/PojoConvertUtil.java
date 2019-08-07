package com.yulece.app.management.user.provide.utils;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PojoConvertUtil {

        /**
         * 变量缓存
         */
        private static final Map<String, Map<String, Field>> cacheFields = new ConcurrentHashMap<>();

        //基础数据类型
        private static final Set<Class<?>> basicClass = new HashSet<>();
        static
        {
            basicClass.add(Integer.class);
            basicClass.add(Character.class);
            basicClass.add(Byte.class);
            basicClass.add(Float.class);
            basicClass.add(Double.class);
            basicClass.add(Boolean.class);
            basicClass.add(Long.class);
            basicClass.add(Short.class);
            basicClass.add(String.class);
            basicClass.add(BigDecimal.class);
        }

        /**
         * 将具有相同属性的类型进行转换
         * @param orig
         * @param <T>
         * @return
         */
        @SuppressWarnings("rawtypes")
        public static <T> T convertPojo(Object orig, Class<T> targetClass)
        {
            try
            {
                T target = targetClass.newInstance();

                if (orig == null)
                {
                    return target;
                }

                // 获取源对象中的所有field
                Class<? extends Object> origClass = orig.getClass();
                Field[] fields = getFieldFromClass(origClass);

                for (Field field : fields)
                {

                    if (isStatic(field))
                    {//跳过静态属性
                        continue;
                    }

                    // 获取目标对象中的对应的field
                    Field targetField = getTargetField(targetClass, field.getName());
                    if (targetField == null)
                        continue;

                    Object value = getFiledValue(field, orig);
                    if (value == null)
                        continue;
                    Class origType = field.getType();
                    Class targetType = targetField.getType();
                    // 两个类型是否相同
                    boolean sameType = origType.equals(targetType);
                    if (isBasicType(origType))
                    {
                        if (sameType)
                            setFieldValue(targetField, target, value);
                    } else if (value instanceof Map && Map.class.isAssignableFrom(targetType))
                    {
                        // map类型拷贝
                        setMap((Map) value, field, targetField, target);
                    } else if (value instanceof Set && Set.class.isAssignableFrom(targetType))
                    {
                        // set类型拷贝
                        setCollection((Collection) value, field, targetField, target);
                    } else if (value instanceof List && List.class.isAssignableFrom(targetType))
                    {
                        // list类型拷贝
                        setCollection((Collection) value, field, targetField, target);
                    } else if (value instanceof Enum && Enum.class.isAssignableFrom(targetType))
                    {
                        //enum拷贝
                        setEnum((Enum) value, field, targetField, target);
                    } else if (value instanceof java.util.Date
                            && java.util.Date.class.isAssignableFrom(targetType))
                    {
                        //对日期类型拷贝，不处理如joda包之类的扩展时间，不处理calendar
                        setDate((Date) value, targetField, targetType, target, sameType);
                    }else{
                        @SuppressWarnings("unchecked")
                        T convertPojo = (T) convertPojo(value, targetType);
                        setFieldValue(targetField, target, convertPojo);
                    }
                }
                return target;
            } catch (Throwable t)
            {
                t.printStackTrace();
                throw new RuntimeException(t.getMessage());
            }
        }

        // 获取类中的field变量，包括父类
        private static Field[] getFieldFromClass(Class<? extends Object> origClass)
        {
            List<Field> fields = new ArrayList<>(Arrays.asList(origClass.getDeclaredFields()));

            if (origClass.getSuperclass() != null)
            {
                fields.addAll(Arrays.asList(getFieldFromClass(origClass.getSuperclass())));
            }

            return fields.toArray(new Field[fields.size()]);
        }

        /**
         * 获取字段值
         * @param field
         * @param obj
         * @return
         */
        private static Object getFiledValue(Field field, Object obj) throws IllegalAccessException
        {
            // 获取原有的访问权限
            boolean access = field.isAccessible();
            try
            {
                // 设置可访问的权限
                field.setAccessible(true);
                return field.get(obj);
            } finally
            {
                // 恢复访问权限
                field.setAccessible(access);
            }
        }

        /**
         * 设置方法值
         * @param field
         * @param obj
         * @param value
         * @throws IllegalAccessException
         */
        private static void setFieldValue(Field field, Object obj, Object value)
            throws IllegalAccessException
        {
            // 获取原有的访问权限
            boolean access = field.isAccessible();
            try
            {
                // 设置可访问的权限
                field.setAccessible(true);
                field.set(obj, value);
            } finally
            {
                // 恢复访问权限
                field.setAccessible(access);
            }
        }

        /**
         * 转换list
         * @param orig
         * @param targetClass
         * @param <T>
         * @return
         */
        @SuppressWarnings("rawtypes")
        public static <T> List<T> convertPojos(List orig, Class<T> targetClass)
        {
            List<T> list = new ArrayList<>(orig.size());
            for (Object object : orig)
            {
                list.add(convertPojo(object, targetClass));
            }
            return list;
        }

        /**
         * 设置Map
         * @param value
         * @param origField
         * @param targetField
         * @param targetObject
         * @param <T>
         */
        @SuppressWarnings(
                { "rawtypes", "unchecked" })
        private static <T> void setMap(Map value, Field origField, Field targetField, T targetObject)
            throws IllegalAccessException, InstantiationException
        {
            Type origType = origField.getGenericType();
            Type targetType = targetField.getGenericType();
            if (origType instanceof ParameterizedType && targetType instanceof ParameterizedType)
            {// 泛型类型
                ParameterizedType origParameterizedType = (ParameterizedType) origType;
                Type[] origTypes = origParameterizedType.getActualTypeArguments();
                ParameterizedType targetParameterizedType = (ParameterizedType) targetType;
                Type[] targetTypes = targetParameterizedType.getActualTypeArguments();
                if (origTypes != null && origTypes.length == 2 && targetTypes != null
                        && targetTypes.length == 2)
                {// 正常泛型,查看第二个泛型是否不为基本类型
                    Class clazz = (Class) origTypes[1];
                    if (!isBasicType(clazz) && !clazz.equals(targetTypes[1]))
                    {// 如果不是基本类型并且泛型不一致，则需要继续转换
                        Set<Map.Entry> entries = value.entrySet();
                        Map targetMap = value.getClass().newInstance();
                        for (Map.Entry entry : entries)
                        {
                            targetMap.put(entry.getKey(),
                                    convertPojo(entry.getValue(), (Class) targetTypes[1]));
                        }
                        setFieldValue(targetField, targetObject, targetMap);
                        return;
                    }
                }
            }
            setFieldValue(targetField, targetObject, value);
        }

        /**
         * 设置集合
         * @param value
         * @param origField
         * @param targetField
         * @param targetObject
         * @param <T>
         * @throws IllegalAccessException
         * @throws InstantiationException
         */
        @SuppressWarnings(
                { "rawtypes", "unchecked" })
        private static <T> void setCollection(Collection value, Field origField, Field targetField,
            T targetObject) throws IllegalAccessException, InstantiationException
        {
            Type origType = origField.getGenericType();
            Type targetType = targetField.getGenericType();
            if (origType instanceof ParameterizedType && targetType instanceof ParameterizedType)
            {// 泛型类型
                ParameterizedType origParameterizedType = (ParameterizedType) origType;
                Type[] origTypes = origParameterizedType.getActualTypeArguments();
                ParameterizedType targetParameterizedType = (ParameterizedType) targetType;
                Type[] targetTypes = targetParameterizedType.getActualTypeArguments();
                if (origTypes != null && origTypes.length == 1 && targetTypes != null
                        && targetTypes.length == 1)
                {// 正常泛型,查看第二个泛型是否不为基本类型
                    Class clazz = (Class) origTypes[0];
                    if (!isBasicType(clazz) && !clazz.equals(targetTypes[0]))
                    {// 如果不是基本类型并且泛型不一致，则需要继续转换
                        Collection collection = value.getClass().newInstance();
                        for (Object obj : value)
                        {
                            collection.add(convertPojo(obj, (Class) targetTypes[0]));
                        }
                        setFieldValue(targetField, targetObject, collection);
                        return;
                    }
                }
            }
            setFieldValue(targetField, targetObject, value);
        }

        /**
         * 设置枚举类型
         * @param value
         * @param origField
         * @param targetField
         * @param targetObject
         * @param <T>
         */
        @SuppressWarnings("rawtypes")
        private static <T> void setEnum(Enum value, Field origField, Field targetField, T targetObject)
            throws Exception
        {
            if (origField.equals(targetField))
            {
                setFieldValue(targetField, targetObject, value);
            } else
            {
                // 枚举类型都具有一个static修饰的valueOf方法
                Method method = targetField.getType().getMethod("valueOf", String.class);
                setFieldValue(targetField, targetObject, method.invoke(null, value.toString()));
            }
        }

        /**
         * 设置日期类型
         * @param value
         * @param targetField
         * @param targetFieldType
         * @param targetObject
         * @param <T>
         */
        @SuppressWarnings("rawtypes")
        private static <T> void setDate(Date value, Field targetField, Class targetFieldType,
            T targetObject, boolean sameType) throws IllegalAccessException
        {
            Date date = null;
            if (sameType)
            {
                date = value;
            } else if (targetFieldType.equals(java.sql.Date.class))
            {
                date = new java.sql.Date(value.getTime());
            } else if (targetFieldType.equals(java.util.Date.class))
            {
                date = new Date(value.getTime());
            } else if (targetFieldType.equals(java.sql.Timestamp.class))
            {
                date = new java.sql.Timestamp(value.getTime());
            }
            setFieldValue(targetField, targetObject, date);
        }

        /**
         * 获取适配方法
         * @param clazz
         * @param fieldName
         * @return
         */
        @SuppressWarnings("rawtypes")
        public static Field getTargetField(Class clazz, String fieldName)
        {
            String classKey = clazz.getName();
            Map<String, Field> fieldMap = cacheFields.get(classKey);
            if (fieldMap == null)
            {
                fieldMap = new HashMap<>();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields)
                {
                    if (isStatic(field))
                        continue;
                    fieldMap.put(field.getName(), field);
                }
                cacheFields.put(classKey, fieldMap);
            }
            return fieldMap.get(fieldName);
        }

        /**
         * 判断数据类型是否是否为基础类型
         */
        public static boolean isBasicType(Class<?> clazz)
        {
            return clazz.isPrimitive() || basicClass.contains(clazz);
        }

        /**
         * 判断变量是否有静态修饰符static
         */
        public static boolean isStatic(Field field)
        {
            return ( Modifier.STATIC & field.getModifiers()) ==  Modifier.STATIC;
        }

}
