package com.sparta.Spartaconferencelogback.service;


import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.dto.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.dto.ConferenceUpdateRequestDto;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;

import com.sparta.Spartaconferencelogback.dto.ConferenceDetailResponseDto;

public interface ConferenceService {
    Conference save(ConferenceRequestDto conferenceRequestDto, UserDetailsImpl userDetails);
    void delete(Long conferenceId);
    Conference update(Long conferenceId, ConferenceUpdateRequestDto conferenceUpdateRequestDto);
    //회의 단건 정보 조회
    public ConferenceDetailResponseDto getConferenceDetail(Long conferenceId);

}

