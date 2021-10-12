package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.domain.User;
import com.sparta.Spartaconferencelogback.domain.UserConferenceAttendance;
import com.sparta.Spartaconferencelogback.dto.ConferenceDetailResponseDto;
import com.sparta.Spartaconferencelogback.dto.TimeResponseDto;
import com.sparta.Spartaconferencelogback.repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConferenceServiceImpl {

    private final ConferenceRepository conferenceRepository;

    //회의 단건 정보 조회
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
