package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private  Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)    //EnumType을 ORDINAL로 하면 숫자로 입력된다. 나중에 Enum이 추가되면 문제가 발생한다._ORDINAL은 절대 사용하지말자.
    private DeliveryStatus status;  //READY, COMP
}
