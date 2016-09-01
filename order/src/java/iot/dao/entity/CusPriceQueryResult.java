/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.entity;

/**
 *
 * @author hatanococoro
 */
public class CusPriceQueryResult {
    
    private Boolean status;
    private int ranges;
    private float rangePrice;
    private int customerPriceId;
    private int customerMasterId;
    private String customerId;
    private int productMasterId;
    private String productId;
    private String customerName;
    private String productName;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getRanges() {
        return ranges;
    }

    public void setRanges(int ranges) {
        this.ranges = ranges;
    }

    public float getRangePrice() {
        return rangePrice;
    }

    public void setRangePrice(float rangePrice) {
        this.rangePrice = rangePrice;
    }

    public int getCustomerPriceId() {
        return customerPriceId;
    }

    public void setCustomerPriceId(int customerPriceId) {
        this.customerPriceId = customerPriceId;
    }

    public int getCustomerMasterId() {
        return customerMasterId;
    }

    public void setCustomerMasterId(int customerMasterId) {
        this.customerMasterId = customerMasterId;
    }

    public int getProductMasterId() {
        return productMasterId;
    }

    public void setProductMasterId(int productMasterId) {
        this.productMasterId = productMasterId;
    }
     
     
    
}
