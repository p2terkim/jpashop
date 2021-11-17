package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")    //SingleTable 전략이므로 Dtype 설정을 해준다.
@Getter @Setter
public class Movie extends Item {

    private String director;
    private String actor;

}
