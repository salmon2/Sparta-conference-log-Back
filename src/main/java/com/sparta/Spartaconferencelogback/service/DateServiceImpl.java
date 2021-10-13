package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.*;
import com.sparta.Spartaconferencelogback.dto.*;
import com.sparta.Spartaconferencelogback.repository.DateRepository;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DateServiceImpl implements DateService {

    private final DateRepository dateRepository;

    //모든 회의 목록 조회(그 달의 모든 회의 목록)
    public DateCountResponseDto getAllConferenceThatMonth(Long year, Long month) {
        Optional<Date> date = dateRepository.findAllByYearAndMonth(year, month);

        List<DayCountResponseDto> dayCountList = new ArrayList<>();

        int count = 0;                      //해당 날짜에 존재하는 회의 개수

        if(date.isPresent()) {
            List<Conference> conferenceList = date.get().getConferences();
            count = conferenceList.size();

            for(int i=0; i<conferenceList.size(); i++) {
                Conference conference = conferenceList.get(i);
                Long day = conference.getDate().getDay();   //일

                DayCountResponseDto dayCountResponseDto = new DayCountResponseDto(day, count);
                dayCountList.add(dayCountResponseDto);
            }
        }

        DateCountResponseDto responseDto = new DateCountResponseDto(year, month, dayCountList);
        return responseDto;
    }

    //내가 참여한 일정만 보기
    public DateCountResponseDto getMyConferenceThatMonth(Long year, Long month, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId(); //로그인한 계정 userId

        Optional<Date> date = dateRepository.findAllByYearAndMonth(year, month);

        List<DayCountResponseDto> dayCountList = new ArrayList<>();

        if(date.isPresent()) {
            List<Conference> conferenceList = date.get().getConferences();

            for(int i=0; i<conferenceList.size(); i++) {
                Conference conference = conferenceList.get(i);
                Long day = conference.getDate().getDay();   //일
                //회의에 포함된 사람들
                List<UserConferenceMember> userConferenceMemberList = conference.getMember();

                int count = 0;                      //해당 날짜에 존재하는 회의 개수
                //현재 회의에 포함된 사람들을 하나씩 꺼내기
                for(UserConferenceMember member: userConferenceMemberList) {
                    //로그인한 사람과 회의에 포함된 사람들 중에서 비교했을 때 로그인한 계정이 존재하면
                    if(userId.equals(member.getUser().getId())) {
                        count++; //카운트 세기
                    }
                }
                DayCountResponseDto dayCountResponseDto = new DayCountResponseDto(day, count);
                dayCountList.add(dayCountResponseDto);
            }
        }
        DateCountResponseDto responseDto = new DateCountResponseDto(year, month, dayCountList);
        return responseDto;
    }

    //해당 날짜에 등록되어있는 회의 리스트 조회
    public DateListResponseDto getAllConferenceThatDate(Long year, Long month, Long day, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId(); //로그인한 계정 userId

        Optional<Date> dateList = dateRepository.findAllByYearAndMonth(year, month);
        Long conferenceId = 0L;     //회의 번호
        String title = "";          //회의 제목
        String leader = "";         //회의를 만든 사람 nickname
        String date = "";           //날짜(YYYY/MM/DD)

        List<ConferenceListResponseDto> conferenceListResponseDtoList = new ArrayList<>();

        if(dateList.isPresent()) {
            List<Conference> conferenceList = dateList.get().getConferences();
            for(int i=0; i<conferenceList.size(); i++) {
                Conference conference = conferenceList.get(i);
                conferenceId = conference.getId();
                title = conference.getTitle();

                //List<UserConferenceAttendance>의 가장 첫번째에 회의를 만든 사람 데이터가 담겨있음
                User leaderUser = conference.getMember().get(0).getUser();
                leader = leaderUser.getNickname();

                date = year + "/" + month + "/" + day;

                ConferenceListResponseDto conferenceListResponseDto =
                                        new ConferenceListResponseDto(conferenceId, title, leader, date);
                conferenceListResponseDtoList.add(conferenceListResponseDto);
            }
        }

        DateListResponseDto responseDto = new DateListResponseDto(conferenceListResponseDtoList);
        return responseDto;
    }

    //내가 참여한 회의 리스트 조회
    public DateListResponseDto getMyConferenceThatDate(Long year, Long month, Long day, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId(); //로그인한 계정 userId

        Optional<Date> dateList = dateRepository.findAllByYearAndMonth(year, month);
        Long conferenceId = 0L;     //회의 번호
        String title = "";          //회의 제목
        String leader = "";         //회의를 만든 사람 nickname
        String date = "";           //날짜(YYYY/MM/DD)

        List<ConferenceListResponseDto> conferenceListResponseDtoList = new ArrayList<>();

        if(dateList.isPresent()) {
            List<Conference> conferenceList = dateList.get().getConferences();
            for(int i=0; i<conferenceList.size(); i++) {
                Conference conference = conferenceList.get(i);
                conferenceId = conference.getId();
                title = conference.getTitle();

                //List<UserConferenceAttendance>의 가장 첫번째에 회의를 만든 사람 데이터가 담겨있음
                User leaderUser = conference.getMember().get(0).getUser();
                leader = leaderUser.getNickname();

                date = year + "/" + month + "/" + day;

                List<UserConferenceMember> userConferenceMemberList = conference.getMember();

                //현재 회의에 포함된 사람들을 하나씩 꺼내기
                for(UserConferenceMember member: userConferenceMemberList) {
                    //로그인한 사람과 회의에 포함된 사람들 중에서 비교했을 때 로그인한 계정이 존재하면
                    if(userId.equals(member.getUser().getId())) {
                        ConferenceListResponseDto conferenceListResponseDto =
                                new ConferenceListResponseDto(conferenceId, title, leader, date);
                        conferenceListResponseDtoList.add(conferenceListResponseDto);
                    }
                }

            }
        }
        DateListResponseDto responseDto = new DateListResponseDto(conferenceListResponseDtoList);
        return responseDto;
    }

}
