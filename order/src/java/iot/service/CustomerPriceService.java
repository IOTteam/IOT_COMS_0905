/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.CustomerPrice;
import iot.dao.entity.CustomerMaster;
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
public class CustomerPriceService {
      @Autowired
      private EntityManagerFactory emf;//声明一个实体管理工厂，创建实体管理，使用实体管理操作数据库
      
      public List<CustomerPrice> queryCustomerPrice(String CustomerName,String ProductName,String PriceMin,String PriceMax,String RangesMin,String RangesMax){
          CustomerMasterDAO cmdao=new CustomerMasterDAO(emf);
          List<CustomerMaster> cms=cmdao.findCustomerMasterByName(CustomerName);
          ProductMasterDAO pmdao=new ProductMasterDAO(emf);
          List<ProductMaster> pms=pmdao.findProductMasterByProductName(ProductName);
          CustomerPriceDAO cpdao=new CustomerPriceDAO(emf);
          List<CustomerPrice> cp=new ArrayList<CustomerPrice>();
          CustomerMaster cm=new CustomerMaster();
          ProductMaster pm=new ProductMaster();
           
          System.out.println(cms.size());
          System.out.println(pms.size());
          
       if(cms.size()==0&&pms.size()==0){

          return cpdao.findCustomerPriceAllCondition(cm, pm, PriceMin, PriceMax, RangesMin, RangesMax);
       }
       
       
       else if(cms.isEmpty()&&!pms.isEmpty()){
           for(int i=0;i<pms.size();i++){
           cp.addAll(cpdao.findCustomerPriceAllCondition( cm, pms.get(i), PriceMin, PriceMax, RangesMin, RangesMax));
           }
           
       }
       else if(cms.size()!=0&&pms.size()==0){
           for(int i=0;i<cms.size();i++){
           cp.addAll(cpdao.findCustomerPriceAllCondition( cms.get(i),pm,  PriceMin, PriceMax, RangesMin, RangesMax));
           }
       }
       else {
           for(int i=0;i<cms.size();i++){
               for(int j=0;j<pms.size();j++){
                    cp.addAll(cpdao.findCustomerPriceAllCondition( cms.get(i), pms.get(j), PriceMin, PriceMax, RangesMin, RangesMax));
               }
           }
      }
          return cp;

      }
      
      
    
}
