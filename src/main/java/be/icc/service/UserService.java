package be.icc.service;

import be.icc.dto.UserDto;

/**
 * Created by Student on 09-12-18.
 */
public interface UserService {
    UserDto findByUsernameAndPassword(String username, String password);

    UserDto signUp(UserDto user);

    UserDto findByUsername(String username);
}
