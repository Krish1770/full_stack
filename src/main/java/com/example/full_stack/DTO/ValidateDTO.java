package com.example.full_stack.DTO;

import com.example.full_stack.Model.User;
import org.springframework.http.HttpStatus;

public class ValidateDTO {
    private String role;

    public ValidateDTO(HttpStatus httpStatus, String passwordChanged, String s) {
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    private Long  status_id;

    private  String email;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
         status_id=user.getStatus().getStatusId();
    }
}

