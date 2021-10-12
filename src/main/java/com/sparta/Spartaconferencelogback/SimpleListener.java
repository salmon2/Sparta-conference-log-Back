package com.sparta.Spartaconferencelogback;

import com.sparta.Spartaconferencelogback.domain.Conference;
import com.sparta.Spartaconferencelogback.domain.Date;
import com.sparta.Spartaconferencelogback.domain.User;
import com.sparta.Spartaconferencelogback.domain.UserConferenceMember;
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

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationStartedEvent event) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        User user1 = new User("user1", "user1", "asdf");
        User user2 = new User("user2", "user2", "asdf");

        em.persist(user1);
        em.persist(user2);

        for(int i = 0; i<30; i++){
            Date date = new Date(2021L, 10L, i + 1L);
            em.persist(date);

            for(int j = 0; j<2; j++) {
                Conference conference = new Conference("회의 " + j, "스터디", "회의 시러용", date);
                em.persist(conference);

                //회의 사람들
                UserConferenceMember userConferenceMember1 = new UserConferenceMember(user1, conference);
                em.persist(userConferenceMember1);

                UserConferenceMember userConferenceMember2 = new UserConferenceMember(user2, conference);
                em.persist(userConferenceMember2);
            }
        }



        em.getTransaction().commit();

    }
}
