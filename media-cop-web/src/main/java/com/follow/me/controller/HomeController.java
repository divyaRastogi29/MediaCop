package com.follow.me.controller;


import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.HashTagDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by divya on 20/10/16.
 */
@Controller
@RequestMapping({"/","/homepage"})
public class HomeController {

    @Autowired
    HibernateDao hibernateDao ;

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String test() throws IOException {
        return  "home";
    }

    @RequestMapping(value = "/register" , method = RequestMethod.GET)
    public String showRegistrationForm(){
        return "register" ;
    }

    @RequestMapping(value = "/addHashtag",method = RequestMethod.POST)
    public String addHashTag(HttpServletRequest request , Model model){

        LOG.info("\nRequest comes for adding Spitter");
        HashTagDO hashTagDO = new HashTagDO();
        hashTagDO.setName(request.getParameter("name"));
        hashTagDO.setRank(Integer.parseInt(request.getParameter("rank")));
        hashTagDO.setPriority(Float.parseFloat(request.getParameter("priority")));
        hashTagDO.setCountry(request.getParameter("country"));

        hibernateDao.saveHashTag(hashTagDO);
        LOG.info("\nHashtag added : "+ hashTagDO);
        model.addAttribute("message","hashTagDO added to table");
        return "default";
    }




}

