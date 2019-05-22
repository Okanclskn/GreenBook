package com.kitap.greenbook.data.model;

import java.io.Serializable;

public class Book implements Serializable {
    String bookID;
    String bookName;
    String author;
    String bookCategory;
    String bookImageURL;

    public Book() {
    }

    public Book(String bookID, String bookName, String author, String bookCategory, String bookImageURL) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.bookCategory = bookCategory;
        this.bookImageURL = bookImageURL;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookImageURL() {
        return bookImageURL;
    }

    public void setBookImageURL(String bookImageURL) {
        this.bookImageURL = bookImageURL;
    }
}
