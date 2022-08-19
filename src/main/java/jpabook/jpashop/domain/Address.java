package jpabook.jpashop.domain;

import lombok.Getter;


import javax.persistence.Embeddable;

@Embeddable     //jpa 내장타입이기 때문에, jpa 어딘가에 내장될 수 있다
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {       //jpa 스팩상 만든 기본 생성자(직접 사용할일 x)

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
