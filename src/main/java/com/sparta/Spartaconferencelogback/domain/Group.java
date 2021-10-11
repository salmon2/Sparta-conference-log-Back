package com.sparta.Spartaconferencelogback.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Group {
    @Id @GeneratedValue
    private Long id;

    private String title;
}
