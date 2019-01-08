package be.icc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Controller
@RequestMapping("")
public class HomeController {

    @RequestMapping("")
    public String index() {
        return "home";
    }

    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }

    @ExceptionHandler(Exception.class)
    public String erreur(HttpServletRequest request, Model model, Exception exception) {
        model.addAttribute("exception",exception);
        model.addAttribute("url",request.getRequestURL());
        return "erreur";
    }
}