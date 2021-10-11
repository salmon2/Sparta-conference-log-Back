package com.sparta.Spartaconferencelogback.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Group_")
public class Group {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<userGroup> userGroups = new ArrayList<>();
}
