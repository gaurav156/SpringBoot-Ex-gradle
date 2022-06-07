package com.springboot.configdemo.MyApplication.ui.books;

import java.util.Arrays;
import java.util.Objects;

public class Books {
    private String bookID;
    private String bookTitle;
    private String issnNo;
    private String[] customerID;

    public Books(String bookID, String bookTitle, String issnNo, String[] customerID) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.issnNo = issnNo;
        this.customerID = customerID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getIssnNo() {
        return issnNo;
    }

    public void setIssnNo(String issnNo) {
        this.issnNo = issnNo;
    }

    public String[] getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String[] customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return getBookID().equals(books.getBookID()) && Objects.equals(getBookTitle(), books.getBookTitle()) && Objects.equals(getIssnNo(), books.getIssnNo()) && Arrays.equals(getCustomerID(), books.getCustomerID());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getBookID(), getBookTitle(), getIssnNo());
        result = 31 * result + Arrays.hashCode(getCustomerID());
        return result;
    }

    @Override
    public String toString() {
        return "BookID : " + bookID +
                "bookTitle : " + bookTitle +
                "bookIssnNo : " + issnNo +
                "bookAuthor : " + Arrays.toString(customerID) ;
    }
}
