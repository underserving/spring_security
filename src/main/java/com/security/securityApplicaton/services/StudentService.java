package com.security.securityApplicaton.services;


import com.security.securityApplicaton.models.StudentModel;
import com.security.securityApplicaton.repos.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

  private StudentRepo repo;

  public List<StudentModel> getStudents() {
    return repo.findAll();
  }
}
