package be.icc.controller;

import be.icc.dto.CityDto;
import be.icc.dto.PanierDto;
import be.icc.dto.UserDto;
import be.icc.entity.User;
import be.icc.form.LoginForm;
import be.icc.form.SignupForm;
import be.icc.model.FileModel;
import be.icc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
        if (isNotBlank(success)) {
            model.addAttribute("success", "success.userCreated");
        }
        if (!model.containsAttribute("loginForm")) {
            model.addAttribute("loginForm", new LoginForm());
        }
        if (!model.containsAttribute("signupForm")) {
            model.addAttribute("signupForm", new SignupForm());
        }
        initialiseModelForAddAndUpdate(model, error);
        return "/connect";
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
                        RedirectAttributes attr, @RequestParam MultipartFile file, Model model) {
        boolean isUsernameUnique = userService.findByUsername(signupForm.getUserName()) == null;
        String redirect = checkError(result, attr, signupForm);
        if (redirect != null) {
            return redirect;
        }
        if (!isUsernameUnique) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.signupForm", result);
            attr.addFlashAttribute("signupForm", signupForm);
            return "redirect:/connect?error=notUniqueLogin";
        }
        boolean isMailUnique = userService.findByMail(signupForm.getEmail()) == null;
        if (!isMailUnique) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.signupForm", result);
            attr.addFlashAttribute("signupForm", signupForm);
            return "redirect:/connect?error=notUniqueEmail";
        }

        String filePath = null;
        if (!signupForm.getFile().isEmpty()) {
            filePath = fileService.uploadFile(file, signupForm.getUserName(), signupForm.getFile().getOriginalFilename());
            if (filePath.contains("error")) {
                attr.addFlashAttribute("org.springframework.validation.BindingResult.signupForm", result);
                attr.addFlashAttribute("signupForm", signupForm);
                model.addAttribute("error", "error.add.pictureFormat");
                return "redirect:/connect?error=pictureFormat";
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
        CityDto cityDto = cityService.createOrGetIfExists(city, signupForm.getCountry());
        user.setCity(cityDto);
        user.setPanier(panierService.add(new PanierDto()));
        user.setCreationDate(new Date());
        userService.signUp(user);
        mailService.sendConfirmationSignUpEmail(user);
        return "redirect:/connect?success=userCreated";
    }

    @RequestMapping("/modifyProfil")
    public String modifyProfil(@ModelAttribute("signupForm") SignupForm updateSignupForm, Model model, @RequestParam(required = false) String error) {
        if ("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
            return "redirect:/connect";
        }
        UserDto userDto = (UserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDto = userService.findEntityById(userDto.getId()).toDto();
        if (!model.containsAttribute("signupForm") || updateSignupForm != null) {
            SignupForm signupForm = new SignupForm();
            signupForm.setId(userDto.getId());
            signupForm.setCity(userDto.getCity().getName());
            signupForm.setEmail(userDto.getEmail());
            signupForm.setFirstName(userDto.getFirstName());
            signupForm.setLastName(userDto.getLastName());
            signupForm.setUserName(userDto.getUsername());
            model.addAttribute("signupForm", signupForm);
        }
        initialiseModelForAddAndUpdate(model, error);
        return "/connect";
    }

    @RequestMapping("/update")
    public String update(@ModelAttribute("signupForm") @Valid SignupForm signupForm, BindingResult result,
                         RedirectAttributes attr, HttpServletRequest request, @RequestParam MultipartFile file) {
        String redirect = checkError(result, attr, signupForm);
        if (redirect != null) {
            return "redirect:/connect";
        }
        String filePath = fileService.uploadFile(file, ((UserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(), signupForm.getFile().getOriginalFilename());
        if (filePath.contains("error")) {
            attr.addFlashAttribute("signupForm", signupForm);
            return "redirect:/connect?error=PictureFormat";
        }
        User user = userService.findEntityById(signupForm.getId());
        user.setEmail(signupForm.getEmail());
        user.setFirstName(signupForm.getFirstName());
        user.setLastName(signupForm.getLastName());
        BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
        String hashedPassword= encoder.encode(signupForm.getPassword());
        user.setPassword(hashedPassword);
        user.setUsername(signupForm.getUserName());
        user.setPicture(filePath);
        String city = signupForm.getCity().split(",")[0];
        CityDto cityDto = cityService.createOrGetIfExists(city, signupForm.getCountry());
        user.setCity(cityDto.toEntity());
        UserDto userDto = userService.update(user);
        return "redirect:/profile?username=" + userDto.getUsername();
    }

    private void initialiseModelForAddAndUpdate(Model model, String error) {
        if (!model.containsAttribute("fileModel")) {
            FileModel fileModel = new FileModel();
            model.addAttribute("fileModel", fileModel);
        }
        if (isNotBlank(error)) {
            if (error.equals("wrong")) {
                model.addAttribute("error", "error.login.error");
            }
            if (error.equals("notUniqueLogin")) {
                model.addAttribute("errorSignup", "error.usernameNotUnique");
            }
            if (error.equals("notUniqueEmail")) {
                model.addAttribute("errorSignup", "error.emailNotUnique");
            }
            if (error.equals("noPicture")) {
                model.addAttribute("errorSignup", "error.profile.noPicture");
            }
            if (error.equals("pictureFormat")) {
                model.addAttribute("errorSignup", "error.add.pictureFormat");
            }
        }
    }

    private String checkError(BindingResult result, RedirectAttributes attr, SignupForm signupForm) {
        if (result.hasErrors() || (signupForm.getFile().isEmpty()&& signupForm.getId() == null)) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.signupForm", result);
            attr.addFlashAttribute("signupForm", signupForm);
            if (signupForm.getFile().isEmpty() && signupForm.getId() == null) {
                return "redirect:/connect?error=noPicture";
            }
            return "redirect:/connect";
        }
        return null;
    }
}