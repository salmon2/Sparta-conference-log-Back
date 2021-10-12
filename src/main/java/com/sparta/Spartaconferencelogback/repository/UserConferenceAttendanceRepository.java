package com.sparta.Spartaconferencelogback.repository;


import com.sparta.Spartaconferencelogback.domain.UserConferenceAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserConferenceAttendanceRepository extends JpaRepository<UserConferenceAttendance, Long> {
}
