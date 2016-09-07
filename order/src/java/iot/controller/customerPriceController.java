/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.CustomerPrice;
import iot.dao.repository.CustomerPriceDAO;
import iot.service.CustomerPriceService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/CustPrice")
@SessionAttributes({"queryCondition"})
public class customerPriceController {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private CustomerPriceService cps;

    //进入客户产品页面执行的默认查询
    @RequestMapping(value = "queryCustPrice", method = RequestMethod.GET)
    public String custPriceQuery(ModelMap model) {

        //将查询条件保存到一个Map中 
        HashMap<String,String> queryCondition = new HashMap<String, String>();
        queryCondition.put("CustomerName", "");
        queryCondition.put("ProductName", "");
        queryCondition.put("PriceMin", "");
        queryCondition.put("PriceMax", "");
        queryCondition.put("RangesMin", "");
        queryCondition.put("RangesMax", "");
        
        Map map = cps.queryCustomerPrice("", "", "", "", "", "", 0);
        
        List<CustomerPrice> cpList = (List<CustomerPrice>)map.get("customerPricesList");
        int count = (int) ((Long)map.get("count")/10 + 1);
        
        //将查询条件保存到Session当中
        model.addAttribute("queryCondition",queryCondition); 
        
        //将当前页、总页及查询数据数传到视图中
        model.addAttribute("pageNo","1");
        model.addAttribute("custPriceList", cpList);
        model.addAttribute("totalPage",count);
        
        //返回视图
        return "custPriceInfo";
    }

    //客户产品单价条件查询
    @RequestMapping(value = "CustQuery", method = RequestMethod.POST)
    public String queryCustomerPrice(@RequestParam("customerName") String CustomerName, @RequestParam("productName") String ProductName, @RequestParam("priceMin") String PriceMin,
            @RequestParam("priceMax") String PriceMax, @RequestParam("rangesMin") String RangesMin, @RequestParam("rangesMax") String RangesMax, ModelMap model) {

        //将查询条件保存到一个Map中 
        HashMap<String,String> queryCondition = new HashMap<String, String>();
        queryCondition.put("CustomerName", CustomerName);
        queryCondition.put("ProductName", ProductName);
        queryCondition.put("PriceMin", PriceMin);
        queryCondition.put("PriceMax", PriceMax);
        queryCondition.put("RangesMin", RangesMin);
        queryCondition.put("RangesMax", RangesMax);
        
        Map map = cps.queryCustomerPrice(CustomerName, ProductName, PriceMin, PriceMax, RangesMin, RangesMax,0);
        
        List<CustomerPrice> cpList = (List<CustomerPrice>)map.get("customerPricesList");
        int count = (int) ((Long)map.get("count")/10 + 1);
       
        //将查询条件、当前页、总页及查询数据数传到视图中
        model.addAttribute("queryCondition",queryCondition); 
        model.addAttribute("pageNo","1");
        model.addAttribute("custPriceList", cpList);
        model.addAttribute("totalPage",count);
        
        //返回视图
        return "custPriceInfo";

    }

    //查询下一页
    @RequestMapping(value = "queryNext", method = RequestMethod.GET)
    @ResponseBody            //将数据转换为合适的格式（Json）
    public List nextPage(@ModelAttribute("queryCondition") HashMap<String,String> queryCondition,@RequestParam("pageNo") int pageNo, ModelMap model) {

         List<CustomerPrice> cpList = (List<CustomerPrice>)cps.queryCustomerPrice(queryCondition.get("CustomerName"), queryCondition.get("ProductName"),queryCondition.get("PriceMin") , 
                                      queryCondition.get("PriceMax"), queryCondition.get("RangesMin"), queryCondition.get("RangesMax"),pageNo).get("customerPricesList");
        
         List list_table = new ArrayList();
         //将查询结果保存在List中
         for(int i = 0;i < cpList.size();i++){
         
             List list_row = new ArrayList();
             list_row.add(cpList.get(i).getCustomerPriceId());
             list_row.add(cpList.get(i).getCustomerMasterId().getCustomerId());
             list_row.add(cpList.get(i).getCustomerMasterId().getCustomerName());
             list_row.add(cpList.get(i).getProductMasterId().getProductId());
             list_row.add(cpList.get(i).getProductMasterId().getProductName());
             list_row.add(cpList.get(i).getRanges());
             list_row.add(cpList.get(i).getRangePrice());
             list_table.add(list_row);
         }
         
        //将数据传回前台
        return list_table;
    }

    //查询上一页
    @RequestMapping(value = "queryPre", method = RequestMethod.GET)
    @ResponseBody            //将数据转换为合适的格式（Json）
    public List prePage(@ModelAttribute("queryCondition") HashMap<String,String> queryCondition,@RequestParam("pageNo") int pageNo,ModelMap model) {

        List<CustomerPrice> cpList = (List<CustomerPrice>)cps.queryCustomerPrice(queryCondition.get("CustomerName"), queryCondition.get("ProductName"),queryCondition.get("PriceMin") , 
                                      queryCondition.get("PriceMax"), queryCondition.get("RangesMin"), queryCondition.get("RangesMax"),pageNo - 2).get("customerPricesList");
        
         List list_table = new ArrayList();
         //将查询结果保存在List中
         for(int i = 0;i < cpList.size();i++){
         
             List list_row = new ArrayList();
             list_row.add(cpList.get(i).getCustomerPriceId());
             list_row.add(cpList.get(i).getCustomerMasterId().getCustomerId());
             list_row.add(cpList.get(i).getCustomerMasterId().getCustomerName());
             list_row.add(cpList.get(i).getProductMasterId().getProductId());
             list_row.add(cpList.get(i).getProductMasterId().getProductName());
             list_row.add(cpList.get(i).getRanges());
             list_row.add(cpList.get(i).getRangePrice());
             list_table.add(list_row);
         }
         
        //将数据传回前台
        return list_table;
    }
    
    //跳转修改客户产品单价信息
    @RequestMapping(value = "CustPriceEdit", method = RequestMethod.GET)
    public String editCustPricePage(@RequestParam("customerPriceId") int CustomerPriceId, ModelMap model) {

        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        //通过客户产品单价表主键找到需要修改的实体
        CustomerPrice cp = cpdao.findCustomerPrice(CustomerPriceId);
       //设置修改时的数量级区间时最大值的确认
        int rangeMax;
        if (cp.getRanges() == 1) {
            rangeMax = cp.getRanges() + 999;
        } else if (cp.getRanges() == 4000) {
            rangeMax = Integer.MAX_VALUE;//暂时表示为最大值
        } else {
            rangeMax = cp.getRanges() * 2;
        }
        //把最大值和实体传回给视图（视图解析器会解析）
        model.addAttribute("rangeMax", rangeMax);
        model.addAttribute("CustomerPriceId", cpdao.findCustomerPrice(CustomerPriceId));
       
        return "custPriceEdit";
    }

    //修改客户产品单价信息
    @RequestMapping(value = "CustPriceEdit", method = RequestMethod.POST)
    public String editCustPrice(@RequestParam("customerPriceId") int CustomerPriceId, @RequestParam("editPrice") String EditPrice, ModelMap model) throws Exception {

        CustomerPriceDAO cpdao = new CustomerPriceDAO(emf);
        CustomerPrice cp = cpdao.findCustomerPrice(CustomerPriceId);//通过CustomerPriceId获取到当前的产品单价
        cp.setRangePrice(Float.parseFloat(EditPrice));//把前台获取到的EditPrice保存到某个位置
        cpdao.edit(cp);
        return "custPriceEdit";
    }

}
