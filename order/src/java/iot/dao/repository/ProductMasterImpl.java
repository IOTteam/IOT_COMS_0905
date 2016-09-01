/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.dao.repository;

import iot.dao.entity.ProductMaster;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author hatanococoro
 */
public interface ProductMasterImpl extends PagingAndSortingRepository<ProductMaster, String>,JpaSpecificationExecutor<ProductMaster>{

    @Query("SELECT p FROM ProductMaster p WHERE p.productId = ?1")
    public ProductMaster findByProductId(String productId);
//
//    @Query("SELECT p FROM ProductMaster p WHERE p.productId = ?1")
//    public ProductMaster loadByProductId(String productId);

    @Query("SELECT p FROM ProductMaster p WHERE p.status = '1'")
    public Iterable<ProductMaster> findByStatus();
    
}
