package com.example.project.Repository.fileTest;

import com.example.project.Entity.data.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Entity를 매개체로 CRUD를 가리킴
// <FileUploadEntity, Long> = <매개변수 File...(엔티티)와 Long(엔티티 내의 기본키의 타입 선언)
public interface FileUploadRepository extends JpaRepository<FileUploadEntity, Long> {
    //findBy: 튜플을 찾겠다
    //BoardSeq: BoardSeq 컬럼에 데이터를 찾겠다. 왜??
    List<FileUploadEntity> findByItemSeq(Long boardSeq);
    //item Entitiy의 기본키는 id 인데 가져오는곳에서 seq로 써서 일단 seq로 써봄
}
