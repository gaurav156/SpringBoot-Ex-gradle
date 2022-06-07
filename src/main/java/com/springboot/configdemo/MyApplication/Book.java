package com.springboot.configdemo.MyApplication;

import java.util.Objects;

public class Book {
    private String bookCategory;
    private String bookTitle;
    private String bookAuthor;

    public Book(String bookCategory, String bookTitle, String bookAuthor) {
        this.bookCategory = bookCategory;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString() {
        return
                "Category : '" + bookCategory + '\'' +
                ", Title : '" + bookTitle + '\'' +
                ", Author : '" + bookAuthor + '\'' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookCategory, book.bookCategory) && Objects.equals(bookTitle, book.bookTitle) && Objects.equals(bookAuthor, book.bookAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookCategory, bookTitle, bookAuthor);
    }

}
