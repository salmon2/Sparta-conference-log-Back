package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.*;
import com.sparta.Spartaconferencelogback.dto.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.dto.ConferenceUpdateRequestDto;
import com.sparta.Spartaconferencelogback.dto.UserIdRequestDto;
import com.sparta.Spartaconferencelogback.repository.*;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService{
    private final UserRepository userRepository;
    private final ConferenceRepository conferenceRepository;
    private final DateRepository dateRepository;
    private final UserConferenceMemberRepository userConferenceMemberRepository;
    private final UserConferenceAttendanceRepository userConferenceAttendanceRepository;


    @Override
    @Transactional
    public Conference save(ConferenceRequestDto conferenceRequestDto, UserDetailsImpl userDetails) {
        Date newDate = Date.StringToDate(conferenceRequestDto.getDate());
        dateRepository.save(newDate);

        Conference newConference = new Conference(conferenceRequestDto.getTitle(),
                conferenceRequestDto.getPurpose(),
                conferenceRequestDto.getContents(),
                newDate);


        Conference saveConference = conferenceRepository.save(newConference);

        saveConferenceUser(conferenceRequestDto, userDetails, saveConference);

        saveConferenceAttendance(conferenceRequestDto, saveConference);


        return saveConference;
    }



    private void saveConferenceAttendance(ConferenceRequestDto conferenceRequestDto, Conference saveConference) {
        for (UserIdRequestDto userIdRequestDto: conferenceRequestDto.getAttendance()) {
            User findUser = userRepository.findById(userIdRequestDto.getUserId()).orElseThrow(
                    () -> new NullPointerException("해당 하는 아이디의 유저가 없습니다.")
            );

            UserConferenceAttendance userConferenceAttendance = new UserConferenceAttendance(findUser, saveConference);

            UserConferenceAttendance save = userConferenceAttendanceRepository.save(userConferenceAttendance);
        }
    }

    private void saveConferenceUser(ConferenceRequestDto conferenceRequestDto, UserDetailsImpl userDetails, Conference saveConference) {
        //saveLeaderUserConference(userDetails, saveConference);

        for (UserIdRequestDto userIdRequestDto: conferenceRequestDto.getMember()) {
            User findUser = userRepository.findById(userIdRequestDto.getUserId()).orElseThrow(
                    () -> new NullPointerException("해당 하는 아이디의 유저가 없습니다.")
            );

            UserConferenceMember userConferenceLeader = new UserConferenceMember(findUser, saveConference);

            UserConferenceMember saveUser = userConferenceMemberRepository.save(userConferenceLeader);
        }
    }

    private void saveLeaderUserConference(UserDetailsImpl userDetails, Conference saveConference) {
        User leader = userRepository.findById(userDetails.getUserId()).orElseThrow(
                () -> new NullPointerException("해당 하는 아이디의 유저가 없습니다.")
        );

        UserConferenceMember userConferenceLeader = new UserConferenceMember(leader, saveConference);
    }

    @Override
    public void delete(Long conferenceId) {
        conferenceRepository.deleteById(conferenceId);
    }

    @Override
    @Transactional
    public void update(Long conferenceId, ConferenceUpdateRequestDto conferenceUpdateRequestDto) {
        Conference findConference = conferenceRepository.findById(conferenceId).orElseThrow(
                () -> new NullPointerException("해당 하는 아이디의 회의글이 없습니다.")
        );

        findConference.setTitle(conferenceUpdateRequestDto.getTitle());
        findConference.setContents(conferenceUpdateRequestDto.getContents());
        findConference.setPurpose(conferenceUpdateRequestDto.getPurpose());

    }

}

