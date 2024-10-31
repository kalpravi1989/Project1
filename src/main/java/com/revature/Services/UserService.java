package com.revature.Services;

import com.revature.DAOs.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserDAO uDAO;
    @Autowired
    public UserService(UserDAO uDAO){
        this.uDAO=uDAO;
    }


    public List<User> getAllUser(){
        return uDAO.findAll();
    }

    public User registerUser(User user){
        return uDAO.save(user);

    }
    public Optional<User> getuserById(int userId){
        User u=uDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException("No user found with id: "+userId));
        return uDAO.findById(userId);
    }
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
