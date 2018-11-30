package com.example.harith.shak.db;

public class Users {

    private int ID;
    private String name;
    private int password;
    private int phone;
    private int follow;

    public Users(int ID, String name, int password, int phone, int follow) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.follow = follow;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPassword() {
        return password;
    }

    public int getPhone() {
        return phone;
    }

    public int getFollow() {
        return follow;
    }

}
