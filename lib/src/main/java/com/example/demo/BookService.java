package com.example.demo;

import javassist.NotFoundException;

import java.util.List;

//This interface acts as a service layer and it talks with the controller and repository to get the desired data
public interface BookService {
    public List<Book> findAllBooks();

    public List<Book> searchBooks(String keyword);

    public Book findBookById(Long id) throws NotFoundException;



    public void deleteBook(Long id) throws NotFoundException;
}
