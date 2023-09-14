package com.example.full_stack.Service;

//import DTO.UserUpdateDTO;
import com.example.full_stack.Model.User;
import com.example.full_stack.Repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private  UserService userservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void user_Save_Success()
    {
        User user = new User();

        when(userRepository.findByEmail(anyString())).thenReturn(null);

        when(userRepository.save(any())).thenReturn(user);

        String savedResult=userservice.createUser(user);

        assertEquals("Added Successfully",savedResult);

    }

    @Test
    public void user_Save_fail()
    {
        User user = new User("krish","kumar","wer@gmail.com",new Date(2001-11-11),"134567878","23A,gandhi nagar");


        when(userRepository.findByEmail(anyString())).thenReturn(user);


        String savedResult=userservice.createUser(user);

        assertEquals("given email id already exist",savedResult);

    }

    @Test

    public void getUsers_Success()
    {
        User user = new User();
        user.setUpdateDate(new Date(2023-01-05));
        User user1 = new User();
        user1.setUpdateDate(new Date(2023-01-06));
        List<User> users = Arrays.asList(user1,user);
        Pageable pageable = PageRequest.of(0, 10);

        Page<User> userList = new PageImpl<User>(users, pageable, users.size());

        when(userRepository.findAll(PageRequest.of(0,10, Sort.by("updateDate").ascending()))).thenReturn(userList);
        Page<User> allUsers=userservice.getAllUsers();


        Assert.assertNotNull(allUsers);
        Assert.assertEquals(user1,allUsers.get().toList().get(0));
    }

    @Test
    public void updateUser_Success()
    {
        User user=new User("ranga","nayak","132567543,"
                ,"23a,khana nagar",new Date(13-02-2002),7L);
        User User
                = new User();
        user.setUser_id(1L);
        ResponseEntity<User> updatedUser = ResponseEntity.ok(user);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userservice.UpdateUser(user);

        Assert.assertEquals(updatedUser.getBody(),response.getBody());
    }

    @Test
    public void deleteUser_fail()
    {

        when(userRepository.findByEmail("guru@gmail.com")).thenReturn(null);
        ResponseEntity<User> response=userservice.deleteUser("guru@gmail.com");
        Assert.assertEquals("user not found",response);
    }


    @Test
    public void deleteUser_Success()
    {

        User user=new User("sadap","khan","khan@gmail.com",new Date(2001-11-11 ) ,"8765432234","1,Ram Street,Bangalore");
        when(userRepository.findByEmail("khan@gmail.com")).thenReturn(user);
        ResponseEntity<User> response=userservice.deleteUser("khan@gmail.com");
        Assert.assertEquals("Successfully deleted",response);
    }






}