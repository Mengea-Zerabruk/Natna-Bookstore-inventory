package com.mluetech.springbootbookstoreinventory.service;


import com.mluetech.springbootbookstoreinventory.entity.Book;
import com.mluetech.springbootbookstoreinventory.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public void save(Book book){
        bookRepository.save(book);
    }

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id){

        return bookRepository.findById(id).get();
    }

    public void deleteById(long id){
        bookRepository.deleteById(id);
    }


}
