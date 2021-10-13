package com.sparta.Spartaconferencelogback.controller;


import com.sparta.Spartaconferencelogback.dto.ResponseMsg;
import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.dto.UserInfoDto;
import com.sparta.Spartaconferencelogback.dto.UserList;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import com.sparta.Spartaconferencelogback.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags={"유저 관련 APi"})
public class UserController {

    private final UserServiceImpl userService;

    /**
     * 예외처리, 반환 방법 합의 후 결정하기
     *
     */

    @ApiOperation(value="회원 등록", notes="성공 실패여부 반환")
    @PostMapping("/user")
    public ResponseMsg registerUserPost(@RequestBody SignupRequestDto requestDto) {
        try {
            userService.registerUser(requestDto);
        } catch (Exception e) {
            return new ResponseMsg(500L, "fail");
        }
        return new ResponseMsg(200L, "success");

    }

    @ApiOperation(value="회원 리스트", notes="회원 리스트를 반환")
    @GetMapping("/user/list")
    @ResponseBody
    public UserList userList(){
        UserList userList = userService.getUserList();
        return userList;
    }

    @ApiOperation(value="회원 아이디 중복체크", notes="올바른 email형식인지 확인, 중복여부 확인 후 성공 실패여부 반환")
    @PostMapping("/email")
    public ResponseMsg checkDupUsername(@RequestBody String username){
        try {
            userService.checkDuplicateByUsername(username);
        } catch (Exception e) {
            return new ResponseMsg(500L, "fail");
        }
        return new ResponseMsg(200L, "success");

    }
    @ApiOperation(value="닉네임 중복체크", notes="닉네임 중북여부 확인 후 성공 실패여부 반환")
    @PostMapping("/user/nickname")
    public ResponseMsg checkDupNickname(@RequestBody String nickname) {
        try {
            userService.checkDuplicateByNickname(nickname);
        } catch (Exception e) {
            return new ResponseMsg(500L, "fail");
        }
        return new ResponseMsg(200L, "success");
    }
}