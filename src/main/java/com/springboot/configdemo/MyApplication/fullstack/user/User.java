package com.springboot.configdemo.MyApplication.fullstack.user;

import java.util.Objects;

public class User {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getEmail().equals(user.getEmail()) && getFirstName().equals(user.getFirstName()) && Objects.equals(getLastName(), user.getLastName()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getFirstName(), getLastName(), getPassword());
    }

    @Override
    public String toString() {
        return "id : " + id +
                ", email : " + email +
                ", firstName : " + firstName +
                ", secondName : " + lastName +
                ", password : " + password ;
    }
}
