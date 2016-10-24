package com.follow.me.controller;


import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.HashTag;
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
        HashTag hashTag = new HashTag();
        hashTag.setName(request.getParameter("name"));
        hashTag.setRank(Integer.parseInt(request.getParameter("rank")));
        hashTag.setPriority(Float.parseFloat(request.getParameter("priority")));
        hashTag.setCountry(request.getParameter("country"));
        hibernateDao.saveHashTag(hashTag);
        LOG.info("\nHashtag added : "+hashTag);
        model.addAttribute("message","hashTag added to table");
        return "default";
    }




}

