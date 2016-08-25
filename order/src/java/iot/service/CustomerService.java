/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.CustomerPriceInfo;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.CustomerPriceDAO;
import iot.dao.repository.ProductMasterDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hatanococoro
 */

@Service
public class CustomerService {
    
    @Autowired
    private EntityManagerFactory emf;
    
    public void addCustomerAndPrice(String customerId, String customerMail, String customerName, String customerPhone, List<CustomerPriceInfo> customerPriceInfos ) throws Exception{
    
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        CustomerMaster cmNew = new CustomerMaster();
        cmNew.setCustomerId(customerId);
        cmNew.setCustomerMail(customerMail);
        cmNew.setCustomerName(customerName);
        cmNew.setCustomerPhone(customerPhone);
        cmNew.setCustomerMasterId(Integer.parseInt(customerId));
        cmdao.create(cmNew);
        
        ProductMasterDAO pmdao = new ProductMasterDAO(emf);
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        
        CustomerPrice customerPrice = new CustomerPrice();
        for (int i = 0; i < customerPriceInfos.size(); i++) {
            
            ProductMaster productMasterId = pmdao.findProductMasterByproductId(customerPriceInfos.get(i).getProductId());
            
            customerPrice.setCustomerMasterId(cmNew);
            customerPrice.setProductMasterId(productMasterId);
            customerPrice.setRangePrice(Float.parseFloat(customerPriceInfos.get(i).getPreferentialCredit()));
            customerPrice.setRanges(Integer.parseInt(customerPriceInfos.get(i).getPreferentialMin()));
            customerPrice.setStatus("0");
            
            cpdao.create(customerPrice);
            
        }
        
        
    }
    
}
