package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.dto.DateCountResponseDto;
import com.sparta.Spartaconferencelogback.dto.DateListResponseDto;

public interface DateService {
    //모든 회의 목록 조회(그 달의 모든 회의 목록 개수)
    public DateCountResponseDto getConferenceThatMonth(Long year, Long month);

    //내가 참여한 일정만 보기
    public DateCountResponseDto getMyConference(Long year, Long month, String userId);

    //전체 회의 리스트 조회(그 달의 모든 회의 목록)
    //public DateListResponseDto getAllConferenceThatMonth(Long year, Long month, String userId);
}
