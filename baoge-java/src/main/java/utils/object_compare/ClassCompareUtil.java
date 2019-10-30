package utils.object_compare;

import guaua.Person;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author shaoxubao
 * @Date 2019/9/20 16:09
 *
 * 比较两个对象，对象字段有注解描述，返回两个对象不同值的字段描述及对应值
 *  如：{年龄={newValue=23, oldValue=22}, 性别={newValue=女, oldValue=男}}
 */
public class ClassCompareUtil {

    private static final Map<Class,Map<String,Field>> classMap = new ConcurrentHashMap<>();

    /**
     * 比较两个实体属性值，返回一个boolean,true则表时两个对象中的属性值无差异
     * @param oldObject 进行属性比较的对象1
     * @param newObject 进行属性比较的对象2
     * @return 属性差异比较结果boolean
     */
    public static boolean compareObject(Object oldObject, Object newObject) {
        Map<String, Map<String, Object>> resultMap=compareFields(oldObject, newObject);

        if(resultMap.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个实体属性值，返回一个map以有差异的属性名为key，value为一个Map分别存oldObject,newObject此属性名的值
     * @param oldObject 进行属性比较的对象1
     * @param newObject 进行属性比较的对象2
     * @return 属性差异比较结果map
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Map<String, Object>> compareFields(Object oldObject, Object newObject) {
        Map<String, Map<String, Object>> map = null;

        try {
            /**
             * 只有两个对象都是同一类型的才有可比性
             */
            if (oldObject.getClass() == newObject.getClass()) {
                map = new HashMap<>();

                Class clazz = oldObject.getClass();

                //获取所有字段
                Map<String,Field> fieldMap = getFields(clazz);

                // 获取object的所有属性
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors();

                for (PropertyDescriptor pd : pds) {
                    // 遍历获取属性名
                    String name = pd.getName();

                    // 获取属性注解描述
                    Field f = fieldMap.get(name);
                    if(f == null) {
                        System.out.println("fieldName:" + name + "is not exist or not set annotation!");
                        continue;
                    }
                    DescriptionFiled descriptionFiled = f.getAnnotation(DescriptionFiled.class);
                    String title = descriptionFiled.title();

                    // 获取属性的get方法
                    Method readMethod = pd.getReadMethod();

                    // 在oldObject上调用get方法等同于获得oldObject的属性值
                    Object oldValue = readMethod.invoke(oldObject);
                    // 在newObject上调用get方法等同于获得newObject的属性值
                    Object newValue = readMethod.invoke(newObject);

                    if(oldValue instanceof List) {
                        continue;
                    }

                    if(newValue instanceof List) {
                        continue;
                    }

                    if(oldValue instanceof Timestamp) {
                        oldValue = new Date(((Timestamp) oldValue).getTime());
                    }

                    if(newValue instanceof Timestamp) {
                        newValue = new Date(((Timestamp) newValue).getTime());
                    }

                    if(oldValue == null && newValue == null) {
                        continue;
                    } else if(oldValue == null && newValue != null) {
                        Map<String,Object> valueMap = new HashMap<>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);

//                        map.put(name, valueMap);
                        map.put(title, valueMap);

                        continue;
                    }

                    if (!oldValue.equals(newValue)) { // 比较这两个值是否相等,不等就可以放入map了
                        Map<String, Object> valueMap = new HashMap<>();
                        valueMap.put("oldValue", oldValue);
                        valueMap.put("newValue", newValue);

//                        map.put(name, valueMap);
                        map.put(title, valueMap);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    /**
     * fields设置一层缓存
     * @param clazz
     * @return
     */
    private static Map<String, Field> getFields(Class clazz){
        Map<String, Field> fields = null;
        if((fields = classMap.get(clazz)) == null) {
            fields = getField2(clazz, new HashMap<>());
            classMap.put(clazz, fields);
        }
        return fields;
    }

    private static Map<String, Field> getField2(Class clazz, Map<String, Field> fieldMap){
        Class parentClass = clazz.getSuperclass();
        if(parentClass != null){
            getField2(parentClass, fieldMap);
        }
        Field[] tmp = clazz.getDeclaredFields();
        for (Field field : tmp) {
            if (field.isAnnotationPresent(DescriptionFiled.class)) {
                fieldMap.put(field.getName(), field);
            }
        }
        return fieldMap;
    }

    public static void main(String[] args) {

        Person person = new Person("a", 22, "男", "中国");
        Person person2 = new Person("a", 23, "女", "中国");

        Map<String, Map<String,Object>> mapMap = compareFields(person, person2);

        System.out.println(mapMap);

    }

}
