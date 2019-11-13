package entity.poi;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Student {

    private int id;
    private String name;
    private int age;
    private String brith;

    public Student() {

    }

    public Student(int id, String name, int age, String brith) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.brith = brith;
    }

    /**
     * @功能：手工构建一个简单格式的Excel
     */
    public static List<Student> getStudents()
    {
        List<Student> list = new ArrayList<Student>();
        Student user1 = new Student(1, "张三", 26, "1985-11-12");
        Student user2 = new Student(2, "李四", 17, "1996-08-12");
        Student user3 = new Student(3, "王五", 26, "1985-11-12");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
