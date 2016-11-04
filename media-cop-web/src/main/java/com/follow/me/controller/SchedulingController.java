package com.follow.me.controller;

import com.follow.me.model.ScheduleRunUtilityResponse;
import com.follow.me.service.ScheduleCountryHashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by divya on 3/11/16.
 */
@Controller
@RequestMapping("/api/service/schedule")
public class SchedulingController {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulingController.class);

    @Autowired
    ScheduleCountryHashService scheduleCountryHashService ;

    @RequestMapping(value = "/runUtility", method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public ScheduleRunUtilityResponse runUtility(){
        ScheduleRunUtilityResponse response = new ScheduleRunUtilityResponse();
        LOG.info("\nRequest to run utility comes");

        scheduleCountryHashService.updateRankRecords();
        response.setMessage("\nUtility run over");
        LOG.info("\nArrives at response after running utility");
        return response;
    }
}
