package com.sparta.Spartaconferencelogback.dto.datedtos;

import com.sparta.Spartaconferencelogback.dto.confefencedtos.ConferenceListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DateListResponseDto {
    private List<ConferenceListResponseDto> conferenceList;
}
