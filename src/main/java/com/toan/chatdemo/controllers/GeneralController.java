package com.toan.chatdemo.controllers;

import com.toan.chatdemo.exceptions.InternalServerException;
import com.toan.chatdemo.models.dtos.AddUserDto;
import com.toan.chatdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class GeneralController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/403")
    public String accessDeny() {
        return "403";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/login")
    public String login(HttpSession httpSession, Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String createAccount(@ModelAttribute AddUserDto dto, Model model) {
        model.addAttribute("user", dto);
        try {
            userService.add(dto);
            return "signup-success";
        } catch (InternalServerException e) {
            return "error";
        }
    }

    @GetMapping("/chat")
    public String chat(HttpSession httpSession, Model model) {
        String username = (String) httpSession.getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username);
            return "chat";
        }
        return "redirect:/login";
    }
}
