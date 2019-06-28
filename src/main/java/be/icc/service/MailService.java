package be.icc.service;

import be.icc.dto.UserDto;

/**
 * Created by Scohier Dorian on 14-03-19.
 */
public interface MailService {

    void sendConfirmationSignUpEmail(UserDto user);

    void sendMessage(String mailTo, String content, String userFrom, String mailFrom, String productTitle);

}
