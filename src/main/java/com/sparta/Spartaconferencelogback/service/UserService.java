package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.User;
import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.dto.UserList;
import com.sparta.Spartaconferencelogback.dto.UserListResponseDto;
import com.sparta.Spartaconferencelogback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("중복 사용자 오류.");
        }
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        User user = new User(username, nickname, password);
        userRepository.save(user);
    }

    public UserList getUserList() {
        List<User> all = userRepository.findAll();

        List<UserListResponseDto> userListResponseDtos = new ArrayList<>();

        for (User user : all) {
            UserListResponseDto userListResponseDto = new UserListResponseDto(user.getId(), user.getUsername());
            userListResponseDtos.add(userListResponseDto);
        }

        UserList newUserList = new UserList(userListResponseDtos);

        return newUserList;
    }
}
