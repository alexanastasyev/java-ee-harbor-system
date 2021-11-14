package ru.rsreu.harbor.controller.result;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ActionCommandResultArguments {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final RequestDispatcher requestDispatcher;
    private final String page;
    private final Map<String, String> jspParameters;

    public ActionCommandResultArguments(HttpServletRequest request, HttpServletResponse response, RequestDispatcher requestDispatcher, String page, Map<String, String> jspParameters) {
        this.request = request;
        this.response = response;
        this.requestDispatcher = requestDispatcher;
        this.page = page;
        this.jspParameters = jspParameters;
        initParameters();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public RequestDispatcher getRequestDispatcher() {
        return requestDispatcher;
    }

    public String getPage() {
        return page;
    }

    public Map<String, String> getJspParameters() {
        return jspParameters;
    }

    private void initParameters() {
        this.getJspParameters().forEach((key, value) -> this.getRequest().getSession().setAttribute(key, value));
    }
}
