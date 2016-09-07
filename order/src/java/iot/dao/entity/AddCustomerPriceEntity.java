/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.entity;

/**
 *
 * @author dell
 */
public class AddCustomerPriceEntity {
     public Integer customerMasterId;
    public Integer ranges;
    public Float rangePrice;
    public String proname;

    public Integer getCustomerMasterId() {
        return customerMasterId;
    }

    public void setCustomerMasterId(Integer customerMasterId) {
        this.customerMasterId = customerMasterId;
    }

    public Integer getRanges() {
        return ranges;
    }

    public void setRanges(Integer ranges) {
        this.ranges = ranges;
    }

    public Float getRangePrice() {
        return rangePrice;
    }

    public void setRangePrice(Float rangePrice) {
        this.rangePrice = rangePrice;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }
    
}
