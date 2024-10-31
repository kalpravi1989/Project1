package com.revature.Controllers;

import com.revature.Services.ReimbursementService;
import com.revature.Services.UserService;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;

    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser=userService.getAllUser();
        System.out.println(allUser);
        return ResponseEntity.status(200).body(allUser);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable int userId){
        return ResponseEntity.status(200).body(userService.getuserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String>deleteUserById(@PathVariable int userId){
       return ResponseEntity.status(204).body(userService.deleteUserById(userId));
    }
    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User newuser){

        User user=userService.registerUser(newuser);
        System.out.println(user);
        return ResponseEntity.ok(user);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
