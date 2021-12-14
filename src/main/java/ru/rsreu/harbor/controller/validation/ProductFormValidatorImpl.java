package ru.rsreu.harbor.controller.validation;

import ru.rsreu.harbor.controller.command.captain.unload.ProductForm;

public class ProductFormValidatorImpl implements ProductFormValidator {
    @Override
    public boolean isValidProductForm(ProductForm productForm) {
        return !productForm.getTitle().isEmpty();
    }
}
