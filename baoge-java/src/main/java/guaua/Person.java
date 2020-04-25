package guaua;

import lombok.Getter;
import lombok.Setter;
import utils.object_compare.DescriptionFiled;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @Author shaoxubao
 * @Date 2019/7/30 15:11
 */

@Getter
@Setter
public class Person implements Serializable {
    private static final long serialVersionUID = -6917551015188433639L;

    @DescriptionFiled(
            title = "姓名"
    )
    private String name;

    @DescriptionFiled(
            title = "年龄"
    )
    private Integer age;

    @DescriptionFiled(
            title = "性别"
    )
    private String sex;

    @DescriptionFiled(
            title = "国家"
    )
    private String country;

    private List<Person> personList;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age) &&
                Objects.equals(sex, person.sex) &&
                Objects.equals(country, person.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex, country);
    }
}
