/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.common.Constants;
import iot.dao.entity.AddCustomerPriceEntity;
import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.CustomerPriceDAO;
import iot.dao.repository.OrderDetailDAO;
import iot.dao.repository.ProductMasterImpl;
import iot.service.CustomerPriceService;
import iot.service.ProductMasterService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author dell
 */
@Controller
@RequestMapping("/productMaster")
public class productMasterController { 
    @Autowired(required = false)
    private ProductMasterService productMasterService;
    
    @Autowired(required = false)
    private ProductMasterImpl productMasterImpl;
   
    @Autowired
    private EntityManagerFactory emf;
    
    @Autowired(required = false)
    private CustomerPriceService customerPriceService;
    
    @RequestMapping(value = "loadProductMaster")
    public String loadProductMaster( Model model,ProductMaster productMaster){
        
        Iterable<ProductMaster> productMasterList = productMasterImpl.findByStatus();//查询所有有效数据以Iterable形式存储
        model.addAttribute("productMasterList", productMasterList);//将数据提交到前台
          
        return "productMaster/Pro_Master";//跳转到相应的页面
    }
    
    @RequestMapping(value = "loadProductMaster",method = RequestMethod.POST)
    public String queryProductMaster(@RequestParam("productId") String productId,@RequestParam("productName") String productName,@RequestParam("productSpec") String productSpec,
            @RequestParam("priceMin") String priceMin,@RequestParam("priceMax") String priceMax,ServletRequest request,Model model){
        /***创建工厂***/
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("orderPU");
        EntityManager em = emf.createEntityManager();
        /***根据不同条件拼接SQL语句***/
        String sql = "SELECT p FROM ProductMaster p WHERE 1=1 and p.status != '0' ";//查询有效数据
        if(!productId.equals("")){
            sql = sql +"and p.productId =:productId ";
        }
        if(!productName.equals("")){
            sql = sql +"and p.productName LIKE :productName ";
        }
        if(!productSpec.equals("")){
            sql = sql +"and p.productSpec LIKE :productSpec ";
        }
        /***如果将priceMin定义为Float类型，当productName以及productName做为联合查询条件时，为抛出异常**/
        if(priceMin.length() > 0){
            sql = sql +"and p.productPrice >= :priceMin ";
        }
        /***如果将priceMax定义为Float类型，当productName以及productName做为联合查询条件时，为抛出异常**/
        if(0 < priceMax.length()){
            sql = sql +"and p.productPrice <= :priceMax ";
        }
        Query query = em.createQuery(sql);//根据SQL查询数据库
        /****根据条件设置参数****/
        if(!productId.equals("")){
            query.setParameter("productId", productId);
        }
        if(!productName.equals("")){
            query.setParameter("productName", "%"+productName+"%");
        }
        if(!productSpec.equals("")){
            query.setParameter("productSpec", "%"+productSpec+"%");
        }
        /***如果将priceMin定义为Float类型，当productName以及productName做为联合查询条件时，为抛出异常**/
        if(priceMin.length() > 0){
            query.setParameter("priceMin", Float.parseFloat(priceMin));
        }
        /***如果将priceMax定义为Float类型，当productName以及productName做为联合查询条件时，为抛出异常**/
        if(priceMax.length()>0){
            query.setParameter("priceMax", Float.parseFloat(priceMax));
        }
        List<ProductMaster> productMasterList = query.getResultList();//将查询结果以List形式存储
        model.addAttribute("productMasterList", productMasterList);//提交数据给前端
        return "productMaster/Pro_Master";//跳转页面
    }
    
    @RequestMapping(value = "addProductMasterInit")
    public String addProductMasterInit(ProductMaster productMaster,Model model){
        Integer count = new Long(productMasterImpl.count()+1).intValue();//将count从Long型转换为Integer型
        productMaster.setProductId(count.toString());//对ProductId进行自动编号
        productMaster.setProductMasterId(count);//设置主键ProductMasterId的值
        CustomerMasterDAO customerMasterDAO = new CustomerMasterDAO(emf);
        List<CustomerMaster> customerMasterList = customerMasterDAO.findCustomerMasterEntities();//将CustomerMaster表作为一个List保存
        model.addAttribute("productMaster", productMaster);//将数据提交到前台
        model.addAttribute("customerMasterList", customerMasterList);//将customerMasterList提交到前台
        return "productMaster/Pro_Master_add";
    }
    
    @RequestMapping(value = "addProductMaster",method = RequestMethod.POST)
    public String addProductMaster(ProductMaster productMaster,ServletRequest request) throws Exception{
        
        this.productMasterService.addproductMaster(productMaster);//通过调用productMasterService中的addproductMaster方法将数据保存到数据库
        if(productMaster.getStatus().equals(Constants.DATA_STATUS.ENABLED)){//判断产品有优惠的情况
            CustomerPriceDAO customerPriceDAO = new CustomerPriceDAO(emf);
            List<CustomerPrice> cp = customerPriceDAO.findCustomerPriceEntities();//创建cp对象
            Integer count = cp.size();//根据CustomerPriceId统计customerPrice表的数据总量
            this.customerPriceService.setNewCustomerPrice(productMaster, count);//调用setNewCustomerPrice方法，进行对customerPrice表的操作
        }
        
        return "redirect:/productMaster/loadProductMaster";
        
    }
    
    @RequestMapping(value = "setCustomerPrice",method = RequestMethod.POST)
    @ResponseBody
    public List<AddCustomerPriceEntity> setCustomerPrice(@RequestParam("proname") String proname,@RequestParam("ranges") Integer ranges,@RequestParam("customerMasterId") Integer customerMasterId,
        @RequestParam("rangePrice") Float rangePrice) throws Exception{        
        
        return customerPriceService.setCustomerPrice(customerMasterId, ranges, rangePrice, proname);
    }
    /**
     *
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping(value = "modifyProductMasterInit")
    public String modifyProductMasterInit(@RequestParam("productId") String productId,Model model){

        CustomerMasterDAO customerMasterDAO = new CustomerMasterDAO(emf);//创建customerMasterDAO对象
        List<CustomerMaster> customerMasterList = customerMasterDAO.findCustomerMasterEntities();//将CustomerMaster表作为一个List保存
        ProductMaster productMaster = this.productMasterService.findByProductId(productId);//根据productId查询相应的数据存入实体类对象productMaster中
        model.addAttribute("productMaster", productMaster);//将productMaster对象传入前端页面
        model.addAttribute("customerMasterList", customerMasterList);//将customerMasterList提交到前台
        return "productMaster/Pro_Master_modify";//跳转到Pro_Master_modify页面
    }
    
    @RequestMapping(value = "modifyProductMaster")
    public String modifyProductMaster(ProductMaster productMaster) throws Exception{

        ProductMaster productMasterOld = this.productMasterService.findByProductId(productMaster.getProductId());//根据productId查询相应的数据存入实体类对象productMasterOld中
        CustomerPriceDAO customerPriceDAO = new CustomerPriceDAO(emf);//创建customerPriceDAO对象
        List<CustomerPrice> customerPrice = customerPriceDAO.findByProductMasterId(productMaster);//根据findByProductMasterId方法将CustomerPrice表中的数据存入customerPrice对象
        //建立for循环，根据ranges判断，当ranges小于1000时，如果产品的标准价格更改，则CustomerPrice的价格要相应的进行更改
        for(int i = 0;i < customerPrice.size();i++){
            if(customerPrice.get(i).getRanges()<1000){
                customerPrice.get(i).setRangePrice(productMaster.getProductPrice());
            }
        }
        try {
            BeanUtils.copyProperties(productMaster, productMasterOld);//调用BeanUtils中的copyProperties方法将修改后的信息更新到原数据所创建的对象productMasterOld中
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.productMasterService.modifyProductMaster(productMasterOld);//调用productMasterService的modifyProductMaster方法保存productMasterOld对象
        if(productMasterOld.getStatus().equals(Constants.DATA_STATUS.ENABLED)){//判断产品有优惠的情况
            List<CustomerPrice> cp = customerPriceDAO.findCustomerPriceEntities();//创建cp对象
            Integer count = cp.size();//根据CustomerPriceId统计customerPrice表的数据总量
            this.customerPriceService.setNewCustomerPrice(productMasterOld, count);//调用setNewCustomerPrice方法，进行对customerPrice表的操作
        }else if(productMasterOld.getStatus().equals(Constants.DATA_STATUS.DISCOUNT)){//判断产品无优惠的情况
            this.customerPriceService.removeCustomerPrice(productMasterOld);//调用removeCustomerPrice方法
        }
        return "redirect:/productMaster/loadProductMaster";
    }
    
    @RequestMapping("removeProductMaster")
    public String removeProductMaster(String productId){
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(emf);//创建orderDetailDAO对象
        ProductMaster productMaster = this.productMasterService.findByProductId(productId);//根据productId查询相应的数据存入实体类对象productMaster中
        Integer count = new Long(orderDetailDAO.count(productMaster)).intValue();//根据productId统计此产品所属的订单数量
        //如果根据productId统计此产品所属的订单数量为0，则此产品可以进行删除，反之则不能删除
        if(count == 0){
            this.productMasterService.removeProductMaster(productMaster);//调用productMasterService的removeProductMaster方法对数据进行删除
            return "redirect:/productMaster/loadProductMaster";
        }else{
            return "redirect:/productMaster/loadProductMaster";
        }
    }
}
    
