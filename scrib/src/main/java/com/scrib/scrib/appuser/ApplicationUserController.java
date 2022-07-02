package com.scrib.scrib.appuser;

import com.scrib.scrib.exception.ExceptionHandling;
import com.scrib.scrib.exception.domain.EmailExistException;
import com.scrib.scrib.exception.domain.UserNameExistsException;
import com.scrib.scrib.exception.domain.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController
@AllArgsConstructor
@RequestMapping(path = {"/","/user"})
public class ApplicationUserController extends ExceptionHandling {



    private ApplicationUserService applicationUserService;

    @PostMapping(path = "/register")
    public ResponseEntity<ApplicationUser> register(
            @RequestBody ApplicationUser applicationUser)
            throws UserNotFoundException, EmailExistException, UserNameExistsException {

            ApplicationUser newlyRegistredApplicationUser=applicationUserService
                    .register(applicationUser.getFirstName()
            ,applicationUser.getLastName()
            ,applicationUser.getUserName()
            ,applicationUser.getEmail());

            return new ResponseEntity<ApplicationUser>(newlyRegistredApplicationUser, OK);
    }

    @GetMapping (path = "/fetching")
    public ApplicationUser fetchUser(){
        ApplicationUser u=applicationUserService.findApplicationUserByEmail("sdfsdfs@gmail.com");
        System.out.println(u.toString());
        return u;
    }


}
