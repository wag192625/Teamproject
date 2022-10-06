package com.example.project.Controller.item;

import com.example.project.Entity.data.FileUploadEntity;
import com.example.project.Entity.item.Item;
import com.example.project.Service.Item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller //디스패처 서블릿이 컨트롤러를 찾기 위해서 @Controller라는 어노테이션을 선언
@RequestMapping(path = "/otherpage")   //미리 경로를 지정해줘서 앞 경로를 생략 가능하게 됨
public class ItemController {
    //private final을 쓰는 이유 = final은 불변성이라 컨트롤러가 안심하고 사용 가능 / itemServiceImp을 쓰기 위하여
    private final ItemService itemService;

    //Autowired는 왜 쓰이는가? = controller가 service를 주입당하겠다고 선언
    @Autowired
    private ItemController(ItemService itemService) {
        this.itemService = itemService; // 생성자 주입 방식
    }

//    @GetMapping("/addItem")
//    public String insertItem() {
////        System.out.println(item.getItemName());
//        System.out.println("get addItem1");
//
//        return "/otherpage/addItem";
//    }

//    @PostMapping("/addItem")
//    public String insertItem(@Nullable Item item, Model model) {
//        System.out.println("-------------------------------------");
//        System.out.println(item);
//        System.out.println("-------------------------------------");
//
////        //Item : 클라이언트에서 서버로 데이터를 받는 Entity
////        //model : 서버에서 클라이언트로 데이터를 전송하는 매개체
////        System.out.println(item.getItemName());
////        //model : 컨트롤러에서 작업한 결과물을 HTML에 전달하기 위한 매개체
////        //addAttribute : key/value으로 데이터를 저장하는 메서드
////        //attributeName(key) : 뒤에 있는 value를 호출하기 위한 문자열(key)
////        //memberService.getMemberList() : @Autowired로 선언된 MemberService 클래스를 호출하여
////        //getMemberList()메서드 실행
////        //model.addAttribute("Item", item); //키 Item,
////
//        String itemChoice = itemService.insertItem(item);    //들어온 값을
//        Optional<Item> test =  itemService.getItem(itemChoice);
//        model.addAttribute("itemList", test);   //엔티티의 아이템 리스트로 값 저장
////
////        itemService.insertItem(item);   //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
////
////        //클라이언트에서 board객체를 받아서 매개변수로 사용
////        //[1]BoardService의 inserBoard메서드 실행 >
////        //[2]BoardRepository(CrudRepository).save(board)를 통해서 (JPA번역)
////        //DB의 저장 (SQL)
////        //insertBoard라는 메서드에 board객체 인자값으로 넣기
////
////        // 입력받은 데이터 추가하기 위하여 Jpa 상속받은 서비스 추가
////        //데이터 오입력으로 400
//        return  "redirect:/otherpage/addItem";



//        //retrun 타입이 String이유 : HTML 파일명을 찾기 위해
//
//        //리스트로 받아오고 그거를 나타낼 수 있는 페이지 하나 만들어서 테스트해보기
 //   }


    @GetMapping("/addItem")
    public String insertItems(Item item, Model model) {
//        System.out.println(item.getItemName());
        System.out.println("get addItem1");
        Item item1 = new Item(
                item.getId(),
                item.getPhoto(),
                item.getItemName(),
                item.getItemText(),
                item.getPrice(),
                item.getDelivery(),
                item.getMainCategory(),
                item.getSubCategory(),
                item.getSeller(),
                item.getPacking(),
                item.getShelfLife(),
                item.getStock(),
                item.getDetailText(),
                item.getDetailPhoto()
        );
        model.addAttribute("item", item1);
        return "/otherpage/addItem";
    }
        @PostMapping("/addItem")
                public String insertItemss(Item item, Model model) {
            itemService.insertItems(item);
            return "redirect:/otherpage/addTest";
        }

    @GetMapping("/addTest")
    public String itemList(Model model){
        model.addAttribute("item",
                itemService.itemListss(
                        itemService.itemLists()));
        return "/otherpage/addTest";
    }

    @PostMapping("/addItem")
    public String insertPhoto(Item item, @Nullable @RequestParam("기져올 데이터의 이름")MultipartFile[] uploadfile) {
        //@Nullable@RequestPanam(
        //MultipartFile을 클라이언트에서 받아오고, 데이터가 없더라도 허용(@Nullable)
        try{
            String Item_seq = itemService.insertItem(item);   //배낀곳에선 Long타입 선언한 item 사용
            List<FileUploadEntity> list = new ArrayList<>();
            for(MultipartFile file : uploadfile) {
                //MultipartFile로 클라이언트에서 온 데이터가 무결성 조건에 성립을 안하거나
                // 메타데이터가 없거나 문제가 생길 여지를 if 문으로 처리
                if(!file.isEmpty()){
                    FileUploadEntity
                            entity = new FileUploadEntity(
                                    null,
                                    UUID.randomUUID().toString(),
                            file.getContentType(),
                            file.getName(),
                            file.getOriginalFilename(),
                            Item_seq);
                }
            }

        }
    }











}