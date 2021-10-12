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
public class UserConferenceAttendance {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "conferenceId")
    @JsonIgnore
    private Conference conferenceAttendance;

}
