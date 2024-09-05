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

    @Override
    public String toString() {
        return "{userId : "+userId+", userName : " + userName + ", score : " + score + ", badges : " + badges + "}";
    }


    
}