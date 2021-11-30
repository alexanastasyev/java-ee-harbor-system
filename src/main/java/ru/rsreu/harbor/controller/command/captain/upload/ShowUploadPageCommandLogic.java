package ru.rsreu.harbor.controller.command.captain.upload;

import ru.rsreu.harbor.datalayer.model.Product;

import java.util.List;

public interface ShowUploadPageCommandLogic {
    List<Product> getProducts();
}
