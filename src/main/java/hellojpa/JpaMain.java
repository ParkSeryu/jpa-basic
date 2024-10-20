package hellojpa;

import jakarta.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = saveMember(em);

            Team team = new Team();
            team.setName("teamA");
            //
            team.getMembers().add(member);

            em.persist(team);

            tx.commit();
        } catch (Exception e) {
            System.out.println("e" + e.toString());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static Member saveMember(EntityManager em) {
        Member member = new Member();
        member.setUsername("member1");
        em.persist(member);
        return member;
    }
}
