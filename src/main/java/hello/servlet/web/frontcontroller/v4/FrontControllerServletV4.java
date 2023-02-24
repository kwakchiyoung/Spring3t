package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4" , urlPatterns = "/front-controller/v4/*") // /front-controller/v1/ 하위에 들어오는 모든걸 포함되서 호출됨.
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String , ControllerV4> controllerMap = new HashMap<>(); //map(URI명,컨트롤러객체)

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();// 지금 들어온 URI를 받을 수 있다.
        ControllerV4 controller = controllerMap.get(requestURI); //컨트롤러 객체 폼,저장,리스트 3개중 하나가 들어오겠지
        if (controller ==null){ //컨트롤러가 null일경우 -> controllermap에 있는거랑 매칭이 안될경우 없는 URI이므로
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); //404 에러 응답.
            return;
        }

        //paramMap을 넘겨줘야함.

        Map<String, String> paramMap = createParamMap(request); //<"age"="20"> ,<"username"="luca">
        Map<String,Object> model = new HashMap<>(); //("member",member객체)
        String viewName = controller.process(paramMap, model);//mv 객체 안에는 각 컨트롤러에서 모델뷰로 전달해준 viewName이 있음 new-form,save-result,members

        ///WEB-INF/views/상대경로.jsp
        MyView view = viewResolver(viewName); // "/WEB-INF/views/save-result.jsp"
        view.render(model,request,response); //뷰로 보낸다. 각 파람값과,요청,응답 객체들을 보내면 MyView에서 랜더링 진행.

    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String,String> paramMap = new HashMap<>(); //파라미터값을 넣어둘 맵
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName))); //파라미터 키값과 키값에대한 벨류값을 파람맵에 넣는다.
        return paramMap;
    }
}
