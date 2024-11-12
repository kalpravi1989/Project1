package com.revature.Services;

import com.revature.Controllers.LoginController;
import com.revature.DAOs.LoginDAO;
import com.revature.models.DTOs.UserDTO;
import com.revature.models.DTOs.UserLoginDTO;
import com.revature.models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

 LoginDAO lDAO;

    @Autowired
    public LoginService(LoginDAO lDAO) {
        this.lDAO = lDAO;
    }

    public UserDTO loginService(UserLoginDTO uDTO, HttpSession session){

        User u=lDAO.findByUserNameAndPassword(uDTO.getUserName(),uDTO.getPassword());
        if(u==null){
            throw new IllegalArgumentException("No user found with those credentials!");
        }
        UserDTO oUser=new UserDTO(u.getUserId(),u.getUserName(),u.getFirstName(),u.getLastName(),u.getRole());
        LoginController.session=session;
        session.setAttribute("userId",u.getUserId());
        session.setAttribute("userName",u.getUserName());
        session.setAttribute("firstName",u.getFirstName());
        session.setAttribute("role",u.getRole());

        return oUser;
    }
}
