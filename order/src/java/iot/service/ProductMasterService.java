/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.ProductMaster;
import iot.dao.repository.ProductMasterImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dell
 */
@Service
public class ProductMasterService {

    @Autowired(required = false)
    private ProductMasterImpl productMasterImpl;

    public void addproductMaster(ProductMaster productMaster) {
        this.productMasterImpl.save(productMaster);
    }

    public ProductMaster findByProductId(String productId) {
        return this.productMasterImpl.findByProductId(productId);
    }

    public void modifyProductMaster(ProductMaster productMasterold) {
        this.productMasterImpl.save(productMasterold);
    }

    public void removeProductMaster(ProductMaster productMaster) {
        productMaster.setStatus("0");
        this.productMasterImpl.save(productMaster);
    }
    
}
