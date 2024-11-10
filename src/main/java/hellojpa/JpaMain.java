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

      Address address = new Address("city", "street", "10000");

      Member member = new Member();
      member.setUsername("member1");
      member.setHomeAddress(address);
      em.persist(member);

      Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

      Member member2 = new Member();
      member2.setUsername("member2");
      member2.setHomeAddress(copyAddress);
      em.persist(member2);

      member.getHomeAddress().setCity("newCity");

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
