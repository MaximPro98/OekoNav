package com.example.oekonav.resources;

import java.util.Date;
import java.util.Map;

public class Challenge {

    private User creator;
    private String name;
    private Date startDate;
    private Date endDate;
    private String description;
    private boolean official;
    private int reward;
    private Map<User,Boolean> participant;

    public Challenge(User creator, String name, Date startDate, Date endDate, String description, boolean official, int reward) {
        this.creator = creator;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.official = official;
        this.reward = reward;
    }

    public boolean addParticipant(User user){

        if (participant.containsKey(user)) return false;
        participant.put(user, false);
        return true;
    }

    public boolean removeParticipant(User user){
            participant.remove(user);
        return true;
    }


    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOfficial() {
        return official;
    }

    public void setOfficial(boolean official) {
        this.official = official;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
}
