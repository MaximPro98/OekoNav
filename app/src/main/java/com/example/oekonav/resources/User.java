package com.example.oekonav.resources;

import java.util.ArrayList;

public class User {


    private String name;
    private String description;
    private int score;
    private ArrayList<User> friendlist = new ArrayList<>();

    public User(String name, String description, int score) {
        this.name = name;
        this.description = description;
        this.score = score;
    }

    public boolean addFriend(User friend){
        if (friendlist.contains(friend)) return false;

        friendlist.add(friend);
        return true;
    }

    public void removeFriend(User friend){
        friendlist.remove(friend);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
