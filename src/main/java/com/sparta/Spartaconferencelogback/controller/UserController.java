package com.sparta.Spartaconferencelogback.controller;


import com.sparta.Spartaconferencelogback.domain.User;
import com.sparta.Spartaconferencelogback.dto.ResponseMsg;
import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.dto.userdtos.LoginRequestDto;
import com.sparta.Spartaconferencelogback.dto.userdtos.TokenResponse;
import com.sparta.Spartaconferencelogback.dto.userdtos.UserInfoDto;
import com.sparta.Spartaconferencelogback.dto.userdtos.UserList;
import com.sparta.Spartaconferencelogback.repository.UserRepository;
import com.sparta.Spartaconferencelogback.security.JwtTokenProvider;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import com.sparta.Spartaconferencelogback.service.UserService;
import com.sparta.Spartaconferencelogback.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags={"유저 관련 APi"})
public class UserController {

    private final UserServiceImpl userService;
    private final JwtTokenProvider jwtTokenProvider;



    @ApiOperation(value="회원 등록", notes="성공 실패여부 반환")
    @PostMapping("/user/signup")
    public ResponseMsg registerUserPost(@RequestBody SignupRequestDto requestDto) {
        try {
            userService.registerUser(requestDto);
        } catch (Exception e) {
            return new ResponseMsg(500L, e.getMessage());
        }
        return new ResponseMsg(200L, "success");

    }

    @ApiOperation(value = "회원 로그인", notes = "로그인 결과 반환")
    @PostMapping("/user/login")
    public TokenResponse userLogin(@RequestBody LoginRequestDto loginRequestDto) {
        TokenResponse tokenResponse = new TokenResponse();
        try {
            User user = userService.userLogin(loginRequestDto);
            tokenResponse.setToken(jwtTokenProvider.createToken(user.getUsername()));
            tokenResponse.setStatusCode(500L);
            tokenResponse.setMsg("success");
        } catch (Exception e) {
            tokenResponse.setStatusCode(500L);
            tokenResponse.setMsg(e.getMessage());
        }
        return tokenResponse;
    }

    @GetMapping("/user/list")
    @ApiOperation(value="회원 리스트 조회 ", notes="회원 리스트 조회 ")
    @ResponseBody
    public UserList userList() {
        UserList userList = userService.getUserList();
        return userList;
    }

    @ApiOperation(value="회원 아이디 중복체크", notes="올바른 email 형식인지 확인, 중복여부 확인 후 성공 실패여부 반환")
    @PostMapping("/user/email")
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

    @GetMapping("/user")
    @ApiOperation(value="유저 정보 확인, jwt token 필요 ", notes="유저 정보 확인")
    public UserInfoDto userInfoDto(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("userDetails = " + userDetails);

        UserInfoDto userInfoDto = new UserInfoDto(userDetails.getUsername(), userDetails.getNickname());

        return userInfoDto;
    }

}