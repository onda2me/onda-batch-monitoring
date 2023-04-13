package com.onda2me.app.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	
    @RequestMapping(value="/")  
    public String context(Model model, Principal principal) {     

        logger.debug("---------------------------------");
        logger.debug("/");
        logger.debug("---------------------------------");
        
        return "/html/index";
    }
    @RequestMapping(value="/index")  
    public String index(Model model, Principal principal) {     

        logger.debug("---------------------------------");
        logger.debug("/index");
        logger.debug("---------------------------------");
        
        return "/html/index";
    }    
}
