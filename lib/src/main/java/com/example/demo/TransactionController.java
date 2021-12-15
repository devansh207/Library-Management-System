package com.example.demo;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

// The controller is kind of a data link layer where all the api's are linked and it talks with the service layer
@Controller
public class TransactionController {
    private final StudentService studentService;
    private final BookService bookService;
    private final TransactionService transactionService;

    public TransactionController(StudentService studentService, BookService bookService, TransactionService transactionService) {
        this.studentService = studentService;
        this.bookService = bookService;
        this.transactionService = transactionService;
    }

    @GetMapping("/add/{id}")
    public String showCreateForm(@PathVariable("id") Long id, Transaction transaction, Model model) throws NotFoundException {
        final Book book= bookService.findBookById(id);
        model.addAttribute("book",book);
        transaction=new Transaction(LocalDateTime.now().minusDays(8));
        model.addAttribute("transaction",transaction);
        return "add-transaction";
    }

    @RequestMapping("/add/{id}")
    public String createTransaction(@PathVariable("id") Long id,Transaction transaction, BindingResult result, Model model)throws NotFoundException {
        if (result.hasErrors()) {
            return "add-transaction";
        }
        List<Student> students=studentService.findAllStudents();
        Student student=students.get(students.size()-1);

        transaction=new Transaction(LocalDateTime.now().minusDays(8));
        student.addTransaction(transaction);
        System.out.println(transaction);
        transactionService.createTransaction(transaction);

        bookService.deleteBook(id);
        model.addAttribute("books", bookService.findAllBooks());

        return "redirect:/books";
    }
}
