package com.example.full_stack.Service;

//import DTO.UserUpdateDTO;
import com.example.full_stack.DTO.LoginDTO;
import com.example.full_stack.DTO.LoginResponseDTO;
//import com.example.full_stack.DTO.ValidateDTO;
import com.example.full_stack.DTO.ValidateDTO;
import com.example.full_stack.Model.User;
import com.example.full_stack.Repository.StatusRepository;
import com.example.full_stack.Repository.UserRepository;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    private StatusRepository statusRepository;

    @Autowired
    private JavaMailSender mailSender;

    public ResponseEntity<User> createUser(User user) {

        User newUser=new User();
        if(userRepository.findByEmail(user.getEmail())==null) {
            user.setUpdateDate(new Date());
            user.setRole("user");
            user.setStatus(statusRepository.findByStatusId(1L));
            userRepository.save(user);

            String link="http://localhost:3000/accountverification?"+user.getEmail();
            System.out.println("Hi");

            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("rameshkrishnaprasath@gmail.com");
            message.setTo(user.getEmail());
//            message.setText("unless");
            message.setText(link);
            message.setSubject("Account Verification!!!");


            mailSender.send(message);
            return ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"User Added Successfully",""));

        }

      return ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"Email alreedy exist",""));


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

    public ResponseEntity<User> UpdateUser(User user) {


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
            return(ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"Updated Succesfully","")));

        }
        return(ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"email not found","")));


    }

    public ResponseEntity<User> deleteUser(String email)
    {
        User user = userRepository.findByEmail(email);

        if(user==null)
            return(ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"User not found","")));

        else {
            return(ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"Update Succesfully","")));

        }
    }

    public ResponseEntity<LoginResponseDTO> CheckLogin(LoginDTO loginDTO) {
        User newUser=(userRepository.findByEmail(loginDTO.getEmail()));
        ValidateDTO tempUser ;
        System.out.println(newUser.toString());
        if(newUser==null)
        {
               return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(HttpStatus.NOT_FOUND,"User not found",""));
        }
        else
        {
            if(loginDTO.getPassword().equals(newUser.getPassword()))
            {
                tempUser=new ValidateDTO(newUser);
                System.out.println("gedf"+tempUser.toString());
                  if(((newUser.getStatus()).getStatusId()).equals(1L))
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

    public ResponseEntity<User> getEmailOfUser(String email) {

        String link="http://localhost:3000/forgot?"+email;
        System.out.println("Hi");

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("rameshkrishnaprasath@gmail.com");
        message.setTo(email);
        message.setText(link);
        message.setSubject("Reset Link!!!");


        mailSender.send(message);

        return(ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"Reset email link","")));





    }

    public ResponseEntity<ValidateDTO> changePassword(LoginDTO loginDTO) {

           User newUser=(userRepository.findByEmail(loginDTO.getEmail()));
        System.out.println(newUser.toString());

        if(newUser!=null)
           {
               System.out.println("yes");
               newUser.setPassword((loginDTO.getPassword()));
                userRepository.save(newUser);
               return(ResponseEntity.status(HttpStatus.OK).body(new ValidateDTO(HttpStatus.OK,"Password changed","")));

           }
        return(ResponseEntity.status(HttpStatus.OK).body(new ValidateDTO(HttpStatus.OK,"Password not changed","")));



    }

    public ResponseEntity<ValidateDTO> updateStatus(String email)
    {
        User newUser =userRepository.findByEmail(email);
        System.out.println("Hi 1 "+email);
        if(newUser!=null)
        {
            System.out.println(newUser.toString());
            newUser.setStatus(statusRepository.findByStatusId(2L));
            userRepository.save(newUser);

            return(ResponseEntity.status(HttpStatus.OK).body(new ValidateDTO(HttpStatus.OK,"Status changed","")));

        }

        return(ResponseEntity.status(HttpStatus.OK).body(new ValidateDTO(HttpStatus.OK,"Status not updated","")));
    }
}
