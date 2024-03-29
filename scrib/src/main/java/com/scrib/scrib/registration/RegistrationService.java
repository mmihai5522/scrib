package com.scrib.scrib.registration;

import com.scrib.scrib.appuser.ApplicationUser;
import com.scrib.scrib.appuser.ApplicationUserPrincipal;
import com.scrib.scrib.appuser.ApplicationUserService;
import com.scrib.scrib.exception.domain.EmailExistException;
import com.scrib.scrib.exception.domain.UserNameExistsException;
import com.scrib.scrib.exception.domain.UserNotFoundException;
import com.scrib.scrib.registration.token.ConfirmationToken;
import com.scrib.scrib.registration.token.ConfirmationTokenService;
import com.scrib.scrib.regmail.EmailSender;
import com.scrib.scrib.utility.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final ApplicationUserService applicationUserService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;
    private JwtProvider jwtProvider;



    public ApplicationUser requestRegister(RegistrationRequest registrationRequest) throws UserNotFoundException, EmailExistException, MessagingException, UserNameExistsException {

        boolean isValid = emailValidator.test(registrationRequest.getEmail());
        if (!isValid) {
            throw new IllegalStateException("Not a valid Mail!");
        }

        applicationUserService.usersRegister(registrationRequest.getFirstName()
        ,registrationRequest.getLastName(),registrationRequest.getUserName()
                , registrationRequest.getEmail(), registrationRequest.getPassword());

        ApplicationUser fetcheduser=applicationUserService
                .findApplicationUserByEmail(registrationRequest.getEmail());

        ApplicationUserPrincipal up=new ApplicationUserPrincipal(fetcheduser);

        //TODO: schimabre headers cu un alt obiect de preferat token (modific token in string din CT)
        String token=jwtProvider.generateJwtToken(up);
        ConfirmationToken ct=new ConfirmationToken(
                token
                , LocalDateTime.now()
                ,LocalDateTime.now().plusMinutes(15)
                ,fetcheduser
        );
        confirmationTokenService.saveToken(ct);

//        ApplicationUserPrincipal up=new ApplicationUserPrincipal(fetcheduser);
//        HttpHeaders jwtHeader=getJwtHeader(up);
//        ConfirmationToken ct=new ConfirmationToken(
//                jwtHeader
//                , LocalDateTime.now()
//                ,LocalDateTime.now().plusMinutes(15)
//                ,fetcheduser
//        );
//        confirmationTokenService.saveToken(ct);



        String httpToken= token;

        String link="http://localhost:8080/register/confirm?token="+httpToken;

        emailSender.send(registrationRequest.getEmail()
                , buildEmail(registrationRequest.getFirstName()
                        , link));
        return fetcheduser;
    }

    @Transactional
    public String confirm(String token) {

        ConfirmationToken confirmationToken=confirmationTokenService
                .getToken(token)
                .orElseThrow(
                        () -> new IllegalStateException("Not found!")
                );
        if (confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("Email already confirmed!");
        }
        LocalDateTime expired = confirmationToken.getExpiresAt();

        if (expired.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Expired!");
        }

        applicationUserService.enableAppUser(confirmationToken.getApplicationUser().getEmail());

    return "confirmed";
    }


    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}


