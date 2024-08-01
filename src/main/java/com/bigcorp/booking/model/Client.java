package com.bigcorp.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entité : décrit des données persistées dans une classe (un POJO)
 */
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int age;
    private boolean premium;


    // Getters et Setters

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public boolean isPremium() {return premium;}

    public void setPremium(boolean premium) {this.premium = premium;}
}