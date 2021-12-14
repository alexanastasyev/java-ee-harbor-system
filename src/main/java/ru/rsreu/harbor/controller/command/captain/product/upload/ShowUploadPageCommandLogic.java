package ru.rsreu.harbor.controller.command.captain.product.upload;

import ru.rsreu.harbor.controller.command.captain.product.ShowProductActionsPageLogic;
import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public interface ShowUploadPageCommandLogic extends ShowProductActionsPageLogic {
    List<Product> getProducts();
}
