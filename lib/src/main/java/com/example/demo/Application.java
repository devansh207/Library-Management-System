package com.example.demo;

import com.github.javafaker.Faker;
import javassist.NotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
// This file will be executed as soon as the application runs
    @Bean
    CommandLineRunner commandLineRunner(
            StudentIdCardRepository studentIdCardRepository, StudentService studentService) {
        return args -> {
            // Random fake data is generated to load the database
            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            String password="dvnenen";

            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    password,
                    faker.number().numberBetween(17, 55));
            Book book3=new Book(LocalDateTime.now().minusDays(4), "War and Peace"," Leo Tolstoy",50 );
            student.addBook(book3);


            Book book4=new Book(LocalDateTime.now().minusDays(5), "Think and Grow Rich","Aayush Shah",50 );
            student.addBook(book4);

            Book book5=new Book(LocalDateTime.now().minusDays(9), "The Adventures of Huckleberry Finn"," Mark Twain",100);
            student.addBook(book5);

            Book book6=new Book(LocalDateTime.now().minusDays(11), "Hamlet"," William Shakespeare",10);
            student.addBook(book6);

            Book book7=new Book(LocalDateTime.now().minusDays(15), "The Great Gatsby"," F. Scott Fizgerald",50);
            student.addBook(book7);

            Book abc = new Book(LocalDateTime.now().minusDays(6), " Lolita", "Vladimir Nabokov", 5);
            student.addBook(abc);


            StudentIdCard studentIdCard = new StudentIdCard(
                    "12345678903",
                    student);
            student.setStudentIdCard(studentIdCard);

            student.addTransaction(new Transaction(LocalDateTime.now().minusDays(6)));
            studentService.createStudent(student);

             firstName = faker.name().firstName();
             lastName = faker.name().lastName();
             email = String.format("%s.%s@gmail.com", firstName, lastName);
             password="ghey1@";
            Student student1 = new Student(
                    firstName,
                    lastName,
                    email,
                    password,
                    faker.number().numberBetween(17, 55));
            Book book=new Book(LocalDateTime.now().minusDays(4), "Anna Karenina","Leo Tolstoy",500 );
            student1.addBook(book);

            Book book1=new Book(LocalDateTime.now().minusDays(5), "Think and Grow Riches","Aayushx Shahx",500 );
            student1.addBook(book1);

            Book book2=new Book(LocalDateTime.now().minusDays(6), "Madame Bovary"," Gustav Flaubert",59 );
            student1.addBook(book2);

            StudentIdCard studentIdCard1 = new StudentIdCard(
                    "1234567895",
                    student1);

            student1.setStudentIdCard(studentIdCard1);

            student1.addTransaction(new Transaction(LocalDateTime.now().minusDays(7)));
            studentService.createStudent(student1);

                        studentIdCardRepository.findById(1L)
                    .ifPresent(System.out::println);

        };
    }

    }
