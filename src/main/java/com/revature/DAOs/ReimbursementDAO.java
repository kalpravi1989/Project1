package com.revature.DAOs;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {

   Reimbursement findByreimbId(int reimbId);
   List<Reimbursement> findByUser_UserId(int user_id);
   List<Reimbursement>findBystatusAndUser_UserId(String status,int user_id);
   List<Reimbursement>findBystatus(String status);

}
