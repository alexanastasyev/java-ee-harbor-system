package ru.rsreu.harbor.controller.command.captain.product.upload;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prutzkow.resourcer.Resourcer;
import ru.rsreu.harbor.controller.dto.DataTransferObject;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class UploadFormDto implements DataTransferObject<List<Long>> {
    @Override
    public List<Long> formModel(HttpServletRequest request) {
        String productsIdsAsJson = request.getParameter(
                Resourcer.getString("request.uploadCommand.parameter.products.ids"));
        Type token = new TypeToken<Collection<Long>>(){}.getType();
        return new Gson().fromJson(productsIdsAsJson, token);
    }
}
