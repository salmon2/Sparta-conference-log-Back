package com.sparta.Spartaconferencelogback.dto.datedtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DateCountResponseDto {
    private Long year;
    private Long month;
    private List<DayCountResponseDto> data; //data = {day:day, count:count}

}
