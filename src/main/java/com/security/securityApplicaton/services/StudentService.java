package com.security.securityApplicaton.services;


import com.security.securityApplicaton.models.StudentModel;
import com.security.securityApplicaton.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

//  @Autowired
  private StudentRepo repo;

  public StudentService(StudentRepo repo){
    this.repo=repo;
  }

  public List<StudentModel> getStudents() {
    return repo.findAll();
  }

  public void addStudent(StudentModel student){
    repo.save(student);
  }

  public StudentModel getStudentById(int id){

    return repo.findById(id).orElse(new StudentModel());
  }
}
