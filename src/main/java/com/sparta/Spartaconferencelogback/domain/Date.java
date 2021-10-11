package com.sparta.Spartaconferencelogback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Date {

    @Id @GeneratedValue
    private Long id;

    private String year;

    private String month;

    private String day;

    @OneToOne
    @JoinColumn(name = "conferenceId")
    @JsonIgnore
    private Conference conference;

}
