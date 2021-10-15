package com.sparta.Spartaconferencelogback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attendance {
    @Id
    @GeneratedValue
    private Long id;


    private String username;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "conferenceId")
    @JsonIgnore
    private Conference conference;


    public Attendance(String username, Conference conference) {
        this.username = username;
        this.conference = conference;
    }
}
