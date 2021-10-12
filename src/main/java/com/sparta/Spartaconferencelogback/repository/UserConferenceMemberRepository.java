package com.sparta.Spartaconferencelogback.repository;

import com.sparta.Spartaconferencelogback.domain.UserConferenceMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserConferenceMemberRepository extends JpaRepository<UserConferenceMember, Long> {
}
