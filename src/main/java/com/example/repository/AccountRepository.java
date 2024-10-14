package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    //User Story 1
    Optional<Account> findByUsername(String username);

    //User Story 2
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
