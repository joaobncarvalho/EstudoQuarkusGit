package my.groupId.jongue.rest.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class CreateUserRequest {
    private Integer id;
    private String namee;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}