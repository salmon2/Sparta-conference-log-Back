package com.sparta.Spartaconferencelogback.repository;

import com.sparta.Spartaconferencelogback.domain.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
    public List<Date> findAllByYearAndMonthAndDay(Long year, Long month, Long day);
    public List<Date> findAllByYearAndMonth(Long year, Long month);
}
