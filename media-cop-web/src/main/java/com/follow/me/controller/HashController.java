package com.follow.me.controller;

import com.follow.me.Request.AddHashtagRequest;
import com.follow.me.Request.GetTopHashTagRequest;
import com.follow.me.Request.HashTag;
import com.follow.me.model.AddHashTagResponse;
import com.follow.me.model.GetTopHashTagResponse;
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
        try {
            boolean isSuccessful =  hashService.updateHashTag(hashTagList);
            response.setSuccessful(isSuccessful);
            response.setMessage("\nSuccessfully updated");
        }
        catch (Throwable e){
            response.setSuccessful(false);
            response.setMessage("\nError while proceesing event");
        }
        return response ;
    }


    @RequestMapping(value = "/getTopHashtags" , method = RequestMethod.POST , produces = "application/json")
    @ResponseBody
    public GetTopHashTagResponse getTopHashTags(@RequestBody GetTopHashTagRequest request){
        LOG.info("\nRecieved Request for getting top Hashtags : "+request);
        GetTopHashTagResponse response = new GetTopHashTagResponse();
        String country = request.getCountry() ;
        try {
            List<String> hashTags = hashService.getHashTagByCountry(country) ;
            response.setHashtags(hashTags);
            response.setMessage("\nResponse successfully processed");
            response.setSuccessful(true);
            LOG.info("\nResponse is : "+response);
        }
        catch (Throwable e){
            response.setMessage("\nError processing the event !!");
            response.setSuccessful(false);
        }
        return response ;
    }

}
