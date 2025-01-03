package com.demo.ChatApplication.service;

import ch.qos.logback.core.util.StringUtil;
import com.demo.ChatApplication.model.Friend;
import com.demo.ChatApplication.model.User;
import com.demo.ChatApplication.model.UserLogin;
import com.demo.ChatApplication.repository.FriendRepository;
import com.demo.ChatApplication.repository.UserRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    public String signUp(User user) {
        User user1 = userRepository.save(user);
        if (user1.getId() != null) {
            return "User Saved";
        }
        return "User Saved";
    }

    public User login(UserLogin user) {
        return userRepository.findByUsernameAndPassword(user.username(), user.password());
    }

    public String addFriend(UUID userId, String friendUsername) {
        // Retrieve the user to be added as a friend
        User friendUser = userRepository.findByUsername(friendUsername);

        // If the friend user does not exist, return an error message
        if (friendUser == null) {
            return "Friend not found";
        }

        // Retrieve the user who is adding the friend
        User user = userRepository.findById(userId).orElse(null);

        // If the main user does not exist, return an error message
        if (user == null) {
            return "User not found";
        }

        // Create a new Friend object
        Friend friend = Friend.builder()
                .friend(friendUser)
                .user(user)
                //.status("pending")
                .build();

        // Save the friend relationship and check if it was successful
        Friend savedFriend = friendRepository.save(friend);

        // Return the appropriate message based on the result
        if (savedFriend != null) {
            return "Friend Added";
        }

        return "Friend Not Added";
    }

    public List<User> getFriendsByUserId(UUID userId) {
        // Retrieve the user who is adding the friend
        User user = userRepository.findById(userId).orElse(null);

        // If the main user does not exist, return an error message
        if (user == null) {
            return new ArrayList<>();
        }

        List<Friend> friends = user.getFriends();
        List<User> friendsByUser = friends.stream()
                .filter(friend -> "accepted".equals(friend.getStatus()))
                .map(friend -> friend.getFriend()).toList();
        return friendsByUser;
    }

}
