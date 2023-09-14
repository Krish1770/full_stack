package com.example.full_stack.Controller;


//import DTO.UserUpdateDTO;

import com.example.full_stack.DTO.LoginDTO;
import com.example.full_stack.DTO.LoginResponseDTO;
import com.example.full_stack.DTO.ValidateDTO;
import com.example.full_stack.Model.User;
import com.example.full_stack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;





    @PostMapping("/createUser")
    public  ResponseEntity<User> createUser(@RequestBody User user)
    {

            return userService.createUser(user);

    }

    @GetMapping("/getUsers")
    public ResponseEntity<Page<User>> getAllUsers()
    {
        Page<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

//    @GetMapping("/{email_id}")
//    public ResponseEntity<User> getUserById(@PathVariable String email)
//    {
//        return userService.getUserById(email);
//    }

    @PutMapping("/update/{email}")
    public ResponseEntity<User> UpdateUser(@RequestBody User user)
    {

        return userService.UpdateUser(user);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<User> DeleteUser(@PathVariable String email)
    {

       return userService.deleteUser(email);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> CheckLogin(@RequestBody LoginDTO loginDTO)
    {

         return userService.CheckLogin(loginDTO);
    }

  @GetMapping("/forgotpassword/{email}")
    public ResponseEntity<User> getEmailOfUser(@PathVariable String email)
  {
      System.out.println(email);
      return userService.getEmailOfUser(email);
  }

  @PostMapping("/newPassword")

    public ResponseEntity<ValidateDTO> changePassword(@RequestBody LoginDTO loginDTO)
  {
       return userService.changePassword(loginDTO);
  }

  @PutMapping("/updateStatus/{email}")

    public ResponseEntity<ValidateDTO>  updateStatus(@PathVariable("email") String email)
  {
      return userService.updateStatus(email);
  }

}
