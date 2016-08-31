/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.CusPriceQueryResult;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.CustomerMaster;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.CustomerPriceDAO;
import iot.dao.repository.ProductMasterDAO;
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
public class CustomerPriceService {

    @Autowired
    private EntityManagerFactory emf;//声明一个实体管理工厂，创建实体管理，使用实体管理操作数据库

    public List<CustomerPrice> queryCustomerPrice(String CustomerName, String ProductName, String PriceMin, String PriceMax, String RangesMin, String RangesMax) {
        //通过模糊查询客户的名称找到客户实体
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        List<CustomerMaster> cms = cmdao.findCustomerMasterByName(CustomerName);
        //通过模糊查询产品名称找到产品实体
        ProductMasterDAO pmdao = new ProductMasterDAO(emf);
        List<ProductMaster> pms = pmdao.findProductMasterByProductName(ProductName);
        //构造一个CustomerPriceDAO进行持久层管理
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        //构造一个ArrayList<CustomerPrice>集合和CustomerMaster和ProductMaster备用
        List<CustomerPrice> cp = new ArrayList<CustomerPrice>();
        CustomerMaster cm = new CustomerMaster();
        ProductMaster pm = new ProductMaster();

      //根据客户名字且产品名字的查询结果为空作以下判断
        if (cms.size() == 0 && pms.size() == 0) {

            return cpdao.findCustomerPriceAllCondition(cm, pm, PriceMin, PriceMax, RangesMin, RangesMax);
        } else if (cms.isEmpty() && !pms.isEmpty()) {//根据客户名字为空但是产品名字的查询结果不为空作以下判断
            for (int i = 0; i < pms.size(); i++) {
                cp.addAll(cpdao.findCustomerPriceAllCondition(cm, pms.get(i), PriceMin, PriceMax, RangesMin, RangesMax));
            }

        } else if (cms.size() != 0 && pms.size() == 0) {//根据客户名字不为空但是产品名字的查询结果为空作以下判断
            for (int i = 0; i < cms.size(); i++) {
                cp.addAll(cpdao.findCustomerPriceAllCondition(cms.get(i), pm, PriceMin, PriceMax, RangesMin, RangesMax));
            }
        } else {//根据客户名字且产品名字的查询结果都不为空作以下判断
            //通过for循环获取两个实体并将实体传入实体层进行处理
            for (int i = 0; i < cms.size(); i++) {
                for (int j = 0; j < pms.size(); j++) {
                    cp.addAll(cpdao.findCustomerPriceAllCondition(cms.get(i), pms.get(j), PriceMin, PriceMax, RangesMin, RangesMax));
                }
            }
        }
        return cp;

    }

    //分页
    public HashMap divPage(String CustomerName, String ProductName, String PriceMin, String PriceMax, String RangesMin, String RangesMax, int pageNo) {

        List<CustomerPrice> cpListAll = this.queryCustomerPrice(CustomerName, ProductName, PriceMin, PriceMax, RangesMin, RangesMax);
        HashMap map = new HashMap();

        int totalPage = cpListAll.size() / 10 + 1;

        map.put("totalPage", totalPage);

        List cpList = new ArrayList<CusPriceQueryResult>();

        int index = pageNo * 10;
        int index_next = index + 10;

        if (cpListAll.size() == 0) {

            map.put("cpList", null);
            return map;
        }

        if (index < cpListAll.size() && index_next < cpListAll.size()) {

            for (; index < index_next; index++) {

                List list = new ArrayList();
                list.add(cpListAll.get(index).getCustomerPriceId());
                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerId());
                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerName());
                list.add(cpListAll.get(index).getProductMasterId().getProductId());
                list.add(cpListAll.get(index).getProductMasterId().getProductName());
                list.add(cpListAll.get(index).getRanges());
                list.add(cpListAll.get(index).getRangePrice());

                cpList.add(list);

            }

        } else if (cpListAll.size() < index_next) {

            for (; index < cpListAll.size(); index++) {

                List list = new ArrayList();
                list.add(cpListAll.get(index).getCustomerPriceId());
                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerId());
                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerName());
                list.add(cpListAll.get(index).getProductMasterId().getProductId());
                list.add(cpListAll.get(index).getProductMasterId().getProductName());
                list.add(cpListAll.get(index).getRanges());
                list.add(cpListAll.get(index).getRangePrice());

                cpList.add(list);

            }

        } else {
            for (; index < cpListAll.size(); index++) {

                List list = new ArrayList();
                list.add(cpListAll.get(index).getCustomerPriceId());
                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerId());
                list.add(cpListAll.get(index).getCustomerMasterId().getCustomerName());
                list.add(cpListAll.get(index).getProductMasterId().getProductId());
                list.add(cpListAll.get(index).getProductMasterId().getProductName());
                list.add(cpListAll.get(index).getRanges());
                list.add(cpListAll.get(index).getRangePrice());
                cpList.add(list);

            }

            int cp_size = cpList.size();
            for (; cp_size < 10; cp_size++) {
                List list_null = new ArrayList();
                list_null.add("");
                list_null.add("");
                list_null.add("");
                list_null.add("");
                list_null.add("");
                list_null.add("");
                list_null.add("");

                cpList.add(list_null);
            }

        }

        map.put("cpList", cpList);
        return map;

    }

    //进入时默认
}
