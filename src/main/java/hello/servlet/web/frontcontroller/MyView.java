package hello.servlet.web.frontcontroller;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath; //url 풀네임이 들어옴
    public MyView(String viewPath) { //url 풀네임이 들어옴
        this.viewPath = viewPath;
    }
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); //viewPath=url 풀네임이 들어옴
        dispatcher.forward(request, response);
    }

    private static void ModelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value )-> request.setAttribute(key,value));
    }
}