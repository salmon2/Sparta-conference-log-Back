package com.sparta.Spartaconferencelogback.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String password;

    @OneToMany(mappedBy = "user", fetch = LAZY, cascade = ALL)
    private List<userGroup> joinedGroup = new ArrayList<>();

    @OneToMany

}
