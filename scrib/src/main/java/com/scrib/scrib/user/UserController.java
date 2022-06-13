package com.scrib.scrib.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService user) {
        this.userService = user;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUser();
    }

    @PostMapping(path = "registration")
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long authorId){
        userService.deleteUser(authorId);
    }

    @PutMapping(path = "{userId}")
    public void updateUserInfo(
            @PathVariable("userId") Long userId
            ,@RequestParam(required= false) String firstName
            ,@RequestParam(required = false) String email
            ,@RequestParam(required = false) String description){

        userService.modifyUserInformation(userId,firstName, email,description);
    }

}
