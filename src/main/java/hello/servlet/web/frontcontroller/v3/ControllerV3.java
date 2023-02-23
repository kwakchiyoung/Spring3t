package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

//Luca필기 중요!! 사실상 컨트롤러쪽에서는 서블릿 객체를 단지 savecontroller와 같이 파람값을
//꺼내는 용도로만 사용된다. 그럼 굳이 필요할까??
//그래서 컨트롤러에서는 모델을 따로 정의하여 순수자바로 이용할것이다!
public interface ControllerV3 {

    ModelView process(Map<String,String> paramMap);

}
