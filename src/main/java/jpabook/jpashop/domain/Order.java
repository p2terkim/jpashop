package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //테이블명과 엔티티명이 다르기 때문에 테이블명을 지정해줘야한다.
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //order : member 는 N:1 관계이다.
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)  //order : orderitem 은 1:N 관계이다.
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;    //주문시간_LocalDateTime을 사용하면 Hibernate가 지원해준다.

    @Enumerated(EnumType.STRING)
    private OderStatus status;  //주문상태 [ORDER, CANCEL]

    //==cascade = CascadeType.ALL 설명==//
//    1.기존
//    persist(orderItemA)
//    persist(orderItemB)
//    persist(orderItemC)
//    persist(order)

//    2.cascade = CascadeType.ALL 적용시
//    persist(order)

//    1번과 2번은 같다.

    //==연관관계 메서드==//
//    1.원래라면 아래의 코드이지만 실수로 코드를 빼먹을 수 있다,
//    public static void main(String[] args) {
//        Member member = new Member();
//        Order order = new Order();
//
//        member.getOrders().add(order);
//        order.setMember(member);
//    }
//
//    2.이런 실수를 하지 않기 위해 아래처럼 작성한다.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void  setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }



}
