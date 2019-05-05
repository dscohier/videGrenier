package be.icc.service.imp;

import be.icc.dto.AuthorityDto;
import be.icc.dto.UserDto;
import be.icc.entity.User;
import be.icc.repository.UserRepository;
import be.icc.service.AuthorityService;
import be.icc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Scohier Dorian on 09-12-18.
 */
@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityService authorityService;

    @Override
    public UserDto findByUsernameAndPassword(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean passwordsMatch = encoder.matches(password, user.getPassword());

        if (!passwordsMatch) {
            return null;
        }

        return user.toDto();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            return user.toDto();
        } else {
            throw new UsernameNotFoundException("Username : " + username + " not found");
        }
    }

    @Override
    public UserDto signUp(UserDto user) {
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String hashedPassword= encoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        AuthorityDto authority = authorityService.createOrgetIfExists("ROLE_USER");
        user.getAuthorities().add(authority.toEntity());
        User userEntity = user.toEntity();
        UserDto userDto = userRepository.save(userEntity).toDto();
        return userDto;
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        } else {
            return user.toDto();
        }
    }

    @Override
    public User findEntityById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserDto update(User user) {
        User userSaved = userRepository.save(user);
        return userSaved.toDto();
    }

    @Override
    public UserDto findByMail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        } else {
            return user.toDto();
        }
    }
}
