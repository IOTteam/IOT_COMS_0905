/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.repository.CustomerPriceDAO;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/CustPrice")
public class customerPriceController {
    
    private int i = 0;
    
    @Autowired
    private EntityManagerFactory emf;
    
    //查询客户单价表
    @RequestMapping(value = "queryCustPrice",method = RequestMethod.GET)
    public String custPriceQuery(ModelMap model){
        
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        
               model.addAttribute("index", i/10 + 1);
        model.addAttribute("custPriceList", cpdao.findCustomerPriceEntities(10,0));
        return "custPriceInfo";
    }
    
    //修改客户产品单价信息
    @RequestMapping(value = "editCustPrice",method = RequestMethod.POST)
    public String editCustPricePage(ModelMap model){
        
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        
        return "custPriceInfo";
    }
    
    //查询下一页
    @RequestMapping(value = "queryNext",method = RequestMethod.GET)
    public String nextPage(ModelMap model){
        
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        i = i + 10;
        model.addAttribute("index", i/10 + 1);
        model.addAttribute("custPriceList", cpdao.findCustomerPriceEntities(10,i));
        return "custPriceInfo";
    }
    
    //查询上一页
    @RequestMapping(value = "queryPre",method = RequestMethod.GET)
    public String prePage(ModelMap model){
        
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        
        if(i == 0){
        
            i=0;
        }else{
        
            i = i - 10;
        }
    
        model.addAttribute("index", i/10 + 1);
        model.addAttribute("custPriceList", cpdao.findCustomerPriceEntities(10,i));
        return "custPriceInfo";
    }
    
}
