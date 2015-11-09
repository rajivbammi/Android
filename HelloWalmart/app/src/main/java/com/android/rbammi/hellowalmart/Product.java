package com.android.rbammi.hellowalmart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rbammi on 11/9/15.
 */
public class Product {
    private long itemId;
    private String itemName;
    private double salePrice;
    private long upc;
    private String description;
    private String imgUrl;
    private String productUrl;
    private String addToCartURL;
    private boolean isAvailableOnline;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public long getUpc() {
        return upc;
    }

    public void setUpc(long upc) {
        this.upc = upc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getAddToCartURL() {
        return addToCartURL;
    }

    public void setAddToCartURL(String addToCartURL) {
        this.addToCartURL = addToCartURL;
    }

    public boolean isAvailableOnline() {
        return isAvailableOnline;
    }

    public void setIsAvailableOnline(boolean isAvailableOnline) {
        this.isAvailableOnline = isAvailableOnline;
    }

    public static Product fromJson(JSONObject jsonObject) {
        Product product = new Product();
        try {
            product.itemId = jsonObject.getLong("itemId");
            product.itemName = jsonObject.getString("name");
            product.upc = jsonObject.getLong("upc");
            product.description = jsonObject.getString("shortDescription");
            product.imgUrl = jsonObject.getString("thumbnailImage");
            product.productUrl = jsonObject.getString("productUrl");
            product.addToCartURL = jsonObject.getString("addToCartUrl");
            product.isAvailableOnline = jsonObject.getBoolean("availableOnline");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static ArrayList<Product> fromJsonArray(JSONArray jsonArray) {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i< jsonArray.length(); i++) {
            try {
                JSONObject jsonObject =  jsonArray.getJSONObject(i);
                Product product = Product.fromJson(jsonObject);
                if (product != null) {
                    products.add(product);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        return products;
    }
}
