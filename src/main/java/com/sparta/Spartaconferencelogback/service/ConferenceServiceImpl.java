package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.*;
import com.sparta.Spartaconferencelogback.dto.*;
import com.sparta.Spartaconferencelogback.repository.*;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    public ConferenceDetailResponseDto update(Long conferenceId, ConferenceUpdateRequestDto conferenceUpdateRequestDto) {
        Conference findConference = conferenceRepository.findById(conferenceId).orElseThrow(
                () -> new NullPointerException("해당 하는 아이디의 회의글이 없습니다.")
        );

        findConference.setTitle(conferenceUpdateRequestDto.getTitle());
        findConference.setContents(conferenceUpdateRequestDto.getContents());
        findConference.setPurpose(conferenceUpdateRequestDto.getPurpose());


    //회의 단건 정보 조회
    @Override
    public ConferenceDetailResponseDto getConferenceDetail(Long conferenceId) {
        String writer = "";     //회의 만든 사람

        Conference conference = conferenceRepository.findById(conferenceId).orElseThrow(
                () -> new NullPointerException("해당 아이디인 회의록 정보가 없습니다.")
        );

        String title = conference.getTitle();
        List<UserConferenceAttendance> actualJoinedPeople = conference.getAttendance();

        //List<UserConferenceAttendance>의 가장 첫번째에 회의를 만든 사람 데이터가 담겨있음
        User writerUser = conference.getMember().get(0).getUser();
        writer = writerUser.getNickname();      //회의 만든 사람 nickname

        //생성 날짜 형식 변경
        LocalDateTime createdAt = conference.getCreatedAt();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); //날짜 출력 형식
        String date = createdAt.format(dateFormatter);      //생성 날짜

        //생성 시간 형식 변경
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); //시간 출력 형식
        String timeHhMmSs = createdAt.format(timeFormatter);
        String[] timeSplit = timeHhMmSs.split(":");  //생성 시간

        String hour = timeSplit[0];     //생성 시간 시
        String minute = timeSplit[1];   //생성 시간 분
        String second = timeSplit[2];   //생성 시간 초

        TimeResponseDto time = new TimeResponseDto(hour, minute, second);

        //수정 날짜
        LocalDateTime modifiedAt = conference.getModifiedAt();
        String lastModifiedAt = modifiedAt.format(dateFormatter);     //수정 시간

        //회의 내용
        String contents = conference.getContents();

        ConferenceDetailResponseDto responseDto =
                new ConferenceDetailResponseDto(conferenceId, title, actualJoinedPeople,
                                                        writer, date, time, lastModifiedAt, contents);

        return responseDto;
    }

}

