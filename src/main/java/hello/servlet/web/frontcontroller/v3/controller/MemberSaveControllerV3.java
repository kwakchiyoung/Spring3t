package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository= MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) { //age=? , username=?
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age); //맴버객체 생성후
        memberRepository.save(member); //레파지토리에 저장

        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member",member); //모델뷰에 맴버를 넣어준다.
        return mv;
    }
}
