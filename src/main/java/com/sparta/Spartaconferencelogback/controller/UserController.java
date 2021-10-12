package com.sparta.Spartaconferencelogback.controller;


import com.sparta.Spartaconferencelogback.dto.ResponseMsg;
import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public ResponseMsg registerUser(@RequestBody SignupRequestDto requestDto) {
        try {
            userService.registerUser(requestDto);
        } catch (Exception e) {
            return new ResponseMsg("fail");
        }
        return new ResponseMsg("success");

    }

    @PostMapping("/email")
    public ResponseMsg checkDupUsername(@RequestBody String username) {
        try {
            userService.checkDuplicateByUsername(username);
        } catch (Exception e) {
            return new ResponseMsg("fail");
        }
        return new ResponseMsg("success");
    }

    @PostMapping("/nickname")
    public ResponseMsg checkDupNickname(@RequestBody String nickname) {
        try {
            userService.checkDuplicateByNickname(nickname);
        } catch (Exception e) {
            return new ResponseMsg("fail");
        }
        return new ResponseMsg("success");
    }
}