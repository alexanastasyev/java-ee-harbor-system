package ru.rsreu.harbor.controller.result;

import java.util.Map;

public class ActionCommandResult {
    private final String page;
    private final ActionCommandResultTypes actionCommandResultType;
    private final Map<String, Object> jspParameters;

    public ActionCommandResult(String page, ActionCommandResultTypes actionCommandResultType, Map<String, Object> jspParameters) {
        this.page = page;
        this.actionCommandResultType = actionCommandResultType;
        this.jspParameters = jspParameters;
    }

    public String getPage() {
        return page;
    }

    public ActionCommandResultTypes getActionCommandResultType() {
        return actionCommandResultType;
    }

    public Map<String, Object> getJspParameters() {
        return jspParameters;
    }
}
