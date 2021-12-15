package com.example.demo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;
//This is the Student Database where all the relations and attributes are created
@Entity(name = "Student")
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
public class Student {


    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "first_name",

            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "First name can not be empty")
    private String firstName;

    @Column(
            name = "last_name",

            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "Last name can not be empty")
    private String lastName;

    @Column(
            name = "email",

            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "Email can not be empty")
    private String email;
    @Column(
            name = "password",

            columnDefinition = "TEXT"
    )
    @NotEmpty(message = "Password can not be empty")
    private String password;

    @Column(
            name = "age"


    )
    private Integer age;

    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}

    )
    private StudentIdCard studentIdCard;
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    // Here we are using Singleton Pattern's Eager method to fetch it with Eager instantiation during runtime
    @JsonManagedReference
    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>();

// Here we are using Singleton Pattern's Lazy method to fetch it with Lazy instantiation during runtime
    @JsonManagedReference
    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions=new ArrayList<>();

// This is the constructor
    public Student(String firstName, String lastName, String email, String password, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
    }


    public Student() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void addBook(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
            book.setStudent(this);
            System.out.println(book);
        }
    }

    public void addTransaction(Transaction transaction){
        if (!this.transactions.contains(transaction)) {
            this.transactions.add(transaction);
            transaction.setStudent(this);
        }
    }

    public void removeBook(Book book) {
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
