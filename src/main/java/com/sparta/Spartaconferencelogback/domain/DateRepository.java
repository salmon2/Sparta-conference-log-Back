package com.sparta.Spartaconferencelogback.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
    Optional<Date> findByYearAndMonthAndDay(String year, String month, String day);
}


