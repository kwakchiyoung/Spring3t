package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {
    private String viewName; //URI에서 맨 끝이름만 ex)new-form
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName; //URI에서 맨 끝이름만 ex)new-form
    }

    public String getViewName() {
        return viewName; //URI에서 맨 끝이름만 ex)new-form
    }

    public void setViewName(String viewName) {
        this.viewName = viewName; //URI에서 맨 끝이름만 ex)new-form
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
