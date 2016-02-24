package com.legomin.model;

import javax.persistence.*;

/**
 * Created by Виталий on 08.02.2016.
 */
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    public User() { }

    public User(Integer id) {
        this.id = id;
    }

    public User(String email, String name) {
        this.email = email;
        this.password = name;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}