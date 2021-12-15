package com.example.demo;


import java.util.List;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// The controller is kind of a data link layer where all the api's are linked and it talks with the service layer to get the required data
@Controller
public class BookController {
    private final BookService bookService;
    private final StudentService studentService;

    public BookController(BookService bookService, StudentService studentService) {
        this.bookService = bookService;
        this.studentService = studentService;
    }

// Each function below is using MVC pattern to interact with front-end and the service layer's in the project
    @RequestMapping(value="/books")
    public String getAllBooks(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        List<Student> students=studentService.findAllStudents();
        Student updateStudent=students.get(students.size()-1);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "books";
    }
    @RequestMapping("/books/author")
    public String getAllBooksByAuthor(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByAuthor();
        context.setContext(search);
        List<Student> students=studentService.findAllStudents();
        Student updateStudent=students.get(students.size()-1);
        books = context.executeSearch(books);
        model.addAttribute("updateStudent",updateStudent);
        model.addAttribute("books", books);
        return "books";
    }

    @RequestMapping("/books/title")
    public String getAllBooksByTitle(Model model)throws NotFoundException{
        List<Book> books = bookService.findAllBooks();
        Context context = new Context();
        Search search = new SearchByTittle();
        context.setContext(search);
        books = context.executeSearch(books);
        List<Student> students=studentService.findAllStudents();
        Student updateStudent=students.get(students.size()-1);
        model.addAttribute("books", books);
        model.addAttribute("updateStudent",updateStudent);
        return "books";
    }

    @RequestMapping("/book/{id}")
    public String findBookById(@PathVariable("id") Long id, Model model)throws NotFoundException{
        final Book book = bookService.findBookById(id);
        model.addAttribute("book", book);
        return "list-book";
    }
// This is the search function which searches book via author and book via name in the database
    @RequestMapping("/search")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        final List<Book> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        List<Student> students=studentService.findAllStudents();
        Student updateStudent=students.get(students.size()-1);
        model.addAttribute("updateStudent",updateStudent);
        return "books";
    }




}
