package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.domain.Date;
import com.sparta.Spartaconferencelogback.domain.UserConferenceMember;
import com.sparta.Spartaconferencelogback.dto.DateCountResponseDto;
import com.sparta.Spartaconferencelogback.repository.DateRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DateServiceImpl implements DateService {

    private final DateRepository dateRepository;

    //모든 회의 목록 조회(그 달의 모든 회의 목록)
    public DateCountResponseDto getConferenceThatMonth(Long year, Long month) {
        Optional<Date> date = dateRepository.findAllByYearAndMonth(year, month);
        //Long year = date.get().getYear();   //년
        //Long month = date.get().getMonth(); //월
        Long day = date.get().getDay();     //일
        int count = 0;                      //해당 날짜에 존재하는 회의 개수

        if(date.isPresent()) {
            List<Conference> conference = date.get().getConferences();
            count = conference.size();
        }

        JSONObject data = new JSONObject();
        data.put("day", day);
        data.put("count", count);

        DateCountResponseDto responseDto = new DateCountResponseDto(year, month, data);
        return responseDto;
    }

    //내가 참여한 일정만 보기
    public DateCountResponseDto getMyConference(Long year, Long month, String userId) {
        Optional<Date> date = dateRepository.findAllByYearAndMonth(year, month);
        List<Conference> conferenceList = date.get().getConferences();

        Long day = date.get().getDay();     //일
        int count = 0;                      //해당 날짜에 존재하는 회의 개수

        JSONObject data = new JSONObject();
        data.put("day", day);
        data.put("count", count);


        for(int i=0; i<conferenceList.size(); i++) {
            Conference conference = conferenceList.get(i);
            //회의에 포함된 사람들
            List<UserConferenceMember> userConferenceMemberList = conference.getMember();
            //현재 회의에 포함된 사람들을 하나씩 꺼내기
            for(UserConferenceMember member: userConferenceMemberList) {
                //로그인한 사람과 회의에 포함된 사람들 중에서 비교했을 때 로그인한 계정이 존재하면
                if(userId.equals(member.getUser())) {
                    count++;
                }
            }
        }
        DateCountResponseDto dateReadResponseDto = new DateCountResponseDto(year, month, data);
        return dateReadResponseDto;
    }

//    //전체 회의 리스트 조회(그 달의 모든 회의 목록)
//    public DateListResponseDto getAllConferenceThatMonth(Long year, Long month) {
//        dateRepository.findAllByYearAndMonth(year, month);
//
//    }
}
