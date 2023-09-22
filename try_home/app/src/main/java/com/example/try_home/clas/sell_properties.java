package com.example.try_home.clas;

public class sell_properties {
    private String address,bhk_type,nearby,prop_furn,prop_type,price;
    private byte by[];

    public sell_properties(String address, String bhk_type, String nearby, String prop_furn, String prop_type, String price, byte[] by) {
        this.address = address;
        this.bhk_type = bhk_type;
        this.nearby = nearby;
        this.prop_furn = prop_furn;
        this.prop_type = prop_type;
        this.price = price;
        this.by = by;
    }

    public sell_properties()
    {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBhk_type() {
        return bhk_type;
    }

    public void setBhk_type(String bhk_type) {
        this.bhk_type = bhk_type;
    }

    public String getNearby() {
        return nearby;
    }

    public void setNearby(String nearby) {
        this.nearby = nearby;
    }

    public String getProp_furn() {
        return prop_furn;
    }

    public void setProp_furn(String prop_furn) {
        this.prop_furn = prop_furn;
    }

    public String getProp_type() {
        return prop_type;
    }

    public void setProp_type(String prop_type) {
        this.prop_type = prop_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getBy() {
        return by;
    }

    public void setBy(byte[] by) {
        this.by = by;
    }
}
