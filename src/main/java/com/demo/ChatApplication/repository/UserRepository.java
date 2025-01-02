package com.demo.ChatApplication.repository;

import com.demo.ChatApplication.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);


}
