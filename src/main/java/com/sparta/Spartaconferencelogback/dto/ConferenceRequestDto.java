package com.sparta.Spartaconferencelogback.dto;

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

    private List<UserIdRequestDto> member;

    private List<UserIdRequestDto> attendance;

    private String purpose;

    private String contents;
}
