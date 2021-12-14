package ru.rsreu.harbor.controller.command.captain.product.unload;

import ru.rsreu.harbor.controller.command.captain.product.ProductsActionsLogic;

import java.util.List;

public interface UnloadCommandLogic extends ProductsActionsLogic {
    void unload(String login, List<ProductForm> productForms);
}
