package com.example.project.Repository.fileTest;

import com.example.project.Entity.data.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//FileUploadEntity를 저장하는 인터페이스 (JPA CrudRepository 활용)
public interface FileUploadInfoRepository extends JpaRepository<FileUploadEntity, Long> {

    //findBy : 튜플을 찾겠다
    //BoardSeq : BoardSeq 컬럼에 데이터를 찾겠다
    List<FileUploadEntity> findByItemSeq(Long ItemSeq); //


}
