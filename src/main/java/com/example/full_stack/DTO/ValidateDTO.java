package com.example.full_stack.DTO;

import com.example.full_stack.Model.User;

public class ValidateDTO {
    private String role;

    private String  status;

    private  String email;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ValidateDTO(User user)
    {
         role= user.getRole();
         email= user.getEmail();
         status=user.getStatus();
    }
}

