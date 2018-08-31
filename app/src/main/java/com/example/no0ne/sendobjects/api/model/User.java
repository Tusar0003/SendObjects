package com.example.no0ne.sendobjects.api.model;

/**
 * Created by no0ne on 1/18/18.
 */

public class User {

    private Integer id; // Since it's nullable, that's why using Integer class.
    private String name;
    private String email;
    private int age;
    private String[] topics;

    public User(String name, String email, int age, String[] topics) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.topics = topics;
    }

    public Integer getId() {
        return id;
    }
}
