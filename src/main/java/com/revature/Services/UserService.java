package com.revature.Services;

import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.UserDTO;
import com.revature.models.DTOs.UserRegisterDTO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserDAO uDAO;
    @Autowired
    public UserService(UserDAO uDAO){
        this.uDAO=uDAO;
    }

   //Method to get all User
    public List<UserDTO> getAllUser(){
        List<User> allUsers=new ArrayList<>();
        List<UserDTO> allUserstoSend=new ArrayList<>();
        allUsers=uDAO.findAll();
        for(User u:allUsers){
            allUserstoSend.add(new UserDTO(u.getUserId(),u.getUserName(),u.getFirstName(),u.getLastName(),u.getRole()));
        }
        return allUserstoSend;
    }

    //mathod to create user
    public UserDTO registerUser(UserRegisterDTO newuser){
        if(newuser.getUserName().isBlank()||newuser.getFirstName().isBlank()){
            throw new IllegalArgumentException("Enter vaild details");
        }
        else{
        User u=new User(0, newuser.getFirstName(), newuser.getLastName(), newuser.getUserName(), newuser.getPassword(), "User");
        User newU= uDAO.save(u);
         UserDTO newOutUser=new UserDTO(newU.getUserId(),newU.getUserName(), newU.getFirstName(), newU.getLastName(),newU.getRole());
         return newOutUser;
       }
    }
    //Method to get user details by user_id
    public UserDTO getuserById(int userId){
        User u=uDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException("No user found with id: "+userId));
        UserDTO usertoSend=new UserDTO(u.getUserId(),u.getUserName(),u.getFirstName(),u.getLastName(),u.getRole());
        return usertoSend;
    }
    //method to delete user by user_id
    public String deleteUserById(int userId){
        User u=uDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException("No user found with id: "+userId));
         uDAO.deleteById(userId);
        Optional<User> deletedUser= uDAO.findById(userId);
        if(deletedUser.isEmpty()){
            return "User Id: "+userId+"deleted successfully";
        }else{
            return "not deleted";
        }
    }



}
