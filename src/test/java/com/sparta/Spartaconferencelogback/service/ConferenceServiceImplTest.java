package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.dto.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.dto.UserIdRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ConferenceServiceImplTest {



    @Test
    @DisplayName("회의 저장")
    void conference_save() {
        new UserIdRequestDto()


        new ConferenceRequestDto("테스트 제목", "2021-11-10 10:00",
                )



    }
}