package com.springboot.configdemo.MyApplication.fullstack.email;

import java.util.Objects;

public class Otp {
    private String email;
    private int otp;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Otp otp1 = (Otp) o;
        return getOtp() == otp1.getOtp() && getEmail().equals(otp1.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getOtp());
    }

    @Override
    public String toString() {
        return "Email : " + email + '\n' +
                "Otp : " + otp;
    }
}
