package com.example.demo;

import com.example.demo.Book;

import java.util.List;
// The another search we did using strategy pattern where user can search via tittle and author name
public interface Search {
    List<Book> searchCatalog(List<Book> books);
}