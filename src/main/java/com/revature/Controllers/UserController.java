package com.revature.Controllers;

import com.revature.Aspects.ManagerOnly;
import com.revature.Services.UserService;
import com.revature.models.DTOs.UserDTO;
import com.revature.models.DTOs.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService=userService;

    }

    @ManagerOnly
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> allUser=userService.getAllUser();
        System.out.println(allUser);
        return ResponseEntity.status(200).body(allUser);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId){
        return ResponseEntity.status(200).body(userService.getuserById(userId));
    }

    @ManagerOnly
    @DeleteMapping("/{userId}")
    public ResponseEntity<String>deleteUserById(@PathVariable int userId){
       return ResponseEntity.status(204).body(userService.deleteUserById(userId));
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRegisterDTO newuser){

        UserDTO user=userService.registerUser(newuser);

        return ResponseEntity.status(201).body(user);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
