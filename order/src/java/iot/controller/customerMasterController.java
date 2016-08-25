/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerMaster;
import iot.dao.entity.CustomerPrice;
import iot.dao.entity.CustomerPriceInfo;
import iot.dao.entity.Product;
import iot.dao.entity.ProductMaster;
import iot.dao.repository.CustomerMasterDAO;
import iot.dao.repository.ProductMasterDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author GaryLiu
 */
@Controller
@RequestMapping("/CustInfo")
public class customerMasterController {
    
    @Autowired
    private EntityManagerFactory emf;
    
    @RequestMapping(value = "CustQuery",method = RequestMethod.GET)//前端传来的请求类型为GET,若是该类型的请求有2个，则需要设置value值以区分
    public String customerQueryString(ModelMap modelMap){//modelmap用以存储controller执行相关操作的结果
        
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);//CustomerMasterJpaController为通过实体类（customerMaster）创建的JPA控制器
        List<CustomerMaster> customerMasters = cmdao.findCustomerMasterEntities();//CustomerMasterJpaController自动生成的方法，查询所有的customerMaster，存在LIST中返回
        modelMap.addAttribute("cmList", customerMasters);//将结果返回至前端cmlist
        return "CustInfo";
    } 
    @RequestMapping(value = "CustQuery",method = RequestMethod.POST)
//前端传来的请求类型为POST,若是该类型的请求有2个，则需要设置value值以区分
    public String queryByidnameString(@RequestParam("customerId") String customerId,@RequestParam("customerName") String customerName,ModelMap modelMap){//RequestParam将前端输入的值作为函数的参数。最后的modelMap需要有
      

        CustomerMasterDAO controller = new CustomerMasterDAO(emf);
//CustomerMasterJpaController为通过实体类（customerMaster）创建的JPA控制器
        List<CustomerMaster> customerMasters = controller.queryCustomerMasterByIdName(customerName, customerId);
//自动生成的函数中有使用主键查询的函数，但此处需要按name、ID、name+ID3种方式查询。因此需要在CustomerMasterJpaController中实现queryCustomerMasterByIdName函数
        if (customerMasters.size()==0) {
//size为0说明查无此人，应向前端反馈错误
            modelMap.addAttribute("cmqf","queryFailed");
        }
        modelMap.addAttribute("cmList", customerMasters);
        return "CustInfo";
    }
    
    
    //跳转到新增客户页面
    @RequestMapping(value = "CustAdd",method = RequestMethod.GET)
    public String customerAddPage(ModelMap model){
         

        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        int count = cmdao.getCustomerMasterCount();
        
        ProductMasterDAO pmdao = new ProductMasterDAO(emf);
        List<ProductMaster> pms = pmdao.findProductMasterEntities();

        model.addAttribute("pmList",pms);
        model.addAttribute("count", count+11);
        return "custAdd";
        
    }
    
    @RequestMapping(value = "CustAdd",method = RequestMethod.POST)
    public String customerAdd(@RequestParam("customerId") String customerId,@RequestParam("customerName") String customerName,
            @RequestParam("customerMail") String customerMail,@RequestParam("customerPhone") String customerPhone,ModelMap model) throws Exception{
         
        CustomerMasterDAO cmdao = new CustomerMasterDAO(emf);
        CustomerMaster cmNew = new CustomerMaster();
        cmNew.setCustomerId(customerId);
        cmNew.setCustomerMail(customerMail);
        cmNew.setCustomerName(customerName);
        cmNew.setCustomerPhone(customerPhone);
        cmNew.setCustomerMasterId(Integer.parseInt(customerId));
        cmdao.create(cmNew);

        return "redirect:/CustInfo/CustQuery";
        
    }
    
    
    @RequestMapping(value = "getProductPrice",method = RequestMethod.GET)
    @ResponseBody
    public Product getProductPrice(@RequestParam("productId") String productId ,ModelMap model){
        
        ProductMasterDAO pmdao = new ProductMasterDAO(emf);
        ProductMaster pm = pmdao.findProductMasterByproductId(productId);

        Product product = new Product();
        
        product.setProductId(pm.getProductId());
        product.setProductMasterId(pm.getProductMasterId());
        product.setProductName(pm.getProductName());
        product.setProductPrice(pm.getProductPrice());
        product.setProductSpec(pm.getProductSpec());
        //return ""+ pm.getProductPrice() +"";
        
        return product;
        
    }
    
    private List<CustomerPriceInfo> customerPriceInfos = new ArrayList<CustomerPriceInfo>();
    
    @RequestMapping(value = "setCusProPrice",method = RequestMethod.POST)
    @ResponseBody
    public List<CustomerPriceInfo> setCusProPrice(@RequestParam("customerId") String customerId,@RequestParam("productId") String productId,
            @RequestParam("preferentialMin") String preferentialMin,@RequestParam("preferentialMax") String preferentialMax,
            @RequestParam("preferentialCredit") String preferentialCredit){
        
        CustomerPriceInfo customerPriceInfo = new CustomerPriceInfo();
        customerPriceInfo.setCustomerId(customerId);
        customerPriceInfo.setProductId(productId);
        customerPriceInfo.setPreferentialMin(preferentialMin);
        customerPriceInfo.setPreferentialMax(preferentialMax);
        customerPriceInfo.setPreferentialCredit(preferentialCredit);
        
        customerPriceInfos.add(customerPriceInfo);
        
        return customerPriceInfos;
        
    }
    
}
