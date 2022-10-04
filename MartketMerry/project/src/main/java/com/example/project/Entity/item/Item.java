package com.example.project.Entity.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
//@Column //왜 빨간줄?
public class Item {
    //  엔티티란? DB와 1:1 매칭되는 것
//    카트 안에 들어갈 db이름
//    상품 사진(서버에 저장하니깐 링크로 저장?),상품명, 상품 설명, 가격, 배송 선택(필요한가),
//    업체명, 포장타입, 판매단위, 중량/용량, 원산지, 유통기한, 재고

    //
    @Id
    private String  seq;        //상품 번호

    private String  photo; //상품 사진 저장한 url

    private String  name;       //상품 이름

    private String  text;       //상품 설명

    private int     price;      //상품 가격
    
    private String  delivery;   //배송 선택

    private String  category1;  //상품 분류1

    private String  category2;  //상품 분류2

    private String  seller;     //업체,판매자명

    private String  packing;    //포장타입

    private Date    shelfLife;  //유통기한

    private int     stock;      //재고 수량

//    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ    //

    private String detailText;    //상세 설명

    private String detailPhto;    //상세 설명

}
