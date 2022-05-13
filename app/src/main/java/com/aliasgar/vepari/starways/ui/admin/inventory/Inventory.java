package com.aliasgar.vepari.starways.ui.admin.inventory;

import com.aliasgar.vepari.starways.R;
import com.google.gson.annotations.SerializedName;

public class Inventory {

    @SerializedName("item_name")
    private String itemName;
    @SerializedName("item_qty")
    private String itemQty;
    @SerializedName("img_dir")
    private String itemImg;
    @SerializedName("item_price")
    private String itemPrice;

    public Inventory(String itemName, String itemQty, String itemImg, String itemPrice) {
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemImg = itemImg;
        this.itemPrice = itemPrice;
    }

    public Inventory(String itemName, String itemQty, String itemPrice) {
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemPrice = itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemImg() {
        return itemImg;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }
}