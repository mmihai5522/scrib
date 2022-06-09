package com.scrib.scrib.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository ur;

    @Autowired
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public List<User> getUser(){
        return ur.findAll();
    }

    public void addNewUser(User user) {

        Optional<User> userOptional = ur.findByEmail(user.getEmail());
        if (userOptional.isPresent()){
            throw new IllegalStateException("Email is Invalid for some reason!");
        }
        ur.save(user);

    }

    public void deleteUser(Long userId) {

        boolean isPresent= ur.existsById(userId);

        if (!isPresent){
            throw new IllegalStateException("User with id: " +userId + " does not exists!");
        }

        ur.deleteById(userId);

    }

    @Transactional
    public void modifyUserInformation(Long userId
            , String firstName
            , String email
            , String description) {

        User user=ur.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id: "+ userId+" does not exist"
                ));

        if (firstName != null && firstName.length() > 0 &&
            !Objects.equals(user.getFirstName(),firstName)){
            user.setFirstName(firstName);
        }

        if (email != null && email.length() > 0 &&
            !Objects.equals(user.getEmail(),email)){
            Optional<User> userFoundByEmail=ur.findByEmail(email);
            if (userFoundByEmail.isPresent()){
                    throw new IllegalStateException(
                            "User with id: "+ userId+" does not exist" );
                    }
            user.setEmail(email);
            }

        if (description != null && description.length() > 0 &&
                !Objects.equals(user.getDescription(),description)){
            user.setDescription(description);
        }

        }




}
