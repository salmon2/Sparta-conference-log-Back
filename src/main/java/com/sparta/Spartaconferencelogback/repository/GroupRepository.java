package com.sparta.Spartaconferencelogback.repository;

import com.sparta.Spartaconferencelogback.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Override
    List<Group> findAll();
}
