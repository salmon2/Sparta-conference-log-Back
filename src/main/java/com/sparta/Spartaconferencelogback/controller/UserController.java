package com.sparta.Spartaconferencelogback.controller;


import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.dto.UserInfoDto;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import com.sparta.Spartaconferencelogback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    @GetMapping("/user/loginView")
    public String login() {
        return "login";
    }


    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }


    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/loginView";
    }


    @PostMapping("/user/userinfo")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUsername();
        String nickname = userDetails.getNickname();

        return new UserInfoDto(username, nickname);
    }


    @GetMapping("/dummy1")
    public String authTest() {
        return "dummy1";
    }

    @GetMapping("/dummy2")
    public String authTest2() {
        return "dummy2";
    }
}