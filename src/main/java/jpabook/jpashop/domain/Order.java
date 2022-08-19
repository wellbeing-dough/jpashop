package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")     //관례로 table명이 Order가 되기 때문에
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne      //Order와 Member는 다대일 관계
    @JoinColumn(name = "member_id")     //매핑을 뭐로할거냐 member_id -> 외래키 이름이 memeber_id가 된다
    private Member member;      //Member와 관계 세팅

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne   //하나의 주문은 하나의 배송
    @JoinColumn(name = "delivery_id")   //연관관계의 주인은 order에 delivery
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문 상태 [ORDER, CANCEL]
}
