package com.sparta.Spartaconferencelogback;

import com.sparta.Spartaconferencelogback.domain.Attendance;
import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.domain.Date;
import com.sparta.Spartaconferencelogback.domain.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@NoArgsConstructor
@Component
public class SimpleListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationStartedEvent event) {
        EntityManager em = entityManagerFactory.createEntityManager();


        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        User user1 = new User("user1", "user1", passwordEncoder.encode("asdf"));

        User user2 = new User("user2", "user2", passwordEncoder.encode("asdf"));
        User user3 = new User("user3", "user3", passwordEncoder.encode("asdf"));
        User root = new User("root", "user3", passwordEncoder.encode("asdf"));

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);

        for(int i = 0; i<30; i++){
            Date date = new Date(2021L, 10L, i + 1L, 10L, 00L);
            em.persist(date);

            for(int j = 0; j<2; j++) {
                makeConference(em, user1, user2, date, "회의 " + j);

                if( i == 15){
                    makeConference(em, user1, user2, date, "테스트테스트");
                    makeConference(em, user3, user2, date, "테스트테스트");
                }
            }
        }



        em.getTransaction().commit();

    }

    private void makeConference(EntityManager em, User user1, User user2, Date date, String 테스트테스트) {
        Conference conference3 = new Conference(테스트테스트, "스터디", "회의 시러용", date);
        em.persist(conference3);

        //회의 사람들
        Attendance newAttendance1 = new Attendance(user1.getUsername(), conference3);
        em.persist(newAttendance1);

        Attendance newAttendance2 = new Attendance(user2.getUsername(), conference3);
        em.persist(newAttendance2);


    }
}
