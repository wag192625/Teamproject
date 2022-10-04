package com.example.project.Controller.item;

import com.example.project.Service.Item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //디스패처 서블릿이 컨트롤러를 찾기 위해서 @Controller라는 어노테이션을 선언
@RequestMapping(path = "/myPage")   //미리 경로를 지정해줘서 앞 경로를 생략 가능하게 됨
public class ItemController {
//    컨트롤러 단에서의 Autowired는 왜 쓰이는가? (서비스 임플리먼트에서도 쓰여서리)

//    private final ItemService itemService;
//
//    @Autowired
    public String

}