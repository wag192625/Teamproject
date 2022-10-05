package com.example.project.Service.Item;

import com.example.project.Entity.item.Item;
import com.example.project.Repository.Item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService{

    private final ItemRepository itemRepo;
    //서비스에서 레파지토리의 JPA를 이용하기 위하여 작성

    @Autowired  //Autowired는 왜 쓰이는가? = controller가 service를 주입당하겠다고 선언
    protected ItemServiceImp(ItemRepository itemRepo){
        this.itemRepo = itemRepo;
        //왜 쓰는지?
    }

    @Override   //상품 등록
    public String insertItem(Item item) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        //아이템을 itemRepo에 저장하겠다.
        return itemRepo.save(item).getId();
    }

    @Override   //상품 수정
    public void updateItem(Item item) {
        Item updateItem = itemRepo.findById(item.getId()).get(); //이해 안됨

        updateItem.setPhoto(item.getPhoto());                   //사진
        updateItem.setItemName(item.getItemName());             //이름
        updateItem.setItemText(item.getItemText());             //설명
        updateItem.setPrice(item.getPrice());                   //가격
        updateItem.setDelivery(item.getDelivery());             //배송 선택
        updateItem.setMainCategory(item.getMainCategory());     //대분류
        updateItem.setSubCategory(item.getSubCategory());       //소분류
        updateItem.setSeller(item.getSeller());                 //업체,판매자명
        updateItem.setPacking(item.getPacking());               //포장 타입
        updateItem.setShelfLife(item.getShelfLife());           //유통기한
        updateItem.setStock(item.getStock());                   //재고 수량
        
        updateItem.setDetailText(item.getDetailText());         //상세 설명
        updateItem.setDetailPhoto(item.getDetailPhoto());       //상세 사진
    }

    @Override   //상품 삭제
    public void deleteItem(Item item) {
        itemRepo.deleteById(item.getId());
    }

    @Override   //멤버에 있길래 써봄
    public Optional<Item> getItem(String itemChoice) {
        Optional<Item>  sample = itemRepo.findById(itemChoice);
        return sample;
    }

    @Override
    public List<Item> getItemList(Item item) {
        return (List<Item>) itemRepo.findAll(); //아이템 레파지토리의 모든 것을 찾겠다!
    }


    //아이템 등록
    @Override
    public void insertItems(Item item) {
        itemRepo.save(item);
    }
    //아이템 리스트
    @Override
    public List<Item> itemLists() {
        return itemRepo.findAll();
    }

    @Override
    public List<Item> itemListss(List<Item> itemList) {
        return itemList;
    }


}
