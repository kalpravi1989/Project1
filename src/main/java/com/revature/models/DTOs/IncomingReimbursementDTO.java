package com.revature.models.DTOs;

public class IncomingReimbursementDTO {


    String description;
    int amount;
    String reason;


    public IncomingReimbursementDTO() {
    }

    public IncomingReimbursementDTO(String description, int amount, String reason) {
        this.description = description;
        this.amount = amount;
        this.reason = reason;

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



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }




    @Override
    public String toString() {
        return "IncomingReimbursementDTO{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +

                '}';
    }
}
