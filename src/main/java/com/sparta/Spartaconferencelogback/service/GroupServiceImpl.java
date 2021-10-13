package com.sparta.Spartaconferencelogback.service;

import com.sparta.Spartaconferencelogback.domain.Group;
import com.sparta.Spartaconferencelogback.dto.GroupList;
import com.sparta.Spartaconferencelogback.dto.GroupRequsetDto;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService{

    @Override
    public Group save(GroupRequsetDto groupRequsetDto, UserDetailsImpl userDetails) {
        return null;
    }

    @Override
    public GroupList getGroupList() {
        return null;
    }
}
