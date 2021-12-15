package com.example.demo;

import com.example.demo.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByTittle implements Search {
    @Override
    public List<Book> searchCatalog(List<Book> books){

        List<Book> titleSort = books.stream()
                .sorted(Comparator.comparing(Book::getBookName)).collect(Collectors.toList());

//        for(Book it:books){
//            bookAuthors.add(it.getBookAuthor());
//        }
//        bookAuthors.sort( Comparator.comparing( String::toString ) );
//
//        List<Book> temp = new ArrayList<Book>();
//        for(int i=0; i<bookAuthors.size(); i++){
//            String currAuth = bookAuthors.get(i);
//            for(int j=0; j<books.size(); j++){
//                if(books.get(j).getBookAuthor() == currAuth){
//                    temp.set(i, books.get(j));
//                    books.remove(j);
//                }
//            }
//        }
//        return temp;
        return titleSort;
    }
}