package com.auribises.activitydatapassing;

import java.io.Serializable;

// Model or Bean or POJO
public class Person implements Serializable{

    String name;
    String email;


    public Person(){

    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
