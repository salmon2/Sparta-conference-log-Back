package com.sparta.Spartaconferencelogback.dto.confefencedtos;

import com.sparta.Spartaconferencelogback.dto.userdtos.UserIdRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRequestDto {
    private String title;

    private String date;

    private String purpose;

    private String contents;

    private List<UserIdRequestDto> member;

    private List<UserIdRequestDto> attendance;

}
