package com.demo.ChatApplication.controller;

import com.demo.ChatApplication.model.User;
import com.demo.ChatApplication.model.UserLogin;
import com.demo.ChatApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLogin user) {
        return userService.login(user);
    }

    @PostMapping("/addFriend")
    public String addFriend(@RequestParam("user_id") UUID userId, @RequestParam("friend_username") String friendUsername){
        return userService.addFriend(userId, friendUsername);
    }

    //listOfFriends for logged in user
    @GetMapping("/friends")
    public List<User> GetFriendsByUserId(@RequestParam("user_id") UUID userId){
        return userService.getFriendsByUserId(userId);
    }

    //select one friend
    //start chat and send message
}
