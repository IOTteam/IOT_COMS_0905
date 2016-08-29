/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerPrice;
import iot.dao.repository.CustomerPriceDAO;
import iot.service.CustomerPriceService;
import javax.persistence.EntityManagerFactory;
import org.eclipse.persistence.mappings.transformers.ConstantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    @Autowired
    private CustomerPriceService cps;
    
    //查询客户单价表
    @RequestMapping(value = "queryCustPrice",method = RequestMethod.GET)
    public String custPriceQuery(ModelMap model){
        
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        
               model.addAttribute("index", i/10 + 1);
        model.addAttribute("custPriceList", cpdao.findCustomerPriceEntities(10,0));
        return "custPriceInfo";
    }
    
    @RequestMapping(value ="CustQuery",method =RequestMethod.POST)
    public String queryCustomerPrice(@RequestParam("customerName") String CustomerName,@RequestParam("productName") String ProductName,@RequestParam("priceMin") String PriceMin,
            @RequestParam("priceMax") String PriceMax,@RequestParam("rangesMin") String RangesMin,@RequestParam("rangesMax") String RangesMax ,ModelMap modelMap){
        modelMap.addAttribute("custPriceList", cps.queryCustomerPrice(CustomerName, ProductName, PriceMin, PriceMax, RangesMin, RangesMax));
        return "custPriceInfo";
    
    }
    
    
    
    //跳转修改客户产品单价信息
    @RequestMapping(value = "CustPriceEdit",method = RequestMethod.GET)
    public String editCustPricePage(@RequestParam("customerPriceId") int CustomerPriceId,ModelMap model){
        
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        model.addAttribute("CustomerPriceId", cpdao.findCustomerPrice(CustomerPriceId));
        CustomerPrice cp=new CustomerPrice();
        cp=cpdao.findCustomerPrice(CustomerPriceId);
        int rangeMax;
        if(cp.getRanges()==1){
         rangeMax=cp.getRanges()+999;
        }
        else{
        rangeMax=cp.getRanges()*2;
        }
        model.addAttribute("rangeMax", rangeMax);
        return "custPriceEdit";
    }
    
    @RequestMapping(value = "CustPriceEdit",method = RequestMethod.POST)
    public String editCustPrice(@RequestParam("customerPriceId") int CustomerPriceId,@RequestParam("editPrice") String EditPrice,ModelMap model){
        
        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        model.addAttribute("CustomerPriceId", cpdao.findCustomerPrice(CustomerPriceId));
        return "custPriceEdit";
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
