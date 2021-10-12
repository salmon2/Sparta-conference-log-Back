package com.sparta.Spartaconferencelogback.repository;

import com.sparta.Spartaconferencelogback.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
