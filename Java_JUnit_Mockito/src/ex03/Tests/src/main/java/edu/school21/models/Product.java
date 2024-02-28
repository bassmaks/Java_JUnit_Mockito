package edu.school21.models;

import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private int price;

    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product prod = (Product) o;
        if (this.id != prod.id) return false;
        if (!this.name.equals(prod.name)) return false;
        if (this.price != prod.price) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int code = (int)id + price;
        for (int i = 0; i<name.length(); i++) {
            code += (int)name.charAt(i);
        }
        return code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
