package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")  //(1)RequestMapping이 맨 최상단에 있으면 위에까 다음 아래꺼가 조합된다..
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping(value = "/new-form",method = RequestMethod.GET) //메소드까지 넣어주면 GET에서만 응답을 해준다.
    @GetMapping("/new-form")
    public String newForm() {
        //return new ModelAndView("new-form"); //v2버전
        return "new-form";
    }

    //@RequestMapping(value = "/save" ,method = RequestMethod.POST) //메소드까지 넣어주면 POST에서만 응답을 해준다.
    @PostMapping("save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
            ) {
        Member member = new Member(username, age); //맴버객체 생성후
        memberRepository.save(member); //레파지토리에 저장

        model.addAttribute("member",member);

        //ModelAndView mv = new ModelAndView("save-result");
        //mv.addObject("member",member);
        //return mv; //v2버전
        return "save-result";
    }
    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members",members);

        //ModelAndView mv = new ModelAndView("members");
        //mv.addObject("members",members);
        //return mv; //v2버전

        return "members";

    }

}
