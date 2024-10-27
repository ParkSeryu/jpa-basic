package hellojpa;

import jakarta.persistence.*;
import java.util.List;
import org.hibernate.Hibernate;

public class JpaMain {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      Team team = new Team();
      team.setName("teamA");
      em.persist(team);

      Team team2 = new Team();
      team.setName("teamB");
      em.persist(team2);

      Member member1 = new Member();
      member1.setUsername("member1");
      member1.setTeam(team);
      em.persist(member1);

      Member member2= new Member();
      member2.setUsername("member2");
      member2.setTeam(team2);
      em.persist(member2);


      em.flush();
      em.clear();

//      Member m = em.find(Member.class, member1.getId());

      List<Member> members = em.createQuery("select m from Member m",
          Member.class).getResultList();

//      for (Member member : members) {
//        System.out.println("member = " + member.getTeam().getName());
//      }

      //SQL : select * from Member
      //SQL : select * from Team where TEAM_ID = xxx





      tx.commit();
    } catch (Exception e) {
      System.out.println("e " + e.toString());
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

  private static void printMember(Member member) {
    System.out.println("member.getUsername() = " + member.getUsername());

  }

  private static void printMemberAndTeam(Member member) {
    String username = member.getUsername();
    System.out.println("username = " + username);

    Team team = member.getTeam();
    System.out.println("team = " + team.getName());


  }

}
