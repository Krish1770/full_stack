package com.example.full_stack.Service;

//import DTO.UserUpdateDTO;
import com.example.full_stack.DTO.LoginDTO;
import com.example.full_stack.DTO.LoginResponseDTO;
import com.example.full_stack.DTO.ValidateDTO;
import com.example.full_stack.Model.User;
import com.example.full_stack.Repository.UserRepository;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;
    public String createUser(User user) {

        if(userRepository.findByEmail(user.getEmail())==null) {
            user.setUpdateDate(new Date());
            user.setRole("user");
            user.setStatus("registered");
            userRepository.save(user);

            return "Added Successfully";
            }

        return "given email id already exist";

    }

    public Page<User> getAllUsers()
    {
         return userRepository.findAll(
                 PageRequest.of(0,10,Sort.by("updateDate").descending()));
    }

//    public ResponseEntity<User> getUserById(Long user_id)
//    {
//        User user = userRepository.findById(user_id).get();
//
//        return ResponseEntity.ok(user);
//    }

    public String UpdateUser(User user) {


        User updatedUser=userRepository.findByEmail(user.getEmail());
        System.out.println(updatedUser);
        if(updatedUser!=null) {

            System.out.println(updatedUser);
            updatedUser.setFirst_name(user.getFirst_name());
            updatedUser.setLast_name(user.getLast_name());
            updatedUser.setAddress(user.getAddress());
            updatedUser.setDate_of_birth(user.getDate_of_birth());
            updatedUser.setPhone_number(user.getPhone_number());
            updatedUser.setUpdateDate(new Date());
            userRepository.save(updatedUser);
            return "update successful";
        }
        return "email not found";


    }

    public String deleteUser(String email)
    {
        User user = userRepository.findByEmail(email);

        if(user==null)
         return "user not found";

        else {
            userRepository.delete(user);
           return "Successfully deleted";
        }
    }

    public ResponseEntity<LoginResponseDTO> CheckLogin(LoginDTO loginDTO) {
        User newUser=(userRepository.findByEmail(loginDTO.getEmail()));
        ValidateDTO tempUser ;
        if(newUser==null)
        {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponseDTO(HttpStatus.NOT_FOUND,"User not found",""));
        }
        else
        {
            if(loginDTO.getPassword().equals(newUser.getPassword()))
            {
                tempUser=new ValidateDTO(newUser);
                  if(newUser.getStatus().equals("verified"))
                  {
                      System.out.println(tempUser.getEmail());

                  return  ResponseEntity.status( HttpStatus.OK).body(new LoginResponseDTO(HttpStatus.OK,"verified user",tempUser));
                  }
                  else{
                      return  ResponseEntity.status( HttpStatus.OK).body(new LoginResponseDTO(HttpStatus.OK,"verified user",tempUser));
                  }

            }
            else
                return  ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(HttpStatus.OK,"invalid password",""));
        }
    }

    public void getEmailOfUser(String email) {

        String link="http://localhost:3000/forgot";
        System.out.println("Hi");
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("rameshkrishnaprasath@gmail.com");
        message.setTo(email);
        message.setText(link);
        message.setSubject("Reset Link!!!");


        mailSender.send(message);


    }
}
