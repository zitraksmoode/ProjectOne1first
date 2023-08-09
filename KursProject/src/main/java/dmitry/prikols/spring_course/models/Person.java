package dmitry.prikols.spring_course.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getAge() {
        return age;
    }

    public Person() {
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(String full_name, int age, String sex, String email) {
        this.full_name = full_name;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }

    private int id;

    @Size(min = 10, max = 100 , message = "Полное имя должно быть больше чем 10 и меньше чем 100")
    @NotEmpty
    private  String full_name;

    @Min(18)
    private int age;


    private String sex;

    @Email
    @NotEmpty
    private String email;

}
