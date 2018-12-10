package be.icc.controller;

import be.icc.form.ConnectForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


/**
 * Created by Scohier Dorian on 04-11-18.
 */
@Controller
@RequestMapping("/connect")
public class IdentificationController {

    @RequestMapping("")
    public String connect(Model model) {
        if (!model.containsAttribute("connectForm")) {
            model.addAttribute("connectForm", new ConnectForm());
        }
        return "connect";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("connectForm") @Valid ConnectForm connectForm, BindingResult result,
                        RedirectAttributes attr) {
        if (result.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.connectForm", result);
            attr.addFlashAttribute("connectForm", connectForm);
            return "redirect:/connect";
        }
        return "redirect:/";
    }
}