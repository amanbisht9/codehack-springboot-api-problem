package crio.codehack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crio.codehack.dto.Userdto;
import crio.codehack.model.User;
import crio.codehack.service.Userservice;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Userservice userService;

    public UserController(Userservice userService) {
        this.userService = userService;
    }
    //POST controller to add a User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Userdto userDto) {
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    //GET controller to get the user details by id
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getByUserId(@PathVariable String userId) {
        Optional<User> retrivedUser = userService.getByUserId(userId);
        if(retrivedUser == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedUser);
    }

    //GET controller to get the details of all user sorted based on score
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> retrivedUsers = userService.getAllUser();
        if(retrivedUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedUsers);
    }

    //PUT controller to update the user score based on userid (oldScore+1)
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateScoreForUserId(@PathVariable String userId) {
        User retrivedUser = userService.updateScoreForUserId(userId);
        if(retrivedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrivedUser);
    }

    //DELETE controller to delete the user based on userid
    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> delByUserId(@PathVariable String userId) {
        boolean retrivedUser = userService.delByUserId(userId);
        if(retrivedUser == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
