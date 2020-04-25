package com.baoge.utils;

/**
 * @Author shaoxubao
 * @Date 2020/4/24 10:31
 */

import com.alibaba.fastjson.JSONObject;
import guaua.Person;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 对象转JSON简单实现
 */
public class MyJsonUtils {

    public static String parseBeanToJson(Object object) throws Exception {
        if (object == null) {
            return "";
        }

        String jsonStr = "{";
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields == null || fields.length <= 0) {
            return jsonStr + "}";
        }

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName(); // 属性名
            if (fieldName.equals("serialVersionUID")) {
                continue;
            }
            jsonStr += "\"" + fieldName + "\"";
            field.setAccessible(true); // 设置属性可以访问

            if (List.class.getName().equals(field.getType().getName())) {
                jsonStr += ":";
                String listToJson = parseListToJson(field.get(object));
                jsonStr += listToJson;
            } else if (String.class.getName().equals(field.getType().getName())) {
                String value = field.get(object).toString();
                jsonStr += ":" + "\"" + value + "\"";
            } else if (int.class.getName().equals(field.getType().getName())) {
                String value = field.get(object).toString();
                jsonStr += ":" + value;
            } else if (Integer.class.getName().equals(field.getType().getName())) {
                String value = field.get(object).toString();
                jsonStr += ":" + value;
            } else if (Long.class.getName().equals(field.getType().getName())) {
                String value = field.get(object).toString();
                jsonStr += ":" + value;
            } else if (Double.class.getName().equals(field.getType().getName())) {
                String value = field.get(object).toString();
                jsonStr += ":" + value;
            } else if (BigDecimal.class.getName().equals(field.getType().getName())) {
                String value = field.get(object).toString();
                jsonStr += ":" + value;
            }
            // 如果是最后一个属性，不需要加逗号
            if (i < (fields.length - 1)) {
                jsonStr += ",";
            }
        }

        jsonStr += "}";
        return jsonStr;
    }

    public static String parseListToJson(Object object) throws Exception {
        if (object == null)
            return "[]";
        String jsonStr = "[";
        List list = (List) object;
        for (int j = 0; j < list.size(); j++) {
            String subJson = parseBeanToJson(list.get(j));
            jsonStr += subJson;
            if (j < list.size() - 1) {
                jsonStr += ",";
            }
        }
        jsonStr += "]";
        return jsonStr;
    }


    public static void main(String[] args) throws Exception {
        String json = parseBeanToJson(initData());
        System.out.println("转JSON结果：" + json);
        System.out.println("是否是JSON结构：" + isJson(json));
    }

    private static boolean isJson(String string) {
        try {
            JSONObject jsonStr = JSONObject.parseObject(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Person initData() {
        Person person = new Person("baoge", 22, "男", "China");
        List<Person> personList = new ArrayList<>();
        Person person2 = new Person("baoge2", 22, "男", "China");
        Person person3 = new Person("baoge3", 22, "男", "China");
        Person person4 = new Person("baoge4", 22, "男", "China");
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        person.setPersonList(personList);

        return person;
    }
}
