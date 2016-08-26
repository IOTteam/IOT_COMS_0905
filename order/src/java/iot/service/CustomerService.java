/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.CustomerPriceInfo;
import iot.dao.entity.Product;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.CustomerPriceDAO;
import iot.dao.repository.ProductMasterDAO;
import iot.dao.repository.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.HashMap;
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
        
    private List<CustomerPriceInfo> customerPriceInfos = new ArrayList<CustomerPriceInfo>();
    
    @Autowired
    private EntityManagerFactory emf;
    
    //添加客户信息和客户产品单价信息
    public void addCustomerAndPrice(String customerId, String customerMail, String customerName, String customerPhone) throws Exception{
    
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        CustomerMaster cmNew = new CustomerMaster();
        cmNew.setCustomerId(customerId);
        cmNew.setCustomerMail(customerMail);
        cmNew.setCustomerName(customerName);
        cmNew.setCustomerPhone(customerPhone);
        cmNew.setCustomerMasterId(Integer.parseInt(customerId));
        cmNew.setStatus(true);
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
            customerPrice.setStatus(true);
            
            cpdao.create(customerPrice);
            
        }
        
        customerPriceInfos.clear();
        
    }
    
    //获取客户列表
    public List<CustomerMaster> customerQuery(){
    
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        List<CustomerMaster> customerMasters = cmdao.findAllCustomerMaster();
        return customerMasters;

    }
    
    //客户条件查询
    public List<CustomerMaster> queryCustomerByIdAndName(String customerId, String customerName){
    
        CustomerMasterDAO controller = new CustomerMasterDAO(emf);
        List<CustomerMaster> customerMasters = controller.queryCustomerMasterByIdName(customerName, customerId);
        return customerMasters;
    }
    
    //获取新增用户页面信息
    public HashMap<String,Object> customerAddPageInfo(){
    
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        int count = cmdao.getCustomerMasterCount();
        
        ProductMasterDAO pmdao = new ProductMasterDAO(emf);
        List<ProductMaster> pms = pmdao.findProductMasterEntities();
        
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("count", count + 1);
        map.put("pms", pms);
        
        return map;
        
    }
    
    //获取产品单价
    public Product getProductPrice(String productId){
    
        ProductMasterDAO pmdao = new ProductMasterDAO(emf);
        ProductMaster pm = pmdao.findProductMasterByproductId(productId);

        Product product = new Product();
        
        product.setProductId(pm.getProductId());
        product.setProductMasterId(pm.getProductMasterId());
        product.setProductName(pm.getProductName());
        product.setProductPrice(pm.getProductPrice());
        product.setProductSpec(pm.getProductSpec());
        
        return product;
    }
    
    //暂时保存客户产品单价信息
    public List<CustomerPriceInfo> setCusProPrice(String customerId,String productId,String preferentialMin,String preferentialMax,String preferentialCredit){
    
        CustomerPriceInfo customerPriceInfo = new CustomerPriceInfo();
        customerPriceInfo.setCustomerId(customerId);
        customerPriceInfo.setProductId(productId);
        customerPriceInfo.setPreferentialMin(preferentialMin);
        customerPriceInfo.setPreferentialMax(preferentialMax);
        customerPriceInfo.setPreferentialCredit(preferentialCredit);
        
        customerPriceInfos.add(customerPriceInfo);
        
        return customerPriceInfos;
        
    }

    //获取客户修改页面所需信息
    public CustomerMaster custEditPageInfo(String customerId){
    
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        CustomerMaster customerMaster = cmdao.findCustomerMasterById(customerId);
        
        return customerMaster;
    }
    
    //修改客户信息                      
    public void custEdit(String customerId, String customerName, String customerMail, String customerPhone) throws Exception{
    
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        CustomerMaster customerMaster = cmdao.findCustomerMasterById(customerId);
        customerMaster.setCustomerMail(customerMail);
        customerMaster.setCustomerName(customerName);
        customerMaster.setCustomerPhone(customerPhone);
        
        cmdao.edit(customerMaster);
    }
    
    //删除客户信息
    public void custDel(String customerId) throws NonexistentEntityException, Exception{
    
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        CustomerMaster customerMaster = cmdao.findCustomerMasterById(customerId);
        customerMaster.setStatus(false);
        
        cmdao.edit(customerMaster);
    }
}
