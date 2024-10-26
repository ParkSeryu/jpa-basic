package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

public class JpaMain {

  public static void main(String[] args) {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      Member member1 = new Member();
      member1.setUsername("hello");
      em.persist(member1);

      Member member2 = new Member();
      member2.setUsername("member2");
      em.persist(member2);

      em.flush();
      em.clear();

      Member refMember = em.getReference(Member.class, member1.getId());
      System.out.println("refMember = " + refMember.getClass()); // Proxy
      Hibernate.initialize(refMember); // 강제초기화


      System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

      tx.commit();
    } catch (Exception e) {
      System.out.println("e" + e.toString());
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
