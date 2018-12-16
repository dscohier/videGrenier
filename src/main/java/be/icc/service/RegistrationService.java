package be.icc.service;

import be.icc.entity.User;

/**
 * Created by Student on 14-01-16.
 */
public interface RegistrationService {
    void sendConfirmationEmail(final User user);
    void sendEmailComingSoon(final User user);
}
