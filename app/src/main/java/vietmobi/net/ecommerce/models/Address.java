package vietmobi.net.ecommerce.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "all_address")
public class Address implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String fullName;
    private String address;
    private String district;
    private String province;
    private String country;
    private String numberPhone;
    private Boolean isSelected;

    public Address(String fullName, String address, String district, String province, String country, String numberPhone, Boolean isSelected) {
        this.fullName = fullName;
        this.address = address;
        this.district = district;
        this.province = province;
        this.country = country;
        this.numberPhone = numberPhone;
        this.isSelected = isSelected;
    }

    public Address(String name, String address, String district, String city, String phone, boolean b) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
