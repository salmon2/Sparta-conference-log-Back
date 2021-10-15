package com.sparta.Spartaconferencelogback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TimeResponseDto {
    private String hour;    //작성 시간 중 시
    private String minute;  //작성 시간 중 분
}
