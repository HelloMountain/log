package tqs.log.model;

import lombok.Data;

import java.util.Date;

@Data
public class TestModel {

    private String id;
    private String name;
    private int age;
    private String addr;
    private Date birth = new Date();
    private int sex = 1;

    public TestModel() {
    }

    public TestModel(String id, String name, int age, String addr, Date birth, int sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addr = addr;
        this.birth = birth;
        this.sex = sex;
    }
}
