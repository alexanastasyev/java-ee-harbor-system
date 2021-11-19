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

    public ActionCommandResultArguments(
            HttpServletRequest request,
            HttpServletResponse response,
            RequestDispatcher requestDispatcher,
            String page
    ) {
        this.request = request;
        this.response = response;
        this.requestDispatcher = requestDispatcher;
        this.page = page;
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
}
