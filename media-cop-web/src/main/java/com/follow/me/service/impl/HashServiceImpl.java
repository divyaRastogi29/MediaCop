package com.follow.me.service.impl;

import com.follow.me.Request.HashTag;
import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.CountryHashDO;
import com.follow.me.entity.HashTagDO;
import com.follow.me.service.HashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by divya on 24/10/16.
 */
@Service
public class HashServiceImpl implements HashService{

    private static final Logger LOG = LoggerFactory.getLogger(HashServiceImpl.class);

    @Autowired
    HibernateDao hibernateDao ;

    @Override
    public boolean updateHashTag(List<HashTag> hashTagList) {
        for(HashTag hashTag : hashTagList){
            saveOrUpdateHashTag(hashTag);
        }
        return true ;
    }

    @Override
    public List<String> getHashTagByCountry(String name) {
        CountryHashDO countryHashDO = hibernateDao.getTopHashtags(name);
        if(countryHashDO==null)
            return new ArrayList<>();
        return countryHashDO.getHashtags();
    }

    public boolean saveOrUpdateHashTag(HashTag hashTag){
        HashTagDO hashTagDO = hibernateDao.getHashTagByNameAndCountry(hashTag.getName(),hashTag.getCountry()) ;
        if(hashTagDO == null) {
            hashTagDO = new HashTagDO(hashTag.getName().toLowerCase(), -1, hashTag.getPriority(), hashTag.getCountry().toLowerCase(),
                    System.currentTimeMillis(), System.currentTimeMillis());
            hibernateDao.saveHashTag(hashTagDO);
            return true;
        }
        else{
            float previousPriority = hashTagDO.getPriority();
            long previousCount = hashTagDO.getCount();
            float newPriority = hashTag.getPriority();
            float toUpdatePriority = ((previousPriority*previousCount)+newPriority)/(previousCount+1);

            hashTagDO.setLastUpdated(System.currentTimeMillis());
            hashTagDO.setCount(previousCount+1);
            hashTagDO.setPriority(toUpdatePriority);

            hibernateDao.saveHashTag(hashTagDO);
            return true;
        }
    }
}
