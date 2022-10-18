package com.scrib.scrib.registration;

import com.scrib.scrib.appuser.ApplicationUser;
import com.scrib.scrib.exception.domain.EmailExistException;
import com.scrib.scrib.exception.domain.UserNameExistsException;
import com.scrib.scrib.exception.domain.UserNotFoundException;
import com.scrib.scrib.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/register"})
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    private ConfirmationTokenService confirmationTokenService;

    @PostMapping(path = "/user")
    public ResponseEntity<ApplicationUser> register(
            @RequestBody RegistrationRequest applicationUser)
            throws UserNotFoundException, EmailExistException, UserNameExistsException, MessagingException {

        ApplicationUser newlyRegisteredApplicationUser=
                registrationService
                        .requestRegister(applicationUser);

        return new ResponseEntity<ApplicationUser>(newlyRegisteredApplicationUser, OK);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirm(token);
    }

}
