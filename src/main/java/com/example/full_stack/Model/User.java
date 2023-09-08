package com.example.full_stack.Model;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Entity
@Table(name="user_Info")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long user_id;



    @Column
    private Date updateDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name="status")
    private String status;

    public User(String krish, String kumar, String mail, Date date, String number, String s) {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="first_name")
    private String first_name;

    @Column(name="Last_name")
    private String last_name;

    @Column(name="email")
    private String email;

    @Column(name="date_of_birth")
    private Date date_of_birth;

    @Column(name="phone_number")
    private String phone_number;

    public String getRole() {
        return role;


    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "role")
    private String role;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name="address")
    private String address;


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }



    public void setEmail_id(String email_id) {
        this.email= email_id;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }



    public User( String first_name,String password, String last_name, String email, Date date_of_birth, String phone_number, String address, String role) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.password=password;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.address = address;
        this.role = role;
    }

    public User() {
    }
}
