package com.mluetech.springbootbookstoreinventory.controller;

import com.mluetech.springbootbookstoreinventory.entity.Book;
import com.mluetech.springbootbookstoreinventory.entity.MyBookList;
import com.mluetech.springbootbookstoreinventory.service.BookService;
import com.mluetech.springbootbookstoreinventory.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookListService myBookListService;
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/book_register")
    public String bookRegister(){
        return "bookRegister";
    }
    @GetMapping("/available_books")
    public ModelAndView getAllBooks(){

        List<Book> list=bookService.getAllBook();

        //Option 1:
//        ModelAndView modelAndView= new ModelAndView();
//        modelAndView.setViewName("bookList");
//        modelAndView.addObject("book", list);
//        return modelAndView;

        //Option 2:
        return new ModelAndView("bookList", "book", list);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book){
        bookService.save(book);
        return "redirect:/available_books";
    }
    @GetMapping("/my_books")
    public String getMyBooks(Model model){
        List<MyBookList> list=myBookListService.getAllMyBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") long id){
        Book book=bookService.getBookById(id);
        MyBookList myBookList= new MyBookList(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
        myBookListService.saveMyBooks(myBookList);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") long id, Model model){
        Book book=bookService.getBookById(id);
        model.addAttribute("book", book);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") long id){
        bookService.deleteById(id);

        return "redirect://available_books";
    }
}
