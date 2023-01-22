package com.akash.recruitment.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String root() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal != null) {

            if (principal instanceof UserDetails) {

                if (((UserDetails) principal).getUsername() != null) {
                    return "redirect:/dashboard";
                }
            }
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-failed")
    public String loginFailed(Model model) {
        model.addAttribute("error", "username or password wrong");
        return "login";
    }
}