package com.springboot.configdemo.MyApplication.fullstack.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class User {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    Logger logger = LoggerFactory.getLogger(getClass());

    public String getId() {
        logger.debug("getId method call");
        return id;
    }

    public void setId(String id) {
        logger.debug("setId method call");
        this.id = id;
    }

    public String getEmail() {
        logger.debug("getEmail method call");
        return email;
    }

    public void setEmail(String email) {
        logger.debug("setEmail method call");
        this.email = email;
    }

    public String getFirstName() {
        logger.debug("getFirstName method call");
        return firstName;
    }

    public void setFirstName(String firstName) {
        logger.debug("setFirstName method call");
        this.firstName = firstName;
    }

    public String getLastName() {
        logger.debug("getLastName method call");
        return lastName;
    }

    public void setLastName(String lastName) {
        logger.debug("setLastName method call");
        this.lastName = lastName;
    }

    public String getPassword() {
        logger.debug("getPassword method call");
        return password;
    }

    public void setPassword(String password) {
        logger.debug("setPassword method call");
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
