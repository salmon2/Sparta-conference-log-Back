package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.dto.ConferenceDetailResponseDto;

public interface ConferenceService {

    //회의 단건 정보 조회
    public ConferenceDetailResponseDto getConferenceDetail(Long conferenceId);

}
