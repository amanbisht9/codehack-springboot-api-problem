package crio.codehack.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "codehackuser")
public class User {
    @Id
    private String userId;
    private String userName;
    private int score;
    private List<String> badges;


    public User() {
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.score = 0;
        this.badges = new ArrayList<>();
    }

    public User(String userId, String userName, int score, List<String> badges) {
        this.userId = userId;
        this.userName = userName;
        this.score = score;
        this.badges = badges;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }


    
}