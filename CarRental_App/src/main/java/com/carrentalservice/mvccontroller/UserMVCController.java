package com.carrentalservice.mvccontroller;

import com.carrentalservice.DTO.CustomerDTO;
import com.carrentalservice.entity.User;
import com.carrentalservice.service.CustomerService;
import com.carrentalservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserMVCController {

    private final UserService userService;
    private final CustomerService customerService;

    @Autowired
    public UserMVCController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping(path = "/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "register";
    }

    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute("userRegister") @Valid CustomerDTO customerDTO, BindingResult result) {
        Optional<User> userOptional = userService.findUserByUsername(customerDTO.getUsername());
        if (userOptional.isPresent()) {
            result.rejectValue("username", null, "Username already exists!");
        }
        if (!customerDTO.getPassword().equals(customerDTO.getConfirmPassword())) {
            result.rejectValue("password", null, "Passwords do not match!");
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerCustomer(customerDTO);
        return "login";
    }
}
