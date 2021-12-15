package com.example.demo;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
// The controller is kind of a data link layer where all the api's are linked and it talks with the service layer
public class StudentController {
    private final StudentService studentService;
    private final BookService bookService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, BookService bookService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.studentRepository = studentRepository;
    }

    @RequestMapping("/student/{id}")
    public String findStudentById(@PathVariable("id") Long id, Model model) throws NotFoundException {
        final Student student = studentService.findStudentById(1L);
        model.addAttribute("student", student);
        return "list-student";
    }
    //This function is used for the registration form where the user will fill the details
    @GetMapping("/")
    public String register(final Model model){
        model.addAttribute("student", new Student());
        return "register";
    }
    @PostMapping("/")
    public String user(final @Valid Student student, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registrationForm", student);
            return "register";
        }
        System.out.println("New Entry");
        System.out.println(student);
        studentService.register(student);
        return "redirect:/books";
    }
// This function works with the update page and provides the form to update the profile
    @GetMapping("/update/{id}")
    public String UpdateForm(@PathVariable("id") Long id, Model model)throws NotFoundException {
        final Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);

        return "update-student";
    }
// This function helps to push the updates to the database
    @PostMapping("/update/{id}")
    public String updateStudent(final @Valid Student student,@PathVariable("id") Long id, final BindingResult result,final Model model, Book book)throws NotFoundException {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }

        studentService.deleteStudent(id);
        studentService.upadateStudent(student);
        return "redirect:/books";
    }
    // This function deletes the entry of student from the database
    @RequestMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable("id") Long id, Model model) throws NotFoundException {
        List<Student> students=studentService.findAllStudents();
        Student student=students.get(students.size()-1);
        studentService.deleteStudent(student.getId());
        model.addAttribute("book", bookService.findAllBooks());
        return "redirect:/";
    }

}
