package com.security.securityApplicaton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student_model")
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private String name;
    private int marks;

    public  StudentModel(){}
    public StudentModel(int marks, String name, int studentId) {
        this.marks = marks;
        this.name = name;
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
