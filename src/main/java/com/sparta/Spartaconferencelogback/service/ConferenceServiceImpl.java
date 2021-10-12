package com.sparta.Spartaconferencelogback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConferenceServiceImpl {

//    private final ConferenceRepository conferenceRepository;
//
//    //모든 회의 목록 조회Number
//    public List<Date> findAllConferences(int year, int month) {
//        return conferenceRepository.findAllByYearAndMonth(year, month);
//    }
//
//    //내가 참여한 일정만 보기
//    //List<Conference>에 userId로 검색한 결과를 담아야 하는데 어떻게 하지..?
////    public List<Date> findMySchedule(int year, int month) {
////        return
////    }
//
//    //전체 회의 리스트 조회(그 날짜에 잡혀있는 회의 리스트)
//    public List<Conference> findThatDateConferences(int year, int month, int day) {
//        return conferenceRepository.findByYearAndMonthAndDay(year, month, day);
//    }
//
//    //내가 참여한 회의 리스트 조회
//    //member List에서 userId로 검색한 결과 리스트를 가져와야하는데 어떻게 하지..?
////    public List<Conference> findMyConferences(int year, int month, int day) {
////        return conferenceRepository.findByYearAndMonthAndDayAndUserId(year, month, day);
////    }
//
//
//
//
//    //회의 만들기
//    public Conference createConference(ConferenceRequestDto requestDto) {
//        Conference conference = new Conference(requestDto);
//        conferenceRepository.save(conference);
//        return null;
//    }

}
