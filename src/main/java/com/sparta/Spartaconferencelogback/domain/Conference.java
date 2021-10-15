package com.sparta.Spartaconferencelogback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Conference extends Timestamped{
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String purpose;

    private String contents;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "dateId")
    @JsonIgnore
    private Date date;

    @OneToMany(mappedBy = "conference", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attendance> attendanceList = new ArrayList<>();


    public Conference(String title, String purpose, String contents, Date date) {
        this.title = title;
        this.purpose = purpose;
        this.contents = contents;
        this.date = date;
    }
}
