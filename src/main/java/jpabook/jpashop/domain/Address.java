package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * 주소(Address) - 값(임베디드, 내장) 타입이다._@Embeddable 사용
 *              - 회원(Member)과 배송(Delivery)에서 사용한다.
 *              - 값 타입은 변경 불가능하게 설계해야 한다._Setter제거
 *              - 생성자에서 값을 모두 초기화한다._변경불가
 *              - 기본생성자를 public 또는 protected로 설정해야한다._protected가 그나마 더 안전하다.
 *
 */
@Embeddable
@Getter
public class  Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
