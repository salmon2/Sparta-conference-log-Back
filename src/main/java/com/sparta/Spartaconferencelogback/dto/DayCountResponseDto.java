package com.sparta.Spartaconferencelogback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DayCountResponseDto {
    private Long day;   //일
    private int count;  //해당 날짜에 있는 회의 개수
}
