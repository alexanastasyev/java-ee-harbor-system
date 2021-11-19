package ru.rsreu.harbor.controller.result;


public class ActionCommandResult {
    private final String page;
    private final ActionCommandResultTypes actionCommandResultType;

    public ActionCommandResult(String page, ActionCommandResultTypes actionCommandResultType) {
        this.page = page;
        this.actionCommandResultType = actionCommandResultType;
    }

    public String getPage() {
        return page;
    }

    public ActionCommandResultTypes getActionCommandResultType() {
        return actionCommandResultType;
    }
}
