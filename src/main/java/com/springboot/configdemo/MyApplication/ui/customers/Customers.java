package com.springboot.configdemo.MyApplication.ui.customers;

import java.util.Arrays;
import java.util.Objects;

public class Customers {

    private String customerID;
    private String customerName;
    private String membershipDate;
    private String[] bookID;

    public Customers(String customerID, String customerName, String membershipDate, String[] bookID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.membershipDate = membershipDate;
        this.bookID = bookID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String[] getBookID() {
        return bookID;
    }

    public void setBookID(String[] bookID) {
        this.bookID = bookID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return getCustomerID().equals(customers.getCustomerID()) && Objects.equals(getCustomerName(), customers.getCustomerName()) && Objects.equals(getMembershipDate(), customers.getMembershipDate()) && Arrays.equals(getBookID(), customers.getBookID());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getCustomerID(), getCustomerName(), getMembershipDate());
        result = 31 * result + Arrays.hashCode(getBookID());
        return result;
    }

    @Override
    public String toString() {
        return "customerID : " + customerID +
                "customerName : " + customerName +
                "membershipDate : " + membershipDate +
                "bookID : " + Arrays.toString(bookID) ;
    }
}
