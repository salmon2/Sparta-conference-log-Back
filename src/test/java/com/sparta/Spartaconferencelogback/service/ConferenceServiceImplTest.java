package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.dto.confefencedtos.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.dto.userdtos.UserIdRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


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