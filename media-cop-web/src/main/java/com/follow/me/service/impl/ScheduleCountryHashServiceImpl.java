package com.follow.me.service.impl;

import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.CountryHashDO;
import com.follow.me.entity.HashTagDO;
import com.follow.me.service.ScheduleCountryHashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by divya on 3/11/16.
 */

@Service
public class ScheduleCountryHashServiceImpl implements ScheduleCountryHashService {

    Logger LOG = LoggerFactory.getLogger(ScheduleCountryHashServiceImpl.class);

    @Autowired
    HibernateDao hibernateDao ;

    ExecutorService executorService ;

    public void updateRankRecords(){
        LOG.info("\nUpdate Rank Records called .");
        Map<String,List<HashTagDO>>  tagMap = hibernateDao.getHashtagsByCountry() ;

        LOG.info("\nMap recieved after calling dao : "+tagMap);

        LOG.info("\nExecutor service called");

        executorService = Executors.newFixedThreadPool(3);

        for(String country : tagMap.keySet()){
            rankRecords(country , tagMap.get(country));
            updateCountryHash(country , hibernateDao.getHashNamesByRankInCountry(country));
            /*executorService.submit(new ScheduleRankService(country, tagMap.get(country))) ;*/
        }
       /* executorService.shutdown();*/
    }

    private void updateCountryHash(String country ,List<String> hashNamesInCountry) {
        LOG.info("\nUpdate country hash called for country : "+country);
        CountryHashDO countryHash = new CountryHashDO();
        countryHash.setCountry(country);
        countryHash.setHashtags(hashNamesInCountry);
        LOG.info("\nUpdating country hash for country : "+country);
        hibernateDao.saveCountryHashNames(countryHash);
        LOG.info("\nUpdated country hash for country : "+country);
    }

    public void rankRecords(String country , List<HashTagDO> hashTagDOList){
        LOG.info("\nRank records called for country : "+country);
        int currRank = -1;
        float prevPriority = 0;
        for(HashTagDO hashTagDO : hashTagDOList){
            if(currRank==-1){
                currRank = 1;
                hashTagDO.setRank(currRank);
                LOG.info("\nSaving Hashtag in rank records : "+hashTagDO);
                hibernateDao.saveHashTag(hashTagDO);
                LOG.info("\nSaved hashtag in rank records");
                prevPriority = hashTagDO.getPriority();
            }
            else{
                if(hashTagDO.getPriority()==prevPriority){
                    hashTagDO.setRank(currRank);
                    LOG.info("\nSaving Hashtag in rank records : "+hashTagDO);
                    hibernateDao.saveHashTag(hashTagDO);
                    LOG.info("\nSaved hashtag in rank records");
                }
                else{
                    currRank += 1;
                    prevPriority = hashTagDO.getPriority();
                    hashTagDO.setRank(currRank);
                    LOG.info("\nSaving Hashtag in rank records");
                    hibernateDao.saveHashTag(hashTagDO);
                    LOG.info("\nSaved hashtag in rank records");
                }
            }
        }
    }

}
