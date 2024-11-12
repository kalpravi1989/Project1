package com.revature.models.DTOs;

import com.revature.models.User;

public class OutgoingReimbManager {
     private int reimbId;
     private String description;
     private int amount;
     private String status;
     private String userName;

    public OutgoingReimbManager() {
    }

    public OutgoingReimbManager(int reimbId, String description, int amount, String status, String userName) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userName = userName;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "OutgoingReimbManager{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
