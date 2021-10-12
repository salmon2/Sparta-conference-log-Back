package com.sparta.Spartaconferencelogback.service;


import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.dto.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.dto.ConferenceUpdateRequestDto;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;

public interface ConferenceService {
    Conference save(ConferenceRequestDto conferenceRequestDto, UserDetailsImpl userDetails);
    void delete(Long conferenceId);

    void update(Long conferenceId, ConferenceUpdateRequestDto conferenceUpdateRequestDto);
}

