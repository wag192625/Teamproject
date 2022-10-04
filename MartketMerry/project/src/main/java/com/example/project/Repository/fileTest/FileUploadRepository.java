package com.example.project.Repository.fileTest;

import com.example.project.Entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속 받음
// Entity를 매개체로 CRUD를 가리킴
// <Item, String> = <매개변수 Item (엔티티)와 String(엔티티 내의 기본키의 타입 선언)
// 이후 service 단에서 레파지토리 사용 (JPA의 CRUD를 이용하기 위하여)
public interface FileUploadRepository extends JpaRepository<Item, String> {

}
