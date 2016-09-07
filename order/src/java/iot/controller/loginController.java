/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iot.controller;

import com.google.code.kaptcha.Constants;
import iot.dao.entity.User;
import com.google.code.kaptcha.Producer;
import iot.dao.repository.UserDaoImpl;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author hatanococoro
 */
@Controller
@RequestMapping("/login")
@SessionAttributes({"user"})
public class loginController {
    
    @Autowired  
    private Producer captchaProducer = null;  
    
    //进入登录页面
    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(ModelMap model){
        
        return "login";
        
    }
    
    //用户登录
    @RequestMapping(method = RequestMethod.POST)
    public String loginAction(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("kaptcha") String kaptcha,
            HttpServletRequest request,ModelMap model){
        
        HttpSession session = request.getSession();  
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY); 
        
//        if (!code.equals(kaptcha)) {
//            
//            model.addAttribute("message_k", "验证码错误");
//            return "login";
//        }
        
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
    
    @RequestMapping(value = "captcha-image")  
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        HttpSession session = request.getSession();  
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);  
        
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");  
        
        String capText = captchaProducer.createText();  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);  
        
        BufferedImage bi = captchaProducer.createImage(capText);  
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
        return null;  
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
