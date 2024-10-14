package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    //User Story 8
    List<Message> findByPostedBy(int poster_id);

    //Built-In User Stories: User Story 3, User Story 4, User Story 5, User Story 6, User Story 7, 
}
