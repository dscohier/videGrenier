package be.icc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Controller
@RequestMapping("")
public class HomeController {



    @RequestMapping("")
    public String index(Model model) {
        return "home";
    }




    @RequestMapping("/menu")
    public String menu() {
        return "menu";
    }



    @RequestMapping("/connect")
    public String connect() {
        return "connect";
    }
}