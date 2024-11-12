package com.revature.Controllers;

import com.revature.Services.LoginService;
import com.revature.models.DTOs.UserDTO;
import com.revature.models.DTOs.UserLoginDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    LoginService lservice;
    public static HttpSession session;
    @Autowired
    public LoginController(LoginService lservice) {
        this.lservice = lservice;
    }

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody UserLoginDTO uDTO, HttpSession session){
        System.out.println(uDTO);
        UserDTO ouser=lservice.loginService(uDTO,session);

        return ResponseEntity.ok(ouser);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
