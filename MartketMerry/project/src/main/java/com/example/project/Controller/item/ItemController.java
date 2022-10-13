package com.example.project.Controller.item;

import com.example.project.Entity.data.FileUploadEntity;
import com.example.project.Entity.item.Item;
import com.example.project.Service.Item.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import static oracle.security.crypto.util.Utils.toByteArray;
//import static com.google.common.io.ByteStreams.toByteArray;


@Slf4j
@Controller //디스패처 서블릿이 컨트롤러를 찾기 위해서 @Controller라는 어노테이션을 선언
@RequestMapping(path = "/otherpage")   //미리 경로를 지정해줘서 앞 경로를 생략 가능하게 됨
public class ItemController {
    //private final을 쓰는 이유 = final은 불변성이라 컨트롤러가 안심하고 사용 가능 / itemServiceImp을 쓰기 위하여
    private final ItemService itemService;

    //Autowired는 왜 쓰이는가? = controller가 service를 주입당하겠다고 선언
    @Autowired
    private ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/addItem")
    public String insertItem(Item item, Model model) {
        System.out.println("get addItem1");
        return "/otherpage/addItem";
    }

    @PostMapping("/addItem")
    public String insertItem(Item item,
                               @Nullable @RequestParam("uploadPhoto") MultipartFile[] uploadPhoto) {
        System.out.println("post addItem1");
        System.out.println(item.getSeq());
        try {
            long item_seq = itemService.insertItem(item);
            List<FileUploadEntity> list = new ArrayList<>();
            for (MultipartFile file : uploadPhoto) {
                if (!file.isEmpty()) {
                    FileUploadEntity entity = new FileUploadEntity(
                            null,
                            UUID.randomUUID().toString(),
                            file.getContentType(),
                            file.getName(),
                            file.getOriginalFilename(),
                            item_seq
                    );
                    itemService.insertFileUploadEntity(entity);
                    list.add(entity);
                    File newFileName = new File(entity.getUuid() + "_" + entity.getOriginalFilename());
                    System.out.println(file.getOriginalFilename());
                    file.transferTo(newFileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        itemService.insertItems(item);
        System.out.println(item);
        return "redirect:/otherpage/itemList";

    }

    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@RequestParam("uploadPhoto") MultipartFile[] uploadPhoto,
                              @RequestParam("seq") Long input_seq) throws IOException {
        log.info("img load session");
        List<FileUploadEntity> list = new ArrayList<>();
        for (MultipartFile file : uploadPhoto) {
            if (!file.isEmpty()) {
                FileUploadEntity entity = new FileUploadEntity(
                        null,
                        UUID.randomUUID().toString(),
                        file.getContentType(),
                        file.getName(),
                        file.getOriginalFilename(),
                        input_seq
                );
                Long output = itemService.insertFileUploadEntity(entity);
                System.out.println();
                log.info("---------------seq check---------------");
                log.info(output.toString());
                list.add(entity);
                File newFileName = new File(entity.getUuid() + "_" + entity.getOriginalFilename());
                file.transferTo(newFileName);
            }
        }
        return "/otherpage/addItem";
    }

    @GetMapping("/itemList")
    public String itemList(Item item, Model model, FileUploadEntity fileUploadEntity) {
        System.out.println("get ItemList");
        List<Item> itemList = itemService.getItemLists(item);
        model.addAttribute("itemList", itemList);
//
//        System.out.println(item.getSeq());
//        System.out.println(item.getPrice());
//        System.out.println(item.getStock());
//        System.out.println(fileUploadEntity.getItemSeq());
        return "/otherpage/itemList";
    }


    @GetMapping("/getItem")      //상세페이지 용
    public String getItem(Item item, Model model) {

        List<FileUploadEntity> fileUploadEntity = itemService.getFileUploadEntity(item.getSeq());
        List<String> path = new ArrayList<>();
        for (FileUploadEntity fe : fileUploadEntity) {
            String savePath = "/item/image/" + fe.getUuid() + "_" + fe.getOriginalFilename();
            path.add(savePath);
        }
        model.addAttribute("item", itemService.getItem(item));              // 작성 보드 불러오기
//        model.addAttribute("boardPrv", ItemService.getPagesSortIndex(board));  // 정렬
        model.addAttribute("imgLoading", path);                                 // 이미지 출력
//        model.addAttribute("imgLoading", path+"/filer");
        return "/otherpage/getItem";
    }


    @GetMapping(value = "/image/{imagename}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> imageLoding(@PathVariable("imagename") String imgname) throws IOException {
        // ResponseEntity<byte[]> : 메서드 리턴타임으로 이미지 데이터를 송신하기 위한 객체<바이트 배열>
        // throws IOException : 스트림 방식으로 데이터를 전송할 떄 도중에 오류가 날 경우를 찾기 위해서 선언한 Exception
        String path = "\\Users\\wag19\\Documents\\Spring\\project\\src\\main\\resources\\static\\upload\\" + imgname;
        // File을 컴퓨터가 이해하기 위해서 stream 배열을 만들어서 작업
        // 객체(데이터 저장) : String, int, double
        // Stream 객체는 파일을 컴퓨터가 cpu에서 바로 읽어들일 수 있도록 하는 객체
        FileInputStream fis = new FileInputStream(path);
        // Buffered : cpu에서 데이터 읽어올 때 메모리와 캐시 사이에서 cpu와의 속도 차이를 줄이기 위한 중간 저장 위치
        BufferedInputStream bis = new BufferedInputStream(fis);
        // byte배열로 전환하여 ResponseEntity를 통해 클라이언트에게 데이터 전달
        // HTTP프로토콜은 바이트 단위(배열)로 데잍를 주고 받음
        byte[] imgByteArr = bis.readAllBytes();
        // ResponseEntity를 통해 http 프로토콜로 클라이언트에게 데이터 전송

        // http 프로토콜은 바이트 배열로 데이터를 주고 받기 때문에 stream이나 버퍼를 통해 전환
        return new ResponseEntity<byte[]>(imgByteArr, HttpStatus.OK);
    }





//    @GetMapping(value = "/image/{imagename}/filter", produces = MediaType.IMAGE_PNG_VALUE)
//    public ResponseEntity<byte[]> imagefilter(@PathVariable("imagename") String imagename) throws IOException{
//        InputStream imageStream = new FileInputStream(
//                "\\Users\\wag19\\Documents\\Spring\\project\\src\\main\\resources\\static\\upload\\" + imagename);
//        byte[] imageByteArray = toByteArray(imageStream);
//        imageStream.close();
//        log.info("image");
//        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
//    }
}