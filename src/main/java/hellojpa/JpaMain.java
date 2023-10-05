package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 에플레이션 전체에서 공유 - 설정 정보의 이름을 가져와서 해당 속성을 토대로 만든다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 엔티티 매니저는 쓰레드간 공유 X(사용하고 버려야 함.)
        EntityManager em = emf.createEntityManager();
        // JPA의 모든 데이터 변경은 트랜젝션 안에서 실행
        EntityTransaction tx = em.getTransaction();

        // 트랜젝션 시작
        tx.begin();

        try {
//            Member findMember = em.find(Member.class, 1L);
            /* SQL - 데이터 베이스 테이블을 대상으로 한 쿼리
            *  JPQL - 엔티티 객체를 대상으로 한 쿼리
            *       - 즉 JPQL은 객체지향 SQL이다. */
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 작동에 문제가 없다면 커밋
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
