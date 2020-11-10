package com.example.sarafan.controller;

import com.example.sarafan.domain.User;
import com.example.sarafan.repo.MessageRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {
    @Autowired
    private final MessageRepo messageRepo;

    @GetMapping
    public String main(
            @AuthenticationPrincipal User user,
            Model model
    ) {

        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", user);
        data.put("messages", messageRepo.findAll());

        model.addAttribute("frontendData", data);

        return "index";
    }
}
