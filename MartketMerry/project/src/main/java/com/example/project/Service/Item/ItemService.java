package com.example.project.Service.Item;

import com.example.project.Entity.data.FileUploadEntity;
import com.example.project.Entity.item.Item;

import java.util.List;

//엔티티의
public interface ItemService {

//    String insertItem(Item item);     // 상품 등록
    // void를 쓰는 이유 :
    void updateItem(Item item);     // 상품 수정 (할건가?)

    void deleteItem(Item item);     // 상품 삭제

//    Optional<Item> getItem(String itemId);        //상품 불러오기? (멤버에 있길래 써봄)
//
//    List<Item> getItemList(Item item);  //상품 리스트 배열로


    Long insertItem(Item item);
    //Long 이 Item으로 바뀜

    List<Item> getItemLists(Item item);

//    List<Item> itemListss(List<Item> itemList);

    Long insertFileUploadEntity(FileUploadEntity fileUploadEntity);
    // 엔티티에 데이터를 저장하기 위해
//    List<FileUploadEntity> getFileUploadEntity(Long item_id);
    List<FileUploadEntity> getFileUploadEntity(Long item_seq);
    // 아이템 entity는 String인데 파일~은 Long이어도 되는가?
    // 카멜케이스 itemSeq가 아닌 스네이크케이스 item_seq 인 이유는?

    Item getItem(Item item);




}