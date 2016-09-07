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
import java.util.Map;
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
    
    private int customerIndex = 0;
    private int productIndex = 0;
    private int customerPriceIndex = 0;
    private List cpsList = new ArrayList();

    public Map queryCustomerPrice(String CustomerName, String ProductName, String PriceMin, String PriceMax, String RangesMin, String RangesMax, int pageNo) {
       
        customerPriceIndex = pageNo*10;
        //通过模糊查询客户的名称找到客户实体
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        List<CustomerMaster> cms = cmdao.findCustomerMasterByName(CustomerName);
        
        //通过模糊查询产品名称找到产品实体
        ProductMasterDAO pmdao = new ProductMasterDAO(emf);
        List<ProductMaster> pms = pmdao.findProductMasterByProductName(ProductName);
        
        //构造一个CustomerPriceDAO进行持久层管理
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        
        Map customerPrices = cpdao.findCustomerPricesByCondition(cms, pms, PriceMin, PriceMax, RangesMin, RangesMax, 10, customerPriceIndex);

        return customerPrices;

    }

    //手动分页
//    public HashMap divPage(String CustomerName, String ProductName, String PriceMin, String PriceMax, String RangesMin, String RangesMax, int pageNo) {
//
//        List<CustomerPrice> cpListAll = this.queryCustomerPrice(CustomerName, ProductName, PriceMin, PriceMax, RangesMin, RangesMax);
//        HashMap map = new HashMap();
//
//        int totalPage = cpListAll.size() / 10 + 1;
//
//        map.put("totalPage", totalPage);
//
//        List cpList = new ArrayList<CusPriceQueryResult>();
//
//        int index = pageNo * 10;
//        int index_next = index + 10;
//
//        if (cpListAll.size() == 0) {
//
//            map.put("cpList", null);
//            return map;
//        }
//        
//         if (cpListAll.size() < index_next && index == 0) {
//
//            for (; index < cpListAll.size(); index++) {
//
//                List list = new ArrayList();
//                list.add(cpListAll.get(index).getCustomerPriceId());
//                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerId());
//                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerName());
//                list.add(cpListAll.get(index).getProductMasterId().getProductId());
//                list.add(cpListAll.get(index).getProductMasterId().getProductName());
//                list.add(cpListAll.get(index).getRanges());
//                list.add(cpListAll.get(index).getRangePrice());
//
//                cpList.add(list);
//
//            }
//
//        }else if(index < cpListAll.size() && index_next < cpListAll.size()) {
//
//            for (; index < index_next; index++) {
//
//                List list = new ArrayList();
//                list.add(cpListAll.get(index).getCustomerPriceId());
//                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerId());
//                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerName());
//                list.add(cpListAll.get(index).getProductMasterId().getProductId());
//                list.add(cpListAll.get(index).getProductMasterId().getProductName());
//                list.add(cpListAll.get(index).getRanges());
//                list.add(cpListAll.get(index).getRangePrice());
//
//                cpList.add(list);
//
//            }
//
//        } else {
//            for (; index < cpListAll.size(); index++) {
//
//                List list = new ArrayList();
//                list.add(cpListAll.get(index).getCustomerPriceId());
//                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerId());
//                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerName());
//                list.add(cpListAll.get(index).getProductMasterId().getProductId());
//                list.add(cpListAll.get(index).getProductMasterId().getProductName());
//                list.add(cpListAll.get(index).getRanges());
//                list.add(cpListAll.get(index).getRangePrice());
//                cpList.add(list);
//
//            }
//
//            int cp_size = cpList.size();
//            for (; cp_size < 10; cp_size++) {
//                List list_null = new ArrayList();
//                list_null.add("");
//                list_null.add("");
//                list_null.add("");
//                list_null.add("");
//                list_null.add("");
//                list_null.add("");
//                list_null.add("");
//
//                cpList.add(list_null);
//            }
//
//        }
//
//        map.put("cpList", cpList);
//        return map;
//
//    }

}
