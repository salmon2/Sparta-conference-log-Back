package com.sparta.Spartaconferencelogback.dto;

import com.sparta.Spartaconferencelogback.domain.UserConferenceAttendance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConferenceDetailResponseDto {
    private Long conferenceId;
    private String title;
    private List<UserConferenceAttendance> actualJoinedPeople;
    private String writer;
    private String date;
    private TimeResponseDto time;
    private String lastModifiedAt;
    private String contents;

}
