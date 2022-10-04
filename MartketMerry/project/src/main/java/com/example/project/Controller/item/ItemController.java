package com.example.project.Controller.item;

import com.example.project.Entity.item.Item;
import com.example.project.Service.Item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //디스패처 서블릿이 컨트롤러를 찾기 위해서 @Controller라는 어노테이션을 선언
@RequestMapping(path = "/otherpage")   //미리 경로를 지정해줘서 앞 경로를 생략 가능하게 됨
public class ItemController {
    //private final을 쓰는 이유 = final은 불변성이라 컨트롤러가 안심하고 사용 가능
    private final ItemService itemService;

    //Autowired는 왜 쓰이는가? = controller가 service를 주입당하겠다고 선언
    @Autowired
    private ItemController(ItemService itemService) {
        this.itemService = itemService; // 생성자 주입 방식
    }


    @GetMapping("/addItem")
    public String addItem(Item item) {
        System.out.println("get addItem");
        return "/otherpage/addItem";
    }

    @PostMapping("/addItem")
    public String addItem(@Nullable Item item, Model model) {
        System.out.println(item.getItemName());
        itemService.insertItem(item);
        //데이터 오입력으로
        return  "redirect:/addItem";
    }
}