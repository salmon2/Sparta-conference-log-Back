package com.sparta.Spartaconferencelogback.controller;


import com.sparta.Spartaconferencelogback.dto.ResponseMsg;
import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.dto.UserInfoDto;
import com.sparta.Spartaconferencelogback.dto.UserList;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import com.sparta.Spartaconferencelogback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 예외처리, 반환 방법 합의 후 결정하기
     *
     */


    @PostMapping("/signup")
    public ResponseMsg registerUserPost(@RequestBody SignupRequestDto requestDto) {
        try {
            userService.registerUser(requestDto);
        } catch (Exception e) {
            return new ResponseMsg(500L, "fail");
        }
        return new ResponseMsg(200L, "success");

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

    @GetMapping("/user/list")
    @ResponseBody
    public UserList userList(){
        UserList userList = userService.getUserList();
        return userList;
    }

    @GetMapping("/dummy1")
    public String authTest() {
        return "dummy1";
    }

    @PostMapping("/email")
    public ResponseMsg checkDupUsername(@RequestBody String username){
        try {
            userService.checkDuplicateByUsername(username);
        } catch (Exception e) {
            return new ResponseMsg(500L, "fail");
        }
        return new ResponseMsg(200L, "success");

    }

    @PostMapping("/nickname")
    public ResponseMsg checkDupNickname(@RequestBody String nickname) {
        try {
            userService.checkDuplicateByNickname(nickname);
        } catch (Exception e) {
            return new ResponseMsg(500L, "fail");
        }
        return new ResponseMsg(200L, "success");
    }
}