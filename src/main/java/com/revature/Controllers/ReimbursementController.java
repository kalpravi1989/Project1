package com.revature.Controllers;

import com.revature.Services.ReimbursementService;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimb")
public class ReimbursementController {

    ReimbursementService rService;

    @Autowired
    public ReimbursementController(ReimbursementService rService) {
        this.rService = rService;
    }

    @GetMapping
    public ResponseEntity<List<Reimbursement>> getAllReimbursement(){
        return ResponseEntity.ok(rService.getAllReimbursement());
    }
    @GetMapping("/{reimbId}")
    public ResponseEntity<Reimbursement> getAllReimbursementByUserId(@PathVariable int reimbId){
        return ResponseEntity.ok(rService.getAllReimbursementByReimbId(reimbId));
    }
    @PostMapping
    public ResponseEntity<Reimbursement> createReimbursement(@RequestBody IncomingReimbursementDTO rDTO){
        return ResponseEntity.status(201).body(rService.addReimbursement(rDTO));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Reimbursement> updateStatus(@PathVariable int id,@RequestBody String status){
        return ResponseEntity.status(201).body(rService.updateStatusById(id,status));
    }
    @GetMapping("/status")
    public ResponseEntity<List<Reimbursement>>getAllReimbByStatus(@RequestParam  String status){
        System.out.println(status);
        return ResponseEntity.ok(rService.getAllReimbBystatus(status));
    } @GetMapping("/users")
    public ResponseEntity<List<Reimbursement>>getAllReimbByStatusAndUserId(@RequestParam  String status,@RequestParam int userId){
        System.out.println(status);
        return ResponseEntity.ok(rService.getAllReimbByStatusAndUserId(status,userId));
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Reimbursement>>getReimbByuserId(@PathVariable int userId){
        return ResponseEntity.ok(rService.getAllReimbByUserId(userId));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
