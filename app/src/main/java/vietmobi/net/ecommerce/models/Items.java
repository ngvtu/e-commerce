package vietmobi.net.ecommerce.models;

import java.util.HashMap;
import java.util.Map;

public class Items {
    public int getImgItems() {
        return imgItems;
    }

    public void setImgItems(int imgItems) {
        this.imgItems = imgItems;
    }

    private int id, imgItems;
    private String itemName, ofBranch, category, describe;
    private Float price, oldPrice, newPrice;
    private boolean isSale, isFavorite;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Map<String, Object > toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("itemName", itemName);
        result.put("ofBranch", ofBranch);
        result.put("category", category);
        result.put("describe", describe);
        result.put("price", price);
        result.put("oldPrice", oldPrice);
        result.put("newPrice", newPrice);
        result.put("isSale", isSale);
        result.put("isFavorite", isFavorite);
        return result;
    }

    public Items(int id, int imgItems, String itemName, String describe, String ofBranch, String category, Float price, Float oldPrice, Float newPrice, boolean isSale, boolean isFavorite) {
        this.id = id;
        this.describe = describe;
        this.imgItems = imgItems;
        this.itemName = itemName;
        this.ofBranch = ofBranch;
        this.category = category;
        this.price = price;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.isSale = isSale;
        this.isFavorite = isFavorite;
    }


    public Items(int imgItems, String itemName, String ofBranch, String category, Float price, boolean isSale, boolean isFavorite) {
        this.imgItems = imgItems;
        this.itemName = itemName;
        this.ofBranch = ofBranch;
        this.category = category;
        this.price = price;
        this.isSale = isSale;
        this.isFavorite = isFavorite;
    }

    public Items(int imgItems, String itemName, String ofBranch, String category, Float price) {
        this.imgItems = imgItems;
        this.itemName = itemName;
        this.ofBranch = ofBranch;
        this.category = category;
        this.price = price;
    }

    public Items(int imgItems, String itemName, String ofBranch, Float price, Float oldPrice, Float newPrice) {
        this.imgItems = imgItems;
        this.itemName = itemName;
        this.ofBranch = ofBranch;
        this.price = price;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public Items() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOfBranch() {
        return ofBranch;
    }

    public void setOfBranch(String ofBranch) {
        this.ofBranch = ofBranch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Float newPrice) {
        this.newPrice = newPrice;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


}
