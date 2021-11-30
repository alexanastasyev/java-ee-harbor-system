package ru.rsreu.harbor.controller.command.captain.unload;

import java.util.List;

public interface UnloadCommandLogic {
    void unload(String login, List<ProductForm> productForms);
}
