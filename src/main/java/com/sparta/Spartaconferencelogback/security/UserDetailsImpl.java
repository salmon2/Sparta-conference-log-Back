package com.sparta.Spartaconferencelogback.security;

import com.sparta.Spartaconferencelogback.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * userdetailsimpl에 user 객체를 넣으면
 * 매번 요청, 인증 때마다 조회용 쿼리가 나가야함
 * 개선 리팩토링 */
public class UserDetailsImpl implements UserDetails {
    private Long userId;
    private String username;
    private String nickname;
    private String password;

    public UserDetailsImpl(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }

    public UserDetailsImpl(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public String getNickname() {return nickname;}

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
