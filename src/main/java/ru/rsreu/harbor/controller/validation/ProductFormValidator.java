package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.controller.command.captain.product.unload.ProductForm;

public interface ProductFormValidator {
    boolean isValidProductForm(ProductForm productForm);
}
