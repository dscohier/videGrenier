package be.icc.controller;

import be.icc.dto.OrdersDto;
import be.icc.dto.UserDto;
import be.icc.service.OrderService;
import be.icc.service.ProductService;
import be.icc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by Scohier Dorian on 27-03-19.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    // TODO error if user not exist
    @RequestMapping("")
    public String profile(Model model, @RequestParam(required = true) String username) {
        UserDto user = userService.findByUsername(username);
        if (user == null) {
            return "error";
        } else {
            List<OrdersDto> ordersDto = orderService.findByUser(user.getId());
            int purchaseNumber = 0;
            for (OrdersDto orderDto : ordersDto) {
                purchaseNumber += orderDto.getProducts().size();
            }
            model.addAttribute("saleNumber", productService.findBySellerAndIsSellTrue(user.toEntity()).size());
            model.addAttribute("user", user);
            model.addAttribute("purchaseNumber", purchaseNumber);
            return "profile";
        }
    }
}