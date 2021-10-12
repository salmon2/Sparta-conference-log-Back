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
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto signupRequestDto) {
        isValidateAll(
                signupRequestDto.getUsername(),
                signupRequestDto.getNickname(),
                signupRequestDto.getPassword());

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();
        User user = new User(username, nickname, password);
        userRepository.save(user);
    }

    public void checkDuplicateByUsername(String username) {

        final String pattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

        if (!Pattern.matches(pattern, username)) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복 이메일 오류.");
        }
    }

    public void checkDuplicateByNickname(String nickname) {
        Optional<User> found = userRepository.findByNickname(nickname);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복 닉네임 오류.");
        }
    }

    private void isValidatePassword(String password) {
        final int MIN = 4;
        final int MAX = 10;
        final String pattern = "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{" + MIN + "," + MAX + "})$";
        if (!Pattern.matches(pattern, password)) {
            throw new IllegalArgumentException("유효하지 않은 패스워드 입니다." +
                    "[패스워드는 4자 이상 10자 이하이며 영어,숫자,특수문자를 포함해야 합니다.]");
        }

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



    public void isValidateAll(String username, String nickname, String password) {
        checkDuplicateByUsername(username);
        checkDuplicateByNickname(nickname);
        isValidatePassword(password);

    }


}
