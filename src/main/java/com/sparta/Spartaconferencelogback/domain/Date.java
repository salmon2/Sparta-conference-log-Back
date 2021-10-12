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

    @OneToMany(mappedBy = "date", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Conference> conferences = new ArrayList<>();

    public Date(Long year, Long month, Long day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}



