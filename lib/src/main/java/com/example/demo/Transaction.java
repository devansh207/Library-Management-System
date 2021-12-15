package com.example.demo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Transaction")
@Table(name = "transaction")
//This is the Transaction Database where all the relations and attributes are created
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "transaction_sequence"
    )
    @Column(
            name = "id"

    )
    private Long id;
    @Column(
            name = "issued_at",

            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime issuedAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "student_id",

            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_transaction_fk"
            )
    )
    private Student student;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(
//            name = "books_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "books_id_fk"
//            )
//    )
//    private Book book;

//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }

    public Transaction() {
    }

    public Transaction(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", issuedAt=" + issuedAt +
                '}';
    }
}
