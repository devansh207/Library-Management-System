package com.example.demo;

import com.example.demo.Book;

import java.util.List;

public class Context {
    private Search search;

    public Context(){}

    public void setContext(Search search){
        this.search = search;
    }

    public List<Book> executeSearch(List<Book> books){
        return search.searchCatalog(books);
    }
}