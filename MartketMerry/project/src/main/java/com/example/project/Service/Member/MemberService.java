package com.example.project.Service.Member;

import com.example.project.Entity.Member.Member;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {


    void insertMember(Member member);   //멤버 추가

    void updateMember(Member member);   //멤버 수정

    public Map<String, String> member_Availability(Errors errors);
                                        //모름
    void deleteMember(Member member);   //멤버 삭제
    public Member getMember(Member member);
                                        //멤버 불러오기?

    //아이디 중복확인할 때 쓰는 것.
    Member getMemberWhereId(String id);
}
