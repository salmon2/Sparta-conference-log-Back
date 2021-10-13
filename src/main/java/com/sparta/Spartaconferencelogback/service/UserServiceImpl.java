package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.Group;
import com.sparta.Spartaconferencelogback.domain.User;
import com.sparta.Spartaconferencelogback.domain.userGroup;
import com.sparta.Spartaconferencelogback.dto.SignupRequestDto;
import com.sparta.Spartaconferencelogback.dto.userdtos.LoginRequestDto;
import com.sparta.Spartaconferencelogback.dto.userdtos.UserList;
import com.sparta.Spartaconferencelogback.dto.userdtos.UserListResponseDto;
import com.sparta.Spartaconferencelogback.repository.UserRepository;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //유저 등록
    public void registerUser(SignupRequestDto signupRequestDto) {
        // 중복검사, 요건 검사.
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

    //username 유효성 검사(1. 중복여부 확인, 2. email형식 확인)
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

    //nickname 유효성검사
    public void checkDuplicateByNickname(String nickname) {
        Optional<User> found = userRepository.findByNickname(nickname);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복 닉네임 오류.");
        }
    }

    // 비밀번호 유효성 검사 4~10자리 영어, 숫자, 특수문자 포함.
    private void isValidatePassword(String password) {
        final int MIN = 4;
        final int MAX = 10;
        final String pattern = "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{" + MIN + "," + MAX + "})$";
        if (!Pattern.matches(pattern, password)) {
            throw new IllegalArgumentException("유효하지 않은 패스워드 입니다." +
                    "[패스워드는 4자 이상 10자 이하이며 영어,숫자,특수문자를 포함해야 합니다.]");
        }

    }

    // 모든 user list 추출.
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

    // group에 포함된 그룹의 user list 추출
    public UserList findUserByGroup(Group group) {
        List<userGroup> userGroups = group.getUserGroups();
        List<UserListResponseDto> userListResponseDtos = new ArrayList<>();
        for (userGroup userGroup : userGroups) {
            User user = userGroup.getUser();
            UserListResponseDto userListResponseDto = new UserListResponseDto(user.getId(), user.getUsername());
            userListResponseDtos.add(userListResponseDto);
        }
        UserList newUserList = new UserList(userListResponseDtos);
        return newUserList;
    }

    // 내가 조인한 그룹의 유저들 추출
    public UserList findUserByJoinedGroup(UserDetailsImpl userDetails) {
        User user = findByUsername(userDetails.getUsername());
        //userlist 생성용
        List<UserListResponseDto> userListResponseDtos = new ArrayList<>();
        //내가 join한 그룹
        List<userGroup> userGroups = user.getUserGroups();
        //userlist 생성용 array에 내가 join한 그룹에 있는 다른 유저리스트를 받아와 add
        for (userGroup userGroup : userGroups) {
            userListResponseDtos.addAll(findUserByGroup(userGroup.getGroup()).getUserList());
        }
        return new UserList(userListResponseDtos);
    }


    // username으로 user 찾기
    @Override
    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당하는 id의 유저가 없습니다."));
    }

    @Override
    public User userLogin(LoginRequestDto loginRequestDto) {
        User user = userRepository
                .findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디 입니다."));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호를 확인하세요");
        }
        return user;


    }


    public void isValidateAll(String username, String nickname, String password) {
        checkDuplicateByUsername(username);
        checkDuplicateByNickname(nickname);
        isValidatePassword(password);

    }


}
