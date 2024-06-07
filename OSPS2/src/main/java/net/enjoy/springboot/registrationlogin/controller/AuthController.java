package net.enjoy.springboot.registrationlogin.controller;

import jakarta.validation.Valid;
import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.service.UserService;
import net.enjoy.springboot.registrationlogin.service.EmailService; // 添加EmailService导入
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthController {
    private UserService userService;
    private EmailService emailService; // 添加EmailService

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/homesample")
    public String homesample() {
        return "homesample";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
            model.addAttribute("user", userDto);
            return "/register"; // 退避消息
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }
        userService.saveUser(userDto);
        emailService.sendWelcomeEmail(userDto.getEmail());
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam(required = false) String username, @RequestParam(required = false) String password, Model model) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("emptyError", "請輸入帳號和密碼");
            return "login";
        }

        if (!username.equals("correctUsername") || !password.equals("correctPassword")) {
            model.addAttribute("invalidError", "Invalid Email or Password");
            return "login";
        }

        return "redirect:/homepage";
    }



    @GetMapping("/map1")
    public String map1() {
        return "map1";
    }
    @GetMapping("/map1admin")
    public String map1admin() {
        return "map1admin";
    }

    @GetMapping("/map2")
    public String map2() {
        return "map2";
    }
    @GetMapping("/map2admin")
    public String map2admin() {
        return "map2admin";
    }
    @GetMapping("/adminlogin")
    public String adminlogin() {
        return "adminlogin";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/homepage")
    public ModelAndView homepage() {
        ModelAndView modelAndView = new ModelAndView("homepage");
        return modelAndView;
    }
}
