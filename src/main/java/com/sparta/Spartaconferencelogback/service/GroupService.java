package com.sparta.Spartaconferencelogback.service;


import com.sparta.Spartaconferencelogback.domain.Group;
import com.sparta.Spartaconferencelogback.dto.GroupList;
import com.sparta.Spartaconferencelogback.dto.GroupRequsetDto;
import com.sparta.Spartaconferencelogback.security.UserDetailsImpl;

public interface GroupService {

    Group save(GroupRequsetDto groupRequsetDto, UserDetailsImpl userDetails);
    public GroupList getGroupList();
}
