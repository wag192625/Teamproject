package com.example.project.Service.Item;

import com.example.project.Entity.item.Item;

import java.util.List;
import java.util.Optional;

//엔티티의
public interface ItemService {

    String insertItem(Item item);     // 상품 등록
    // void를 쓰는 이유 :
    void updateItem(Item item);     // 상품 수정 (할건가?)

    void deleteItem(Item item);     // 상품 삭제

    Optional<Item> getItem(String itemId);        //상품 불러오기? (멤버에 있길래 써봄)

    List<Item> getItemList(Item item);  //상품 리스트 배열로


    void insertItems(Item item);

    List<Item> itemLists();

    List<Item> itemListss(List<Item> itemList);






}