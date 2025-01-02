package com.demo.ChatApplication.repository;

import com.demo.ChatApplication.model.Friend;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface FriendRepository extends CrudRepository<Friend, UUID> {

}
