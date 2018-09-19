package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException {
        String className = "reflect.Person";

        Class clazz = Class.forName(className);
        Person person = (Person) clazz.newInstance();
        Method method = clazz.getMethod("setText", String.class);
        method.invoke(person, "hello word");

        person.work();
    }

}
