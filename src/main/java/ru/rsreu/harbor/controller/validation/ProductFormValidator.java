package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.controller.command.captain.unload.ProductForm;

public interface ProductFormValidator {
    boolean isValidProductForm(ProductForm productForm);
}
