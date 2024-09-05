package crio.codehack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody Userdto userDto) {
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser.toString());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getByUserId(@PathVariable String userId) {
        User retrivedUser = userService.getByUserId(userId);
        if(retrivedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Error : User not found}");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedUser.toString());
    }

    @GetMapping
    public ResponseEntity<String> getAllUser() {
        List<User> retrivedUsers = userService.getAllUser();
        if(retrivedUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Error : No user registered}");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(retrivedUsers.toString());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateScoreForUserId(@PathVariable String userId) {
        User retrivedUser = userService.updateScoreForUserId(userId);
        if(retrivedUser == null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("{Error : User not foud for updating the score}");
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrivedUser.toString());
    }


    // @RequestMapping(value="/{userId}", method={RequestMethod.DELETE, RequestMethod.GET})
    // public ResponseEntity<String> delByUserId(@PathVariable String userId) {
    //     boolean retrivedUser = userService.delByUserId(userId);
    //     if(retrivedUser == false){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Error : User not found}");
    //     }

    //     return ResponseEntity.status(HttpStatus.OK).body("{Status : User deletion success}");
    // }
}
