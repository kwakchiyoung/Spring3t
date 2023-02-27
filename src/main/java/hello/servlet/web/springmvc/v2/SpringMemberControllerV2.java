package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")  //(1)RequestMapping이 맨 최상단에 있으면 위에까 다음 아래꺼가 조합된다..
public class SpringMemberControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping
    public ModelAndView save() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members",members);

        return mv;

    }

    @RequestMapping("/save")
    public ModelAndView members(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age); //맴버객체 생성후
        memberRepository.save(member); //레파지토리에 저장

        ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member",member); //모델뷰에 맴버를 넣어준다.
        mv.addObject("member",member);
        return mv;
    }
}
