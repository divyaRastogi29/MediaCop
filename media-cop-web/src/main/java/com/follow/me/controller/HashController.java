package com.follow.me.controller;

import com.follow.me.Request.AddHashtagRequest;
import com.follow.me.Request.HashTag;
import com.follow.me.model.AddHashTagResponse;
import com.follow.me.service.HashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by divya on 24/10/16.
 */
@Controller
@RequestMapping({"/api/service/hashfollowing"})
public class HashController {

    private static final Logger LOG = LoggerFactory.getLogger(HashController.class);

    @Autowired
    HashService hashService ;

    @RequestMapping(value = "/updateHashTag", method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public AddHashTagResponse updateHashTag(@RequestBody AddHashtagRequest request){
      LOG.info("\nRecieved Request for updating Hashtag : "+request);
        AddHashTagResponse response = new AddHashTagResponse() ;
        List<HashTag> hashTagList = request.getHashTagList();
        boolean success = hashService.updateHashTag(hashTagList);
        if(success)
        response.setMessage("\nSuccessfully updated");
        else
        response.setMessage("\nError while proceesing event");
        return response ;
    }

}
