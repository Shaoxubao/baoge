package guaua;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author shaoxubao
 * @Date 2019/7/30 15:11
 */

@Getter
@Setter
public class Person implements Serializable {
    private static final long serialVersionUID = -6917551015188433639L;

    private String name;
    private Integer age;
    private String sex;
    private String country;

    public Person(String name, Integer age, String sex, String country) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
