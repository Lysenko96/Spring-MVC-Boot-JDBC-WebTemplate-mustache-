package org.example.application.controller;

import org.example.application.entity.User;
import org.example.application.repository.BookRepository;
import org.example.application.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class LibraryController {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String testing(Map<String, Object> model){
        return "testing";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Book> books = bookRepository.findAll();
        model.put("books",books);
        return "main";
    }
    @PostMapping("/main")
    public String addBook(
            @AuthenticationPrincipal User user,
            @RequestParam String author,
            @RequestParam String bookName,
            @RequestParam Integer bookYear,
            @RequestParam String bookGenre, Map<String, Object> model){
        Book book = new Book(author,bookName,bookYear,bookGenre, user);
        bookRepository.save(book);
        Iterable<Book> books = bookRepository.findAll();
        model.put("books",books);
        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Book> byAuthor;
        if(filter != null && !filter.isEmpty()){
            byAuthor = bookRepository.findByAuthor(filter);
        } else {
            byAuthor = bookRepository.findAll();
        }
        model.put("books", byAuthor);
        return "main";
    }
}
