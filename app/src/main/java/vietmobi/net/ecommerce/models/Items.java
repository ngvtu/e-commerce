package vietmobi.net.ecommerce.models;

public class Items {
    private int id;
    private String itemName, ofBranch, category;
    private Float price, oldPrice, newPrice;
    private boolean isSale, isFavorite;

    public Items(String itemName, String ofBranch, String category, Float price, Float oldPrice, Float newPrice, boolean isSale, boolean isFavorite) {
        this.itemName = itemName;
        this.ofBranch = ofBranch;
        this.category = category;
        this.price = price;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.isSale = isSale;
        this.isFavorite = isFavorite;
    }

    public Items(String itemName, String ofBranch, String category, Float price, boolean isSale, boolean isFavorite) {
        this.itemName = itemName;
        this.ofBranch = ofBranch;
        this.category = category;
        this.price = price;
        this.isSale = isSale;
        this.isFavorite = isFavorite;
    }

    public Items(String itemName, String ofBranch, String category, Float price) {
        this.itemName = itemName;
        this.ofBranch = ofBranch;
        this.category = category;
        this.price = price;
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
