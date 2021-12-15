package com.example.demo;
import org.springframework.beans.BeanUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findStudentById(Long id) throws NotFoundException {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with ID %d", id)));
    }

    @Override
    public void createStudent(Student student){
        studentRepository.save(student);
    }

    @Override
    public void upadateStudent(Student student){
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) throws NotFoundException {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("student not found with ID %d", id)));

        studentRepository.deleteById(student.getId());
    }
    @Override
    public void register(Student student)  {
        if (checkIfUserExist(student.getEmail())) {
            System.out.println("Email found...");
        }
            Student student2 = new Student();
            BeanUtils.copyProperties(student, student2);
            studentRepository.save(student2);

    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public boolean checkIfUserExist(String email)  {
        return studentRepository.findStudentByEmail(email) !=null ? true : false;
    }

}
