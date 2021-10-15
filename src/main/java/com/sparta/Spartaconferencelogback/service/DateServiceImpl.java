package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.*;
import com.sparta.Spartaconferencelogback.dto.confefencedtos.ConferenceListResponseDto;
import com.sparta.Spartaconferencelogback.dto.datedtos.DateCountResponseDto;
import com.sparta.Spartaconferencelogback.dto.datedtos.DateListResponseDto;
import com.sparta.Spartaconferencelogback.dto.datedtos.DayCountResponseDto;
import com.sparta.Spartaconferencelogback.repository.DateRepository;
import com.sparta.Spartaconferencelogback.repository.UserRepository;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DateServiceImpl implements DateService {

    private final DateRepository dateRepository;
    private final UserRepository userRepository;

    //모든 회의 목록 조회(그 달의 모든 회의 목록)
    // 잘돌아감
    public DateCountResponseDto getAllConferenceThatMonth(Long year, Long month) {
        List<Date> date = dateRepository.findAllByYearAndMonth(year, month);

        List<DayCountResponseDto> dayCountList = new ArrayList<>();

        if(date.size() != 0) {
            for (Date oneDate: date) {
                List<Conference> conferences = oneDate.getConferences();
                DayCountResponseDto dayCountResponseDto = new DayCountResponseDto(oneDate.getDay(), conferences.size());
                dayCountList.add(dayCountResponseDto);
            }
        }

        DateCountResponseDto responseDto = new DateCountResponseDto(year, month, dayCountList);
        return responseDto;
    }

    //내가 참여한 일정만 보기
    public DateCountResponseDto getMyConferenceThatMonth(Long year, Long month, UserDetailsImpl userDetails) {
        String username = userDetails.getUsername(); //로그인한 계정 아이디
        int size = 0;
        List<Date> date = dateRepository.findAllByYearAndMonth(year, month);

//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        List<UserConferenceMember> userConferenceMembers = optionalUser.get().getUserConferenceMembers();

        List<DayCountResponseDto> dayCountList = new ArrayList<>();

        for(Date oneDate: date) {
            List<Conference> conferences = oneDate.getConferences();
            size = 0;
            //회의 검사
            for(int i=0; i<conferences.size(); i++) {
                Conference conference = conferences.get(i);
                List<Attendance> members = conference.getAttendanceList();

                //멤버검사
                for(int j=0; j<members.size(); j++) {
                    String memberUsername = members.get(j).getUsername();
                    if(memberUsername.equals(username)) {
                        size++;
                    }
                }
            }

            DayCountResponseDto dayCountResponseDto =
                    new DayCountResponseDto(oneDate.getDay(), size);
            dayCountList.add(dayCountResponseDto);
        }

        DateCountResponseDto responseDto = new DateCountResponseDto(year, month, dayCountList);
        return responseDto;
    }

    //해당 날짜에 등록되어있는 회의 리스트 조회
    public DateListResponseDto getAllConferenceThatDate(Long year, Long month, Long day) {
        List<Date> dateList = dateRepository.findAllByYearAndMonthAndDay(year, month, day);
        Long conferenceId = 0L;     //회의 번호
        String title = "";          //회의 제목
        String leader = "";         //회의를 만든 사람 nickname
        String date = "";           //날짜(YYYY/MM/DD)

        List<ConferenceListResponseDto> conferenceListResponseDtoList = new ArrayList<>();
        if(dateList.size() != 0 ) {
            for (Date onedate: dateList) {
                for (Conference conference: onedate.getConferences()) {
                    conferenceId = conference.getId();
                    title = conference.getTitle();

                    //List<UserConferenceAttendance>의 가장 첫번째에 회의를 만든 사람 데이터가 담겨있음

                    List<Attendance> member = conference.getAttendanceList();
                    Attendance userConferenceMember = member.get(0);
                    leader = userConferenceMember.getUsername();

                    date = year + "/" + month + "/" + day;

                    ConferenceListResponseDto conferenceListResponseDto =
                            new ConferenceListResponseDto(conferenceId, title, leader, date);
                    conferenceListResponseDtoList.add(conferenceListResponseDto);
                }
            }
        }

        DateListResponseDto responseDto = new DateListResponseDto(conferenceListResponseDtoList);
        return responseDto;
    }

    //내가 참여한 회의 리스트 조회
    public DateListResponseDto getMyConferenceThatDate(Long year, Long month, Long day, UserDetailsImpl userDetails) {
        Long userId = userDetails.getUserId(); //로그인한 계정 userId

        List<Date> dateList = dateRepository.findAllByYearAndMonthAndDay(year, month, day);
        String dateString = year + "/" + month + "/" + day;


        List<ConferenceListResponseDto> conferenceList = new ArrayList<>();

        for (Date date: dateList) {
            for (Conference conference : date.getConferences()) {
                String leader = conference.getAttendanceList().get(0).getUsername();
                for (Attendance attendance:conference.getAttendanceList()) {
                    if(attendance.getUsername().equals(userDetails.getUsername())){
                        ConferenceListResponseDto conferenceListResponseDto = new ConferenceListResponseDto(conference.getId(), conference.getTitle(), leader, dateString);
                        conferenceList.add(conferenceListResponseDto);
                    }
                }
            }
        }

        DateListResponseDto responseDto = new DateListResponseDto(conferenceList);
        return responseDto;
    }

}
