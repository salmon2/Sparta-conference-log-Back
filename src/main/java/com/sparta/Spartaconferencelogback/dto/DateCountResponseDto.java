package com.sparta.Spartaconferencelogback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DateCountResponseDto {
    private Long year;
    private Long month;
    private JSONObject data; //data = {day:day, count:count}

}
