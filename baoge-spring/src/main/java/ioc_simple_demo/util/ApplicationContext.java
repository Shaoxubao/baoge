package ioc_simple_demo.util;

import ioc_simple_demo.IocSimpleDemo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private Map<String, Object> beanMap = new ConcurrentHashMap();

    public ApplicationContext(String resourcePath) throws Exception {
        InputStream inputStream = IocSimpleDemo.class.getClassLoader().getResourceAsStream(resourcePath);
        SAXReader saxReader = new SAXReader();

        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
//        List<Element> elementList = rootElement.elements();
        // 只获取xml中bean标签元素
        List<Element> elementList = rootElement.selectNodes("//bean");
        for (Element element : elementList) {
            String id = element.attributeValue("id");
            Class clazz = Class.forName(element.attributeValue("class"));
            Object object = clazz.newInstance();

            List<Element> propertyElementList = element.elements();
            for (Element propertyElement : propertyElementList) {
                Field field = clazz.getDeclaredField(propertyElement.attributeValue("name"));
                String value = propertyElement.attributeValue("value");
                // 强制反射出私有属性
                field.setAccessible(true);
                if (field.getType() == String.class) {
                    field.set(object, value);
                } else if (field.getType() == Integer.class) {
                    field.set(object, Integer.parseInt(value));
                } else if (field.getType() == int.class) {
                    field.set(object, Integer.parseInt(value));
                }
            }
            beanMap.put(id, object);
        }
//        System.out.println(beanMap);
    }

    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }

}
