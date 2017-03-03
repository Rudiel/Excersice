package com.rudielavilaperaza.excersice.Models.Response;

import android.support.annotation.Nullable;

/**
 * Created by rudielavilaperaza on 3/1/17.
 */

public class Product {

    private int id;
    private String name;
    private String description;
    private String price;
    private String display_price;
    private String available_on;
    private String total_on_hand;
    private String shipping_category_id;

    public String getShipping_category_id() {
        return shipping_category_id;
    }

    public void setShipping_category_id(String shipping_category_id) {
        this.shipping_category_id = shipping_category_id;
    }

    public String getTotal_on_hand() {
        return total_on_hand;
    }

    public void setTotal_on_hand(String total_on_hand) {
        this.total_on_hand = total_on_hand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDisplay_price() {
        return display_price;
    }

    public void setDisplay_price(String display_price) {
        this.display_price = display_price;
    }

    public String getAvailable_on() {
        return available_on;
    }

    public void setAvailable_on(String available_on) {
        this.available_on = available_on;
    }
}
