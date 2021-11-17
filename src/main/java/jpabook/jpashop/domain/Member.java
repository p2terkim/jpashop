package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") //컬럼명과 변수명 다르기 때문에 컬럼명을 지정해줘야한다.
    private Long id;

    private String name;

    @Embedded   //내장 타입을 포함
    private  Address address;

    @OneToMany(mappedBy = "member") //member : order 는 1:N 관계이다. / 연관관계 주인을 알려주기위해 'mappedBy' 속성 사용
    private List<Order> orders = new ArrayList<>();

    //--orders 초기화 다른 방법_이 방법도 있지만 지금 하고있는 방법이 BEST--//
//    @OneToMany(mappedBy = "member") //member : order 는 1:N 관계이다. / 연관관계 주인을 알려주기위해 'mappedBy' 속성 사용
//    private List<Order> orders;
//
//    public Member() {
//        orders = new ArrayList<>();
//    }

}
