package com.revature.Aspects;

import com.revature.Controllers.LoginController;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginAspect {

    @Before("execution(* com.revature.Controllers.UserController.*(..))"+"&& !execution(* com.revature.Controllers.UserController.createUser(..))")
    public void checkLogin()  {
        System.out.println(LoginController.session);
        if (LoginController.session == null) {
            throw new SessionNotFoundException("Session not found");

        }
    }

    @Before("@annotation(com.revature.Aspects.ManagerOnly)")
    public void checkManager() throws IllegalArgumentException{

        if(!LoginController.session.getAttribute("role").equals("Manager")){
            throw new IllegalArgumentException("You must be a Manager to do this");
        }

    }



}
