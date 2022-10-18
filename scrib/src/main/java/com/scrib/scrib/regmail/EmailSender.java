package com.scrib.scrib.regmail;

import javax.mail.MessagingException;

public interface EmailSender {

    void send(String to, String email) throws MessagingException;
}
