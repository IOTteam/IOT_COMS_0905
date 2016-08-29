/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerPriceInfo;
import iot.dao.entity.Product;
import iot.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private CustomerService customerService;
    
    //进入客户页面时的默认查询
    @RequestMapping(value = "CustQuery",method = RequestMethod.GET)
    public String customerQuery(ModelMap modelMap){
        
        modelMap.addAttribute("cmList", customerService.customerQuery());
        return "custInfo";
    } 
    
    //客户条件查询
    @RequestMapping(value = "CustQuery",method = RequestMethod.POST)
    public String queryCustomerByIdAndName(@RequestParam("customerId") String customerId,@RequestParam("customerName") String customerName,ModelMap modelMap){//RequestParam将前端输入的值作为函数的参数。最后的modelMap需要有
            
        modelMap.addAttribute("cmList", customerService.queryCustomerByIdAndName(customerId, customerName));
        return "custInfo";
    }
    
    
    //跳转到新增客户页面
    @RequestMapping(value = "CustAdd",method = RequestMethod.GET)
    public String customerAddPage(ModelMap model){

        model.addAttribute("pmList", customerService.customerAddPageInfo().get("pms"));
        model.addAttribute("count", customerService.customerAddPageInfo().get("count"));
        return "custAdd";
        
    }
    
    //获取产品标准单价
    @RequestMapping(value = "getProductPrice",method = RequestMethod.GET)
    @ResponseBody
    public Product getProductPrice(@RequestParam("productId") String productId ,ModelMap model){
 
        return customerService.getProductPrice(productId);
        
    }

    
    //保存客户单价信息
    @RequestMapping(value = "setCusProPrice",method = RequestMethod.POST)
    @ResponseBody
    public List<CustomerPriceInfo> setCusProPrice(@RequestParam("customerId") String customerId,@RequestParam("productId") String productId,
            @RequestParam("preferentialMin") String preferentialMin,@RequestParam("preferentialMax") String preferentialMax,
            @RequestParam("preferentialCredit") String preferentialCredit){
        
        return customerService.setCusProPrice(customerId, productId, preferentialMin, preferentialMax, preferentialCredit);
    }
    
    //新增客户信息及客户产品单价信息
    @RequestMapping(value = "CustAdd",method = RequestMethod.POST)
    public String customerAdd(@RequestParam("customerId") String customerId,@RequestParam("customerName") String customerName,
            @RequestParam("customerMail") String customerMail,@RequestParam("customerPhone") String customerPhone,ModelMap model) throws Exception{
         
       customerService.addCustomerAndPrice(customerId, customerMail, customerName, customerPhone);

        return "redirect:/CustInfo/CustQuery";
        
    }
    
    //获取客户信息，并转到客户信息修改页面
    @RequestMapping(value = "CustEdit",method = RequestMethod.GET)
    public String custEditPage(@RequestParam("customerId") String customerId, ModelMap model){
    
        model.addAttribute("customer", customerService.custEditPageInfo(customerId));
        return "custEdit";
    }
    
    //修改客户信息
    @RequestMapping(value = "CustEdit",method = RequestMethod.POST)
    public String custEdit(@RequestParam("customerId") String customerId, @RequestParam("customerName") String customerName, 
            @RequestParam("customerMail") String customerMail, @RequestParam("customerPhone") String customerPhone, ModelMap model) throws Exception{
    
        customerService.custEdit(customerId, customerName, customerMail, customerPhone);
        return "redirect:/CustInfo/CustQuery";
    }
    
    
    //删除客户信息
    @RequestMapping(value = "CustDel",method = RequestMethod.GET)
    public String custDel(@RequestParam("customerId") String customerId, ModelMap model) throws Exception{
    
        customerService.custDel(customerId);
        return "redirect:/CustInfo/CustQuery";
    }
    
    
}
