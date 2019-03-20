package be.icc.service;

import be.icc.dto.UserDto;

/**
 * Created by Student on 14-01-16.
 */
public interface MailService {
    void sendConfirmationSignUpEmail(UserDto user);
}
