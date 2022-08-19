package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue     //id를 시퀀스값 으로 쓰겠다
    @Column(name = "member_id")     //외래키가 member_id라서
    private Long id;

    private String name;

    @Embedded       //내장타입을 포함했다.
    private Address address;

    @OneToMany(mappedBy = "member")    //멤버와 오더는 일대다 관계, 나는 order table에있는 member필드에 의해서 매핑된거다 (읽기전용)
    private List<Order> orders = new ArrayList<>();

}
