package com.springboot.configdemo.MyApplication.fullstack.emerald;

import com.google.gson.JsonElement;

import java.util.Arrays;
import java.util.Objects;

public class EmeraldBooks {

    private String bookID;
    private String bookTitle;
    private String issnNo;
    private String[] customerID;


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
        com.springboot.configdemo.MyApplication.fullstack.emerald.EmeraldBooks book = (com.springboot.configdemo.MyApplication.fullstack.emerald.EmeraldBooks) o;
        return getBookID().equals(book.getBookID()) && Objects.equals(getBookTitle(), book.getBookTitle()) && Objects.equals(getIssnNo(), book.getIssnNo()) && Arrays.equals(getCustomerID(), book.getCustomerID());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getBookID(), getBookTitle(), getIssnNo());
        result = 31 * result + Arrays.hashCode(getCustomerID());
        return result;
    }

//    @Override
//    public JsonElement deepCopy() {
//        return null;
//    }

    @Override
    public String toString() {
        return "BookID : " + bookID +
                "bookTitle : " + bookTitle +
                "bookIssnNo : " + issnNo +
                "bookAuthor : " + Arrays.toString(customerID) ;
    }
}
