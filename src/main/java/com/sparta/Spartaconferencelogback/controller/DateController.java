package com.sparta.Spartaconferencelogback.controller;


import com.sparta.Spartaconferencelogback.dto.datedtos.DateCountResponseDto;
import com.sparta.Spartaconferencelogback.dto.datedtos.DateListResponseDto;

import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import com.sparta.Spartaconferencelogback.service.DateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags={"날짜에 따른 회의 목록 조회 관련 API"})
public class DateController {

    private final DateService dateService;
//    private final UserRepository userRepository;

    @ApiOperation(value="해당 월의 모든 회의를 날마다 개수로 표현 (완성) ", notes="그 달의 날짜별로 회의 개수를 반환한다.")
    @GetMapping("/main/alllist")
    public DateCountResponseDto GetAllConferenceThatMonth(@RequestParam Long year, @RequestParam Long month) {
        return dateService.getAllConferenceThatMonth(year, month);
    }

    @ApiOperation(value="내가 참여한 일정만 보기 (완성)", notes="그 달의 날짜별로 자신이 참여한 회의 개수를 반환한다.")
    @GetMapping("/main/mylist")
    public DateCountResponseDto getMyConferenceThatMonth(@RequestParam Long year,

                                                         @RequestParam Long month,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails
                                                         ) {

//        User findUser = userRepository.getById(1L);
//        UserDetailsImpl dummyUserDetails = new UserDetailsImpl(findUser);

        return dateService.getMyConferenceThatMonth(year, month, userDetails);
    }


    @ApiOperation(value="해당 날짜 회의 리스트 조회 (완성) ", notes="해당 날짜에 등록되어있는 회의 리스트를 반환한다.")
    @GetMapping("/conference")
    public DateListResponseDto getAllConferenceThatDate(@RequestParam Long year, @RequestParam Long month,
                                                        @RequestParam Long day) {
        DateListResponseDto result = dateService.getAllConferenceThatDate(year, month, day);
        return result;
    }

    //완성
    @ApiOperation(value="내가 참여한 회의 리스트 조회 (완성) ", notes="해당 날짜에 내가 참여한 회의 리스트를 반환한다.")
    @GetMapping("/conference/my")
    public DateListResponseDto getMyConferenceThatDate(@RequestParam Long year,
                                                       @RequestParam Long month,
                                                       @RequestParam Long day,
                                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return dateService.getMyConferenceThatDate(year, month, day, userDetails);
    }


}
