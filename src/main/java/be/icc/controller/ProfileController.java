package be.icc.controller;

import be.icc.dto.UserDto;
import be.icc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Scohier Dorian on 27-03-19.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;

    // TODO error if user not exist
    @RequestMapping("")
    public String profile(Model model, @RequestParam(required = true) String username) {
        UserDto user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
}