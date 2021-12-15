package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
// This interface extends inbuilt jpa repository to get our query's running
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %?1%" + " OR b.bookAuthor LIKE %?1%")
    public List<Book> search(String keyword);

    @Transactional
    @Modifying
    @Query("DELETE FROM Book u WHERE u.id = ?1")
    int  deleteBookById(Long id);
}
