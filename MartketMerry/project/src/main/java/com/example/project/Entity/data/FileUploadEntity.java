package com.example.project.Entity.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter //@Setter를 안쓰는 이유 : 파일 받아들이고 넘기기만 할거라
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 생성
@NoArgsConstructor  //파라미터가 없는 기본 생성자 생성
public class FileUploadEntity {
    @Id
    @GeneratedValue //GeneratedValue란?
    private Long    id;

    private String  uuid;

    private String  contentType;

    private String  name;

    private String  originalFilename;

    private Long    itemSeq;
//    private Long    itemId;
}
