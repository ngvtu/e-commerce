package vietmobi.net.ecommerce.models;

public class PromoCode {
    private String codeName;
    private String code;
    private int remainingDay;
    private int valuePromo;

    public PromoCode( String codeName, String code, int remainingDay, int valuePromo) {
        this.codeName = codeName;
        this.code = code;
        this.remainingDay = remainingDay;
        this.valuePromo = valuePromo;
    }

    public PromoCode() {
    }
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRemainingDay() {
        return remainingDay;
    }

    public void setRemainingDay(int remainingDay) {
        this.remainingDay = remainingDay;
    }

    public int getValuePromo() {
        return valuePromo;
    }

    public void setValuePromo(int valuePromo) {
        this.valuePromo = valuePromo;
    }
}
