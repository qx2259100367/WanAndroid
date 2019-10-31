package com.example.wanandroid.bean;

public class Stringadd {
    private String name;

    public Stringadd(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Stringadd{" +
                "name='" + name + '\'' +
                '}';
    }
}
