package com.revature.Services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.DTOs.OutgoingReimbManager;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<OutgoingReimbManager> getAllReimbursement(){
        List<Reimbursement> allReimb=rDAO.findAll();
        List<OutgoingReimbManager> outReimb=new ArrayList<>();
        for(Reimbursement reimb:allReimb){
            outReimb.add(new OutgoingReimbManager(reimb.getReimbId(),reimb.getDescription(),reimb.getAmount(),reimb.getStatus(),reimb.getUser().getUserName()));
        }
        return outReimb;
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
    public List<OutgoingReimbManager>getAllReimbBystatus(String status){
        List<Reimbursement> allReimb=rDAO.findBystatus(status);
        List<OutgoingReimbManager> outReimb=new ArrayList<>();
        for(Reimbursement reimb:allReimb){
            outReimb.add(new OutgoingReimbManager(reimb.getReimbId(),reimb.getDescription(),reimb.getAmount(),reimb.getStatus(),reimb.getUser().getUserName()));
        }
        return outReimb;

    }
    public List<OutgoingReimbManager>getAllReimbByStatusAndUserId(String status,int userId){
        User u=uDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException("No user found with id: "+userId));
        List<Reimbursement>  allReimb= rDAO.findBystatusAndUser_UserId(status,userId);
        List<OutgoingReimbManager> outReimb=new ArrayList<>();
        for(Reimbursement reimb:allReimb){
            outReimb.add(new OutgoingReimbManager(reimb.getReimbId(),reimb.getDescription(),reimb.getAmount(),reimb.getStatus(),reimb.getUser().getUserName()));
        }
        return outReimb;

    }
    public Reimbursement addReimbursement(IncomingReimbursementDTO rDTO,int userId){

        if(rDTO.getDescription().isBlank()||rDTO.getAmount()<=0||rDTO.getReason().isBlank()){
           throw new IllegalArgumentException("Enter vaild details");
        }
        Reimbursement newReimbursement=new Reimbursement(0,rDTO.getDescription(),rDTO.getAmount(),"Pending",null, rDTO.getReason(),null);
        Optional<User> u=uDAO.findById(userId);
        if(u.isEmpty()){
            throw new IllegalArgumentException("No user with id: "+userId);
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
    public Reimbursement updateDescrptionById(int reimbId,String describtion ){
        Reimbursement r=rDAO.findById(reimbId).orElseThrow(()->
                new IllegalArgumentException("No reimbId with id: "+reimbId));


        System.out.println(r);
        r.setDescription(describtion);
        return rDAO.save(r);
    }


}
