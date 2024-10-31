package com.revature.Services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {

    private ReimbursementDAO rDAO;
    private UserDAO uDAO;
    @Autowired
    public ReimbursementService(ReimbursementDAO rDAO,UserDAO uDAO){
        this.rDAO=rDAO;
        this.uDAO=uDAO;
    }
    public List<Reimbursement> getAllReimbursement(){
        return rDAO.findAll();
    }
    public Reimbursement getAllReimbursementByReimbId(int reimbId){
        Reimbursement r=rDAO.findById(reimbId).orElseThrow(()->
            new IllegalArgumentException("No reimb Id found "+reimbId)
        );
        return rDAO.findByreimbId(reimbId);
    }

    public List<Reimbursement>getAllReimbByUserId(int userId){
        User u=uDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException("No user found with id: "+userId));
        return rDAO.findByUser_UserId(userId);
    }
    public List<Reimbursement>getAllReimbBystatus(String status){
       return  rDAO.findBystatus(status);
    }
    public List<Reimbursement>getAllReimbByStatusAndUserId(String status,int userId){
        User u=uDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException("No user found with id: "+userId));
        return rDAO.findBystatusAndUser_UserId(status,userId);
    }
    public Reimbursement addReimbursement(IncomingReimbursementDTO rDTO){
     Reimbursement newReimbursement=new Reimbursement(0,rDTO.getDescription(),rDTO.getAmount(),"Pending",null, rDTO.getReason(),null);
        Optional<User> u=uDAO.findById(rDTO.getUserId());
        if(u.isEmpty()){
            throw new IllegalArgumentException("No user with id: "+rDTO.getUserId());
        }else{
            newReimbursement.setUser(u.get());
            return rDAO.save(newReimbursement);
        }
    }
    public Reimbursement updateStatusById(int reimbId,String status ){
        Reimbursement r=rDAO.findById(reimbId).orElseThrow(()->
                new IllegalArgumentException("No reimbId with id: "+reimbId));


        System.out.println(r);
        r.setStatus(status);
        return rDAO.save(r);
    }


}
