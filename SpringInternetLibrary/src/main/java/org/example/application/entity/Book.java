package org.example.application.entity;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String author;
    private String bookName;
    private Integer bookYear;
    private String bookGenre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userName;


    public Book() {
    }
    public Book(String author, String bookName, Integer bookYear, String bookGenre, User user)
    {
        this.userName = user;
        this.author = author;
        this.bookName = bookName;
        this.bookYear = bookYear;
        this.bookGenre = bookGenre;
    }
    public User getUserName() {
        return userName;
    }
    public String getAuthorName(){
        return userName != null ? userName.getUsername() : "<none>";
    }
    public void setUserName(User userName) {
        this.userName = userName;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookYear() {
        return bookYear;
    }

    public void setBookYear(Integer bookYear) {
        this.bookYear = bookYear;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }
}
