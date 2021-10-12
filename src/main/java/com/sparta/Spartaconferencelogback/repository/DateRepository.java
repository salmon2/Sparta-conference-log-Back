package com.sparta.Spartaconferencelogback.repository;

import com.sparta.Spartaconferencelogback.domain.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
    public Optional<Date> findAllByYearAndMonth(Long year, Long month);
}
