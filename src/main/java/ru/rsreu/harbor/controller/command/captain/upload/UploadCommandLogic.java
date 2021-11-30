package ru.rsreu.harbor.controller.command.captain.upload;

import java.util.List;

public interface UploadCommandLogic {
    void upload(List<Long> productIds);
}
