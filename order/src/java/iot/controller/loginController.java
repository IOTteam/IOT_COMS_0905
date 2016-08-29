/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import iot.dao.entity.User;
import iot.dao.repository.UserDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/login")
@SessionAttributes({"user"})
public class loginController {
    
    //进入登录页面
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(ModelMap model){
        
        return "login";
        
    }
    
    //用户登录
    @RequestMapping(method = RequestMethod.POST)
    public String loginAction(@RequestParam("username") String username,@RequestParam("password") String password,ModelMap model){
        
        UserDaoImpl udi = new UserDaoImpl();
        User user = udi.getUserByNameAndPassword(username,password);
        
        if (user == null){
            model.addAttribute("message", "用户名或密码错误");
            return "login";
        }else{
            model.addAttribute("user", user);
            return "redirect:/index";
        }
    }
    
    //查询用户信息
    @RequestMapping(value = "userInfo",method = RequestMethod.GET)
    public String getUserInfo(@ModelAttribute("user") User user, ModelMap model){
        
        model.addAttribute("user", user);
        return "userInfo";
        
    }
    
    //跳转修改密码页面
    @RequestMapping(value = "editPassword",method = RequestMethod.GET)
    public String editPasswordPage(ModelMap model){
        
        return "editPassword";
        
    }
    
    //修改密码
    @RequestMapping(value = "editPassword",method = RequestMethod.POST)
    public String editPassword(@ModelAttribute("user") User user,@RequestParam("passwordOld") String passwordOld,@RequestParam("passwordNew") String passwordNew,
            @RequestParam("passwordConfirm") String passwordConfirm,ModelMap model){

        if(passwordOld.equals(user.getPassword())){
        
            if(passwordNew.equals(passwordConfirm)){
            
                user.setPassword(passwordNew);
                UserDaoImpl udi = new UserDaoImpl();
                udi.editUser(user);
                
            }else{
                
            model.addAttribute("message", "两次输入的密码不一致");
            return "editPassword";
                
            }
            
        }else{
        
            model.addAttribute("message", "原密码输入有误");
             return "editPassword";
        }
        return "redirect:/index";
    }
    
    
}
