package ru.rsreu.harbor.controller.command.captain.unload;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.dto.DataTransferObject;
import ru.rsreu.harbor.controller.validation.ProductFormValidator;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class UnloadFormDto implements DataTransferObject<List<ProductForm>> {
    private final ProductFormValidator productFormValidator;

    public UnloadFormDto(ProductFormValidator productFormValidator) {
        this.productFormValidator = productFormValidator;
    }

    @Override
    public List<ProductForm> formModel(HttpServletRequest request) {
        String productFormsAsJson = request.getParameter(
                Resourcer.getString("request.unloadCommand.parameter.products"));
        try {
            List<ProductForm> productForms = deserializeJsonToProductForms(productFormsAsJson);
            productForms.forEach(productForm -> {
                productForm.setTitle(productForm.getTitle().trim());
                if (!productFormValidator.isValidProductForm(productForm)) {
                    throw new IllegalArgumentException();
                }
            });
            return productForms;
        } catch (JsonSyntaxException exception) {
            throw new IllegalArgumentException();
        }
    }

    private List<ProductForm> deserializeJsonToProductForms(String json) {
        Type token = new TypeToken<Collection<ProductForm>>() {
        }.getType();
        return new Gson().fromJson(json, token);
    }
}
