package com.example.demospringapi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "class")
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id_class;
    @Column(name = "name")
    private String class_name;
    @Column(name = "number_member")
    private Integer number_member = 0;
    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Student> students;


    public ClassRoom(Integer id_class, String class_name, Integer number_member) {
        this.id_class = id_class;
        this.class_name = class_name;
        this.number_member = number_member;
    }

    public ClassRoom() {

    }

    public Integer getId_class() {
        return id_class;
    }

    public void setId_class(Integer id_class) {
        this.id_class = id_class;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Integer getNumber_member() {
        return number_member;
    }

    public void setNumber_member(Integer number_member) {
        this.number_member = number_member;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id_class=" + id_class +
                ", class_name='" + class_name + '\'' +
                ", number_member=" + number_member +
                '}';
    }
}

