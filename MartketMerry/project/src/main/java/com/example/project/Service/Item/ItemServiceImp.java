package com.example.project.Service.Item;

import com.example.project.Entity.item.Item;
import com.example.project.Repository.Item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService{

    private final ItemRepository itemRepo;
    //서비스에서 레파지토리의 JPA를 이용하기 위하여 작성

    @Autowired  //오토와이어드 왜 쓰는지?
    protected ItemServiceImp(ItemRepository itemRepo){
        this.itemRepo = itemRepo;
        //왜 쓰는지?
    }

    @Override   //상품 등록
    public void insertItem(Item item) {
        itemRepo.save(item);
        //아이템을 itemRepo에 저장하겠다.
    }

    @Override   //상품 수정
    public void updateItem(Item item) {
        Item updateItem = itemRepo.findBySeq(item.getseq()).get(); //이해 안됨
        //Jpa레파지토리에 seq찾는게 없어서 어카징
        updateItem.setPhoto(item.getPhoto());           //사진
        updateItem.setName(item.getName());             //이름
        updateItem.setText(item.getText());             //설명
        updateItem.setPrice(item.getPrice());           //가격
        updateItem.setDelivery(item.getDelivery());     //배송 선택
        updateItem.setCategory1(item.getCategory1());   //분류1
        updateItem.setCategory2(item.getCategory2());   //분류2
        updateItem.setSeller(item.getSeller());         //업체,판매자명
        updateItem.setPacking(item.getPacking());       //포장 타입
        updateItem.setShelfLife(item.getShelfLife());   //유통기한
        updateItem.setStock(item.getStock());           //재고 수량
//        updateItem.set(item.get());

        //미구현
    }

    @Override   //상품 삭제
    public void deleteItem(Item item) {

    }

    @Override   //멤버에 있길래 써봄
    public Item getItem(Item item) {
        return null;
    }
}
