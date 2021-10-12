package com.sparta.Spartaconferencelogback.dto;

import com.sparta.Spartaconferencelogback.domain.Conference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DateListResponseDto {
    private List<Conference> conferenceList;
}
