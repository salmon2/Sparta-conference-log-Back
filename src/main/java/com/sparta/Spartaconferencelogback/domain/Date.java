package com.sparta.Spartaconferencelogback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Date {

    @Id @GeneratedValue
    private Long id;

    private Long year;

    private Long month;

    private Long day;

    private Long hour;

    private Long minutes;

    @OneToMany(mappedBy = "date", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Conference> conferences = new ArrayList<>();

    public Date(Long year, Long month, Long day, Long hour, Long minutes) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
    }

    public static Date StringToDate(String date){
        String data[] = date.split(" ");

        String year = data[0].split("-")[0];
        String month = data[0].split("-")[1];
        String day = data[0].split("-")[2];

        String hour = data[1].split(":")[0];
        String minutes = data[1].split(":")[1];

        Date newDate = new Date(Long.parseLong(year), Long.parseLong(month), Long.parseLong(day),
                Long.parseLong(hour), Long.parseLong(minutes));
        return newDate;
    }
}



