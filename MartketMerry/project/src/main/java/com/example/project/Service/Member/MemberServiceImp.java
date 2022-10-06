package com.example.project.Service.Member;

import com.example.project.Entity.Member.Member;
import com.example.project.Repository.Member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService{

    private final MemberRepository memberRepo;
    //서비스에서 레파지토리의 JPA를 이용하기 위하여 작성

    @Autowired
    protected  MemberServiceImp(MemberRepository memberRepo){
        this.memberRepo = memberRepo;
    }

    @Override //    회원가입
    public void insertMember(Member member) {
        memberRepo.save(member);

    }

    @Override //   회원 수정
    public void updateMember(Member member) {
        Member updateMember = memberRepo.findById(member.getMemberId()).get();
        updateMember.setMemberPassword(member.getMemberPassword());
        updateMember.setMemberAddress(member.getMemberAddress());
        updateMember.setMemberPhone(member.getMemberPhone());
        updateMember.setMemberEmail(member.getMemberEmail());
        System.out.println(updateMember.getMemberPassword());
        System.out.println(updateMember.getMemberAddress());
        System.out.println(updateMember.getMemberPhone());
        System.out.println(updateMember.getMemberEmail());

        memberRepo.save(updateMember);
    }


    //아이디 유효성 검사
    @Override
    public Map<String, String> member_Availability(Errors errors) {
        //유효성 검사에 실패한 필드들은 Map 자료구조를 통해 키값과 에러 메시지를 응답한다.
        //Key : valid_{dto 필드명}
        //Message : dto에서 작성한 message 값
        //유효성 검사에 실패한 필드 목록을 받아 미리 정의된 메시지를 가져와 Map에 넣어준다.
        Map<String, String> availability_ID = new HashMap<>();
        /* 유효성 검사에 실패한 필드 목록을 받음 */
        // errors.getFieldErrors() : 유효성 검사에 실패한 필드 목록을 가져옴
        for(FieldError error : errors.getFieldErrors()){
            //유효성 검사에 실패한 필드명을 가져옵니다. : error.getField() / 키 : "members_%s"  = > mevers_dto필드명
            String member_availability_ID = String.format("valid_%s", error.getField());
            //error.getDefaultMessage() : 유효성 검사에 실패한 필드에 정의된 메시지를 가져옵니다.
            availability_ID.put(member_availability_ID, error.getDefaultMessage());
        }
        return availability_ID;
    }
    //회원탈퇴
    @Override
    public void deleteMember(Member member) {
        memberRepo.deleteById(member.getMemberId());
    }

    @Override
    public Member getMember(Member member) {
        // 특정회원을 검색하여 리턴하고, 만약 검색 결과에 없으면 null을 리턴한다.
        // 로그인시에도 활용한다.
        Optional<Member> findMember = memberRepo.findById(member.getMemberId());
        if(findMember.isPresent())
            return memberRepo.findById(member.getMemberId()).get();
        else return null;
    }

    //아이디 중복확인할 때 쓰는 것.
    @Override
    public Member getMemberWhereId(String memberId) {
        return memberRepo.findMemberById(memberId);
    }
}