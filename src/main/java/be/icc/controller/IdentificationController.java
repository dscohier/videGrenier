package be.icc.controller;

import be.icc.dto.CityDto;
import be.icc.dto.PanierDto;
import be.icc.dto.UserDto;
import be.icc.form.LoginForm;
import be.icc.form.SignupForm;
import be.icc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.Date;

import static org.apache.commons.lang.StringUtils.isNotBlank;


/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Controller
@RequestMapping("/connect")
public class IdentificationController {

    @Autowired
    UserService userService;
    @Autowired
    CityService cityService;
    @Autowired
    PanierService panierService;
    @Autowired
    MailService mailService;
    @Autowired
    FileService fileService;

    @RequestMapping("")
    public String connect(Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String success) {
        if (isNotBlank(error)) {
            if (error.equals("wrong")) {
                model.addAttribute("error", "error.login.error");
            }
            if (error.equals("notUnique")) {
                model.addAttribute("error", "error.usernameNotUnique");
            }
        }
        if (isNotBlank(success)) {
            model.addAttribute("success", "success.userCreated");
        }
        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        if (!model.containsAttribute("signupForm")) {
            model.addAttribute("signupForm", new SignupForm());
        }
        return "connect2";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") @Valid LoginForm loginForm, BindingResult result,
                        RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.loginForm", result);
            attr.addFlashAttribute("loginForm", loginForm);
            return "redirect:/connect";
        }

        UserDto user = userService.findByUsernameAndPassword(loginForm.getUserName(), loginForm.getPassword());

        if (user == null) {
            return "redirect:/connect?error=wrong";
        } else {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        return "redirect:/";
    }

// TODO CHECK EMAIL duplicate
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult result,
                        RedirectAttributes attr, @RequestParam MultipartFile file) {
        boolean isUsernameUnique = userService.findByUsername(signupForm.getUserName()) == null;
        if (result.hasErrors() || !isUsernameUnique) {
            signupForm.setCity("");
            attr.addFlashAttribute("org.springframework.validation.BindingResult.signupForm", result);
            attr.addFlashAttribute("signupForm", signupForm);
            if (!isUsernameUnique) {
                return "redirect:/connect?error=notUnique";
            } else {
                return "redirect:/connect";
            }
        }
        String filePath = null;
        if (file != null) {
            filePath = fileService.uploadFile(file, signupForm.getUserName(), signupForm.getFile().getOriginalFilename());
            if (filePath.contains("error")) {
                attr.addFlashAttribute("signupForm", signupForm);
                return "redirect:/connect?error=PictureFormat";
            }
        }
        UserDto user = new UserDto();
        user.setEmail(signupForm.getEmail());
        user.setFirstName(signupForm.getFirstName());
        user.setLastName(signupForm.getLastName());
        user.setPassword(signupForm.getPassword());
        user.setUsername(signupForm.getUserName());
        user.setPicture(filePath);
        String city = signupForm.getCity().split(",")[0];
        CityDto cityDto = cityService.createOrGetIfExists(city, signupForm.getState());
        user.setCity(cityDto);
        user.setPanier(panierService.add(new PanierDto()));
        user.setCreationDate(new Date());
        userService.signUp(user);
        mailService.sendConfirmationSignUpEmail(user);
        return "redirect:/connect?success=userCreated";
    }
}