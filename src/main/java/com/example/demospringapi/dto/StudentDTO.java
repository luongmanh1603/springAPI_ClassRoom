package com.example.demospringapi.dto;

public class StudentDTO {
    private Long id;
    private int age;
    private String name;
    private String email;
    private String className;
    private Integer classId;


    // Getters and setters
    public StudentDTO() {
    }

    public StudentDTO(Long id, int age, String name, String email, String className, Long classId) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
        this.className = className;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
