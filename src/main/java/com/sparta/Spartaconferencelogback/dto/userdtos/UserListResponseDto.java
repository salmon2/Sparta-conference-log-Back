package com.sparta.Spartaconferencelogback.dto.userdtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserListResponseDto {
    private Long userId;
    private String username;
}
