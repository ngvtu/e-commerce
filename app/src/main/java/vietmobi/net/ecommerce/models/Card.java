package vietmobi.net.ecommerce.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "all_card")
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int id;

    String cardName;
    String expiryDate;
    String numberCard;
    public int CVV;
    boolean isMasterCard;
    boolean isVisa;

    public Card(String cardName, String expiryDate, String numberCard, int CVV, boolean isMasterCard, boolean isVisa) {
        this.cardName = cardName;
        this.expiryDate = expiryDate;
        this.numberCard = numberCard;
        this.CVV = CVV;
        this.isMasterCard = isMasterCard;
        this.isVisa = isVisa;
    }

    public Card() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public boolean isMasterCard() {
        return isMasterCard;
    }

    public void setMasterCard(boolean masterCard) {
        isMasterCard = masterCard;
    }

    public boolean isVisa() {
        return isVisa;
    }

    public void setVisa(boolean visa) {
        isVisa = visa;
    }
}
