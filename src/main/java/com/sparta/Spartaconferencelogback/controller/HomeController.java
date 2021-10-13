package com.sparta.Spartaconferencelogback.controller;

import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import io.swagger.annotations.Api;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags={"더미 홈 관련 APi"})
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }

        return "index";
    }
    @GetMapping("/user/loginView")
    public String testLoginPage(){
        return "login";
    }
}