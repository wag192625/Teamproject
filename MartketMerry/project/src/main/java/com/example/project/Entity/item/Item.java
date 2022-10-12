package com.example.project.Entity.item;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.Date;

//@ToString         //왜 쓰는지 헷갈림
@Entity
@Getter
@Setter
@NoArgsConstructor  //파라미터가 없는 기본 생성자 생성
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 생성
@Builder
@ToString
public class Item {
//  엔티티란? DB와 1:1 매칭되는 것
//    카트 안에 들어갈 db 이름 (@Table 지정 안할 시 클래스 이름으로 매핑)
//    상품 사진(서버에 저장하니깐 링크로 저장?),상품명, 상품 설명, 가격, 배송 선택(필요한가),
//    업체명, 포장타입, 판매단위, 중량/용량, 원산지, 유통기한, 재고

//    pirvate String id; findBySeq가 없어서 Id로 바꿔야 하나? (바꿈)
   //기본 키(PK Key) 지정
    @Id     // 기본 키의 자동 생성 전략 (?)
    @GeneratedValue
    private Long  id;
//    private String  seq;            //상품 번호

//    @Column     //컬럼이란? 객체 필드를 테이블의 컬럼에 매핑시켜줌
//    private String  photo;          //상품 사진 저장한 url
    @Column
    private String  itemName;       //상품 이름
    @Column
    private String  itemText;       //상품 설명
    @Column
    private int     price;          //상품 가격
    @Column
    private String  delivery;       //배송 선택
    @Column
    private String  mainCategory;   //대분류
    @Column
    private String  subCategory;    //소분류
    @Column
    private String  seller;         //업체,판매자명
    @Column
    private String  packing;        //포장타입
    @Column
    private String   shelfLife;      //유통기한
    //타입이 안맞는다 함 (int에서 String으로 바꾸니 됨)
    @Column
    private int     stock;          //재고 수량

//    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ    //
    @Column
    private String detailText;      //상세 설명

//    @Column
//    private String detailPhoto;     //상세 사진
    //타입이 안맞는다 함


}
