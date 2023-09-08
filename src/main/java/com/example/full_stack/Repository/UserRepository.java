package com.example.full_stack.Repository;

import com.example.full_stack.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    void deleteByEmail(String Email);



}
