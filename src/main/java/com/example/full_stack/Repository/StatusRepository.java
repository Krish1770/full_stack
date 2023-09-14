package com.example.full_stack.Repository;

import com.example.full_stack.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
    Status findByStatusId(Long statusId);
}
