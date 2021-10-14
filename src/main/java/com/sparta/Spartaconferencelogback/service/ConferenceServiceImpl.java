package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.*;
import com.sparta.Spartaconferencelogback.dto.*;
import com.sparta.Spartaconferencelogback.dto.confefencedtos.ConferenceDetailResponseDto;
import com.sparta.Spartaconferencelogback.dto.confefencedtos.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.dto.confefencedtos.ConferenceUpdateRequestDto;
import com.sparta.Spartaconferencelogback.dto.userdtos.UserIdRequestDto;
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
    public Conference update(Long conferenceId, ConferenceUpdateRequestDto conferenceUpdateRequestDto) {
        Conference findConference = conferenceRepository.findById(conferenceId).orElseThrow(
                () -> new NullPointerException("해당 하는 아이디의 회의글이 없습니다.")
        );

        findConference.setTitle(conferenceUpdateRequestDto.getTitle());
        findConference.setContents(conferenceUpdateRequestDto.getContents());
        findConference.setPurpose(conferenceUpdateRequestDto.getPurpose());

        return findConference;
    }

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

        //회의 날짜
        Long dateMonth = conference.getDate().getMonth();
        Long dateYear = conference.getDate().getYear();
        Long dateDay = conference.getDate().getDay();
        String date = dateYear + "/" + dateMonth + "/" + dateDay;

        //회의 시간
        String hour = String.valueOf(conference.getDate().getHour());
        String minute = String.valueOf(conference.getDate().getMinutes());
        String second = String.valueOf(0L);

        TimeResponseDto time = new TimeResponseDto(hour, minute, second);

        //수정 날짜
        LocalDateTime modifiedAt = conference.getModifiedAt();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); //날짜 출력 형식
        String lastModifiedAt = modifiedAt.format(dateFormatter);     //수정 시간

        //회의 내용
        String contents = conference.getContents();

        ConferenceDetailResponseDto responseDto =
                new ConferenceDetailResponseDto(conferenceId, title, actualJoinedPeople,
                                                        writer, date, time, lastModifiedAt, contents);

        return responseDto;
    }

}

