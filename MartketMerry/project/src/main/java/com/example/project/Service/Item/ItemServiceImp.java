package com.example.project.Service.Item;

import com.example.project.Entity.data.FileUploadEntity;
import com.example.project.Entity.item.Item;
import com.example.project.Repository.Item.ItemRepository;
import com.example.project.Repository.fileTest.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService{

    private final ItemRepository itemRepo;
    private final FileUploadRepository fileUploadRepo;
    //서비스에서 레파지토리의 JPA를 이용하기 위하여 작성

    @Autowired  //Autowired는 왜 쓰이는가? = controller가 service를 주입당하겠다고 선언
    protected ItemServiceImp(ItemRepository itemRepo,
                             FileUploadRepository fileUploadRepo){
        this.itemRepo = itemRepo;
        this.fileUploadRepo = fileUploadRepo;
        //this.~~ : 값이 변하지 않는 상수로 지정
        //           = ~~ : 컨트롤러에 입력하기 위하여?? (확인 필요)
    }

//    @Override   //상품 등록
//    public String insertItem(Item item) {
//        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
//        //아이템을 itemRepo에 저장하겠다.
//        return itemRepo.save(item).getId();
//    }

    @Override   //상품 수정
    public void updateItem(Item item) {
        Item updateItem = itemRepo.findById(item.getId()).get(); //이해 안됨

//        updateItem.setPhoto(item.getPhoto());                   //사진
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
//        updateItem.setDetailPhoto(item.getDetailPhoto());       //상세 사진
    }

    @Override   //상품 삭제
    public void deleteItem(Item item) {
        itemRepo.deleteById(item.getId());
    }

//    @Override   //멤버에 있길래 써봄
//    public Optional<Item> getItem(String itemChoice) {
//        Optional<Item>  sample = itemRepo.findById(itemChoice);
//        return sample;
//    }

//    @Override
//    public List<Item> getItemList(Item item) {
//        return (List<Item>) itemRepo.findAll(); //아이템 레파지토리의 모든 것을 찾겠다!
//    }


    //아이템 등록
    @Override
    public Long insertItems(Item item) {
//        itemRepo.save(item);
        return itemRepo.save(item).getId();
    }

    //아이템 리스트
    @Override
    public List<Item> getItemLists(Item item) {
        return itemRepo.findAll();
    }
//    //아이템 리스트
//    @Override
//    public List<Item> itemLists(Item item) {
//        return itemRepo.findAll();
//    }

    @Override
    public List<Item> itemListss(List<Item> itemList) {
        return itemList;
    }

    @Override
    public Long insertFileUploadEntity(FileUploadEntity fileUploadEntity) {
        return fileUploadRepo.save(fileUploadEntity).getId();
    }

    @Override
    public List<FileUploadEntity> getFileUploadEntity(Long item_seq) {  //스트링타입이 아니라 롱으로 바꿔야 할까?
        return fileUploadRepo.findByItemSeq(item_seq);  //메소드 이름 자체가 쿼리이다. 가 무슨뜻?>
    }

    @Override
    public Item getItem(Item item) {
        return itemRepo.findById(item.getId()).get();
    }


}
