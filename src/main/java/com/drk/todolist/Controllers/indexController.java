package com.drk.todolist.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class indexController {
    
    @RequestMapping("/")
    public String index(HttpSession session){
        if (session.getAttribute("user")!=null)
            return "redirect:todo/";
        else
            return "redirect:auth/";
    }
}