package crio.codehack.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import crio.codehack.dto.Userdto;
import crio.codehack.exceptions.UserCreationException;
import crio.codehack.model.User;
import crio.codehack.repository.UserRepositary;

@Service
public class Userservice {
    
    @Autowired
    private UserRepositary userRepository;

    public void UserService(UserRepositary userRepository) {
        this.userRepository = userRepository;
    }

    //Insert the user details in db.
    public User createUser(Userdto userDto) {
        User user = new User(userDto.getUserId(), userDto.getUserName());
        // System.out.println("User saved: " + userRepository.insert(user));
        try {
            User ansUser = userRepository.insert(user);
            return ansUser; 
        } catch (Exception e) {
            throw new UserCreationException("Exception while creation of user, Try with another user id.", e);
        }
        
    }

    public Optional<User> getByUserId(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            return null;
        }

        return userRepository.findById(userId);

    }

    //Retrieve all user details based on decreasing scores.
    public List<User> getAllUser() {
        List<User> ans = userRepository.findAll();

        if(ans.isEmpty()){
            return new ArrayList<>();
        }
        Collections.sort(ans, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getScore(), u1.getScore());
            }
        });
        
        return ans;
    }

    //Update the score and badges for userid
    public User updateScoreForUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null){
            return null;
        }

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


        User ansUser = userRepository.save(user);

        return ansUser;


    }

    //Delete a user from db based on userid
    public boolean delByUserId(String userId) { 
        if(userRepository.findByUserId(userId) == null){
            return false;
        }
        userRepository.deleteById(userId);;
        return true;
    }

    
}
