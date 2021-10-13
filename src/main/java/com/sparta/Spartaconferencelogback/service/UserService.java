package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.User;
import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.dto.UserList;

public interface UserService {
    public void registerUser(SignupRequestDto signupRequestDto);

    public void checkDuplicateByUsername(String username);

    public void checkDuplicateByNickname(String nickname);

    public UserList getUserList();

    public User findByUsername(String username);


}
