package com.example.domain;

/**
 * Created by Administrator on 2017-03-08.
 */
public class Room {
    private int id;
    private int size;
    private String name;
    
    public Room(int id, int size, String name) {
        this.id = id;
        this.size = size;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }
}
