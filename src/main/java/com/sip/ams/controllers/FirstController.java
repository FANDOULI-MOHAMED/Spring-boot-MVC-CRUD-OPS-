package com.sip.ams.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
    @GetMapping("/home")
    //  @ResponseBody
    public String home(@RequestParam(required =false,defaultValue ="Spring Boot")String framework, ModelMap monObj){
        monObj.put("myframework", framework);
        return "pages/home";
    }
}
