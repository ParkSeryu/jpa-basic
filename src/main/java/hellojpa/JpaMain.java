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

      Member member = new Member();
      member.setUsername("hello");
      member.setHomeAddress(new Address("city", "street", "10000e"));
      member.setWorkPeriod(new Period());

      em.persist(member);

      tx.commit();
    } catch (Exception e) {
      System.out.println("e " + e.toString());
      tx.rollback();
    } finally {
      em.close();
    }

    emf.close();
  }

}
