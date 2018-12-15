package be.icc.controller;

import be.icc.dto.CityDto;
import be.icc.dto.PanierDto;
import be.icc.dto.UserDto;
import be.icc.form.LoginForm;
import be.icc.form.SignupForm;
import be.icc.service.CityService;
import be.icc.service.PanierService;
import be.icc.service.UserService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    @RequestMapping("")
    public String connect(Model model,  @RequestParam(required = false) String error) {
        if (isNotBlank(error)) {
            if (error.equals("wrong")) {
                model.addAttribute("message", "error.login.error");
            }
            if (error.equals("notUnique")) {
                model.addAttribute("message", "error.usernameNotUnique");
            }
        }
        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        if (!model.containsAttribute("signupForm")) {
            model.addAttribute("signupForm", new SignupForm());
        }
        return "connect";
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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult result,
                        RedirectAttributes attr) {
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
        UserDto user = new UserDto();
        user.setEmail(signupForm.getEmail());
        user.setFirstName(signupForm.getFirstName());
        user.setLastName(signupForm.getLastName());
        user.setPassword(signupForm.getPassword());
        user.setUsername(signupForm.getUserName());
        CityDto city = new CityDto();
        city.setName(signupForm.getCity());
        city.setState(signupForm.getState());
        user.setCity(cityService.add(city));
        user.setPanier(panierService.add(new PanierDto()));
        userService.signUp(user);
        return "redirect:/";
    }
}