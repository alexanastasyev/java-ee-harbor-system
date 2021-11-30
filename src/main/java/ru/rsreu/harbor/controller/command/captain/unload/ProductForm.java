package ru.rsreu.harbor.controller.command.captain.unload;

import com.google.gson.annotations.SerializedName;

public class ProductForm {

    @SerializedName("title")
    private String title;

    @SerializedName("quantity")
    private int quantity;

    public ProductForm(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
