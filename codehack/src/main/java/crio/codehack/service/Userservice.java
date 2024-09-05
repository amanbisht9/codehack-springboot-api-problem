package crio.codehack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crio.codehack.dto.Userdto;
import crio.codehack.model.User;
import crio.codehack.repository.UserRepositary;

@Service
public class Userservice {
    
    @Autowired
    private UserRepositary userRepository;

    public void UserService(UserRepositary userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(Userdto userDto) {
        User user = new User(userDto.getUserId(), userDto.getUserName());
        // System.out.println("User saved: " + savedUser.toString());
        return userRepository.insert(user); // Save the user to MongoDB
    }

    public User getByUserId(String userId) {
        return userRepository.findByUserId(userId);

        
    }

    public List<User> getAllUser() {
        if(userRepository.findAll() == null){
            return new ArrayList<>();
        }
        List<User> ans = userRepository.findAll();
        Collections.sort(ans, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u1.getScore(), u2.getScore());
            }
        });
        
        return ans;
    }

    public User updateScoreForUserId(String userId) {
        if(userRepository.findByUserId(userId) == null){
            return null;
        }
        User user = userRepository.findByUserId(userId);
        int score = user.getScore()+1;
        user.setScore(score);
        List<String> badges = user.getBadges();
        String title = null;

        if (score >= 1 && score < 30) {
            title = "Code Ninja";
        } else if (score >= 30 && score < 60) {
            title = "Code Champ";
        } else if (score >= 60 && score <= 100) {
            title = "Code Master";
        } else {
            return user;
        }

        if (title != null && !badges.contains(title)) {
            badges.add(title);
            user.setBadges(badges);
        }



        return userRepository.save(user);


    }

    public boolean delByUserId(String userId) {
        if(userRepository.findByUserId(userId) == null){
            return false;
        }
        userRepository.deleteById(userId);;
        return true;
    }
    
}
