package be.icc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
        ModelAndView errorPage = new ModelAndView("error");
        return errorPage;
    }
}