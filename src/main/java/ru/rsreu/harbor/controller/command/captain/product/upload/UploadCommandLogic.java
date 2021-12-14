package ru.rsreu.harbor.controller.command.captain.product.upload;

import ru.rsreu.harbor.controller.command.captain.product.ProductsActionsLogic;

import java.util.List;

public interface UploadCommandLogic extends ProductsActionsLogic {
    void upload(List<Long> productIds);
}
