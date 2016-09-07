/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.service;

import iot.dao.entity.AddCustomerPriceEntity;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.CustomerMaster;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.CustomerPriceDAO;
import iot.dao.repository.CustomerPriceImpl;
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

    private List<AddCustomerPriceEntity> addCustomerPriceEntitys = new ArrayList<AddCustomerPriceEntity>();
    @Autowired
    private EntityManagerFactory emf;//声明一个实体管理工厂，创建实体管理，使用实体管理操作数据库
    
    @Autowired(required = false)
    private CustomerPriceImpl customerPriceImpl;
    
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
    
    public List<AddCustomerPriceEntity> setCustomerPrice(Integer customerMasterId,Integer ranges, Float rangePrice,String proname) 
            throws Exception {
        AddCustomerPriceEntity addCustomerPriceEntity = new AddCustomerPriceEntity();
        addCustomerPriceEntity.setCustomerMasterId(customerMasterId);
        addCustomerPriceEntity.setProname(proname);
        addCustomerPriceEntity.setRangePrice(rangePrice);
        addCustomerPriceEntity.setRanges(ranges);
        
        addCustomerPriceEntitys.add(addCustomerPriceEntity);
        return addCustomerPriceEntitys;
        
    }
    
    public void setNewCustomerPrice(ProductMaster productMaster,Integer count) throws Exception {
        CustomerMasterDAO customerMasterDAO = new CustomerMasterDAO(emf);//创建customerMasterDAO对象
        CustomerPriceDAO customerPriceDAO = new CustomerPriceDAO(emf);//创建customerPriceDAO对象
        CustomerPrice customerPrice = new CustomerPrice();//创建customerPrice对象
        List<CustomerMaster> customerMasterList = customerMasterDAO.findCustomerMasterEntities();//查询CustomerMaster表中的所有数据
        Integer ranges[] ={1,1000,2000,4000};
        Float per[] = {1.0f,0.9f,0.8f,0.7f};
        /**根据数据库的联系，当新增产品时，要增加的customerPrice数量共有customerName * ranges条，所以用了两个for循环**/
        for(int i = 1; i < customerMasterList.size()+1; i++){

                for(int j = 1; j < 5; j++){
                    count = count + 1;
                    customerPrice.setCustomerMasterId(customerMasterList.get(i-1));//设置customerMasterId的值
                    customerPrice.setProductMasterId(productMaster);//设置productMasterId的值
                    customerPrice.setRangePrice(productMaster.getProductPrice()*per[j-1]);//设置相应的值
                    customerPrice.setCustomerPriceId(count);//设置相应的值
                    customerPrice.setRanges(ranges[j-1]);//有优惠则暂时设置Ranges为不同的区间，表示无论购买数量不同，产品的价格不同
                    customerPrice.setStatus(true);//将数据设置为有效
                    customerPriceImpl.save(customerPrice);
//                }
            }
        }
        if(addCustomerPriceEntitys.size()>0){//判断前端页面在新增产品时是否对CustomerPrice表进行操作
            for(int k = 0 ; k < addCustomerPriceEntitys.size() ; k++ ){//循环遍历addCustomerPriceEntitys
                /**根据addCustomerPriceEntitys中的CustomerMasterId找出CustomerMaster表对应的数据，并新建customerMaster对象**/
                CustomerMaster customerMaster = customerMasterDAO.findCustomerMaster(addCustomerPriceEntitys.get(k).getCustomerMasterId());
                CustomerPrice newCustomerPrice = new CustomerPrice();//新建newCustomerPrice对象用于保存前端页面传回的数据
                newCustomerPrice.setCustomerMasterId(customerMaster);//设置相应的值
                newCustomerPrice.setProductMasterId(productMaster);//设置相应的值
                newCustomerPrice.setRangePrice((addCustomerPriceEntitys.get(k).rangePrice)*productMaster.getProductPrice());//设置相应的值
                newCustomerPrice.setRanges(addCustomerPriceEntitys.get(k).getRanges());//设置相应的值
                newCustomerPrice.setStatus(true);//设置相应的值
                /**根据productMaster表中的ProductMasterId查出CustomerPrice表中所有有关的数据，并建立customerPriceList对象**/
                List<CustomerPrice> customerPriceList = customerPriceDAO.findByProductMasterId(productMaster);
                for(int m = 0; m < customerPriceList.size(); m++){//循环遍历customerPriceList
                    /**根据CustomerMasterId以及Ranges找出相应的数据，并修改数据**/
                    if(customerPriceList.get(m).getCustomerMasterId().equals(newCustomerPrice.getCustomerMasterId()) &&
                            customerPriceList.get(m).getRanges() == newCustomerPrice.getRanges()){
                        newCustomerPrice.setCustomerPriceId(customerPriceList.get(m).getCustomerPriceId());//设置相应的值
                    }
                }
                customerPriceDAO.edit(newCustomerPrice);//保存数据   
            }
        }
    }

    public void removeCustomerPrice(ProductMaster productMaster) {
        CustomerPriceDAO customerPriceDAO = new CustomerPriceDAO(emf);//创建customerPriceDAO对象
        /**根据productMaster表中的ProductMasterId查出CustomerPrice表中所有有关的数据，并建立customerPriceList对象**/
        List<CustomerPrice> customerPriceList = customerPriceDAO.findByProductMasterId(productMaster);
        /**如果customerPriceList中存在数据，则对其中的数据进行逻辑删除**/
        if(!customerPriceList.isEmpty()){
            for(int i = 0; i < customerPriceList.size(); i++){//循环遍历customerPriceList
                CustomerPrice customerPrice = new CustomerPrice();//创建customerPrice对象
                customerPrice.setCustomerMasterId(customerPriceList.get(i).getCustomerMasterId());//设置相应的值
                customerPrice.setCustomerPriceId(customerPriceList.get(i).getCustomerPriceId());//设置相应的值
                customerPrice.setProductMasterId(productMaster);//设置相应的值
                customerPrice.setRangePrice(customerPriceList.get(i).getRangePrice());//设置相应的值
                customerPrice.setRanges(0);//设置相应的值
                customerPrice.setStatus(false);//设置相应的值，表示此条数据是无效的
                customerPriceImpl.save(customerPrice);//报存逻辑删除后的数据
            }
        }
    }

}
