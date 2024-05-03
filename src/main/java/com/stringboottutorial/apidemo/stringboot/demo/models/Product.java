package com.stringboottutorial.apidemo.stringboot.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//POJO - Plain Object Java Object
@Entity
public class Product {
    //this is primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//auto-increment
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private String url;

    //default constuctor
    public Product(){}
    public Product( String name, int quantity, double price, String url) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }
}
