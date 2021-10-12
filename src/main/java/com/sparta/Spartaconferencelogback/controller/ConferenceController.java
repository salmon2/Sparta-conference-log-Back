package com.sparta.Spartaconferencelogback.controller;

import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.dto.ConferenceRequestDto;
import com.sparta.Spartaconferencelogback.dto.ResponseMsg;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import com.sparta.Spartaconferencelogback.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceService conferenceService;

    @GetMapping("/test")
    public ResponseMsg test(){
        ResponseMsg responseMsg = new ResponseMsg("테스트");
        return responseMsg;
    }

    @PostMapping("/conference")
    public ResponseMsg conferenceSave(@RequestBody ConferenceRequestDto conferenceRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Conference newConference = conferenceService.save(conferenceRequestDto, userDetails);

        ResponseMsg responseMsg = new ResponseMsg("회의 저장 성공");
        return responseMsg;
    }

}
