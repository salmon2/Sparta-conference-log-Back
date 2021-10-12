package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.domain.Date;
import com.sparta.Spartaconferencelogback.dto.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.repository.ConferenceRepository;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService{
    private final ConferenceRepository conferenceRepository;

    @Override
    @Transactional
    public Conference save(ConferenceRequestDto conferenceRequestDto, UserDetailsImpl userDetails) {



        Date date = Date.StringToDate(conferenceRequestDto.getDate());

        Conference newConference = new Conference(conferenceRequestDto.getTitle(),
                conferenceRequestDto.getPurpose(),
                conferenceRequestDto.getContents(),
                date);



        Conference saveConference = conferenceRepository.save(newConference);

        return saveConference;
    }
}

