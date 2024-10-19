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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.changeTeam(team);
            team.addMember(member);
            em.persist(member);


            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("findTeam = " + findTeam);

            tx.commit();
        } catch (Exception e) {
            System.out.println("e" + e.toString());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
