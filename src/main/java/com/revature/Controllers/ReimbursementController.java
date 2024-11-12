package com.revature.Controllers;

import com.revature.Aspects.ManagerOnly;
import com.revature.Services.ReimbursementService;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.DTOs.OutgoingReimbManager;
import com.revature.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimb")
@CrossOrigin
public class ReimbursementController {

    ReimbursementService rService;

    @Autowired
    public ReimbursementController(ReimbursementService rService) {
        this.rService = rService;
    }

    @ManagerOnly
    @GetMapping
    public ResponseEntity<List<OutgoingReimbManager>> getAllReimbursement(){
        return ResponseEntity.ok(rService.getAllReimbursement());
    }
    @GetMapping("/{reimbId}")
    public ResponseEntity<Reimbursement> getAllReimbursementByUserId(@PathVariable int reimbId){
        return ResponseEntity.ok(rService.getAllReimbursementByReimbId(reimbId));
    }
    @PostMapping("{userId}")
    public ResponseEntity<Reimbursement> createReimbursement(@RequestBody IncomingReimbursementDTO rDTO,@PathVariable int userId){
        return ResponseEntity.status(201).body(rService.addReimbursement(rDTO,userId));
    }
    @ManagerOnly
    @PatchMapping("/{id}")
    public ResponseEntity<Reimbursement> updateStatus(@PathVariable int id,@RequestBody String status){
        return ResponseEntity.status(201).body(rService.updateStatusById(id,status));
    }
    @ManagerOnly
    @GetMapping("/status")
    public ResponseEntity<List<OutgoingReimbManager>>getAllReimbByStatus(@RequestParam  String status){

        return ResponseEntity.ok(rService.getAllReimbBystatus(status));
    }

    @PatchMapping("/description/{id}")
    public ResponseEntity<Reimbursement> updateDescription(@PathVariable int id,@RequestBody String description){
        return ResponseEntity.status(200).body(rService.updateDescrptionById(id,description));
    }
    @GetMapping("/users")
    public ResponseEntity<List<OutgoingReimbManager>>getAllReimbByStatusAndUserId(@RequestParam  String status,@RequestParam int userId){

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
