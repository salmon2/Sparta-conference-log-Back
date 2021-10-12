package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.dto.DateCountResponseDto;
import com.sparta.Spartaconferencelogback.dto.DateListResponseDto;

public interface DateService {
    //모든 회의 목록 조회(그 달의 모든 회의 목록 개수)
    public DateCountResponseDto getAllConferenceThatMonth(Long year, Long month);

    //내가 참여한 일정만 보기
    public DateCountResponseDto getMyConferenceThatMonth(Long year, Long month, Long userId);

    // //해당 날짜에 등록되어있는 회의 리스트 조회
    public DateListResponseDto getAllConferenceThatDate(Long year, Long month, Long day, Long userId);

    //내가 참여한 회의 리스트 조회
    public DateListResponseDto getMyConferenceThatDate(Long year, Long month, Long day, Long userId);

}
