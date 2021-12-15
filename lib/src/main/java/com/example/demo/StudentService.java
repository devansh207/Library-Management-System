package com.example.demo;

import javassist.NotFoundException;

import java.util.List;

public interface StudentService {
    public Student findStudentById(Long id) throws NotFoundException;

    public List<Student> findAllStudents();

    public void createStudent(Student student);

    public void upadateStudent(Student student);

    public void deleteStudent(Long id) throws NotFoundException;
    public boolean checkIfUserExist(String email);
    public void register(Student student);


}
