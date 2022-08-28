package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);     //영속성 컨텍스트가 멤버 엔티티 넣고 트랜잭션이 커밋될때 디비
    }                           //에 반영 디비에 인서트 쿼리가 날라감

    public Member findOne(Long id) {
        return em.find(Member.class, id);   //jpa에 find메서드 첫번째타입 두번째는 primary key
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();           //jpql사용 문법은 sql랑 비슷한데 from의 대상이
    }                                       //table이아니라 entity

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }



}
