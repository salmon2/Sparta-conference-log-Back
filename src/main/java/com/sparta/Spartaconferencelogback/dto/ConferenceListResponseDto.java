package com.sparta.Spartaconferencelogback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConferenceListResponseDto {
    private Long conferenceId;
    private String title;
    private String leader;
    private String date;
}
