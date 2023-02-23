package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Luca필기 중요!! 사실상 컨트롤러쪽에서는 서블릿 객체를 단지 savecontroller와 같이 파람값을
//꺼내는 용도로만 사용된다. 그럼 굳이 필요할까??
public interface ControllerV2 {
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
