package com.example.full_stack.Controller;


import com.example.full_stack.Model.User;
import com.example.full_stack.Service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class userControllerTest {

    @InjectMocks
    private userController usercontroller;

    @Mock
    private UserService userService;

   @BeforeEach
   void setUp() {
       MockitoAnnotations.openMocks(this);

   }
    @Test
    public void addUser_Success()
    {
        User user=new User();
        when(userService.createUser(user)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"Successfully added","")));


        ResponseEntity<User> response=usercontroller.createUser(user);
        Assert.assertEquals("Added Successfully",response);

    }

    @Test
    public void addUser_Fail()
    {
        User user=new User();
        when(userService.createUser(user)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"Email alreedy exist","")));


        ResponseEntity<User> response=usercontroller.createUser(user);
        Assert.assertEquals("Email alreedy exist",response);
    }

    @Test
    public void getAllUser_Success()
    {
        Page<User> userList=null;
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<Page<User>> users= usercontroller.getAllUsers();
        Assert.assertEquals(userList,users.getBody());
    }

    @Test
    public void deleteUser_Success()
    {
          when(userService.deleteUser("karan@gmail.com")).thenReturn((ResponseEntity<User> ) ));

        ResponseEntity<User>  response =usercontroller.DeleteUser("karan@gmail.com");

          Assert.assertEquals("Successfully deleted",response);
    }

    @Test
    public  void  updateUser_Success()
    {
        User userUpdate=new User("ranga","1234566878","nayak","guru@gmail.com",
                new Date(13-02-2002),"1232323345","23a");
        User user = new User();
        user.setUser_id(1L);
        String updatedUser = "update successful";
        when(userService.UpdateUser(userUpdate)).thenReturn((ResponseEntity.status(HttpStatus.OK).body(new User(HttpStatus.NOT_FOUND,"Update Succesfully",""))));

  ResponseEntity<User>  response   = usercontroller.UpdateUser(userUpdate);

        Assert.assertEquals(updatedUser,response);
    }




}