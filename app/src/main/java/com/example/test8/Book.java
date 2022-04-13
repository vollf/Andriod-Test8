package com.example.test8;

public class Book {
    private String name;
    private String style;
    private String price;

    public Book(String name, String style, String price) {
        this.name = name;
        this.style = style;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
