package com.follow.me.service.impl;

import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.CountryHashDO;
import com.follow.me.entity.HashTagDO;
import com.follow.me.service.ScheduleCountryHashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by divya on 3/11/16.
 */

@Service
@EnableScheduling
public class ScheduleCountryHashServiceImpl implements ScheduleCountryHashService {

    Logger LOG = LoggerFactory.getLogger(ScheduleCountryHashServiceImpl.class);

    @Autowired
    HibernateDao hibernateDao ;

    @Scheduled(cron="0 0 0,6,12,18 * * *")
    public void updateRankRecords(){
        LOG.info("\n\n\n\n\nScheduling started at : "+new Date());
        LOG.info("\nUpdate Rank Records called .");
        Map<String,List<HashTagDO>>  tagMap = hibernateDao.getHashtagsByCountry() ;

        for(String country : tagMap.keySet()){
            rankRecords(country , tagMap.get(country));
            updateCountryHash(country , hibernateDao.getHashNamesByRankInCountry(country));
        }
        LOG.info("\nScheduling completed at time : "+new Date());
    }

    private void updateCountryHash(String country ,List<String> hashNamesInCountry) {
        LOG.info("\nUpdate country hash called for country : "+country);
        CountryHashDO countryHash = new CountryHashDO();
        countryHash.setCountry(country);
        countryHash.setHashtags(hashNamesInCountry);
        hibernateDao.saveCountryHashNames(countryHash);
        LOG.info("\nUpdated country hash for country : "+country);
    }

    public void rankRecords(String country , List<HashTagDO> hashTagDOList){
        LOG.info("\nRank records called for country : "+country);
        int currRank = -1;
        float prevPriority = 0;
        for(HashTagDO hashTagDO : hashTagDOList){
            LOG.info("\nSaving Hashtag in rank records");
            if(currRank==-1){
                currRank = 1;
                hashTagDO.setRank(currRank);
                hibernateDao.saveHashTag(hashTagDO);
                prevPriority = hashTagDO.getPriority();
            }
            else{
                if(hashTagDO.getPriority()==prevPriority){
                    hashTagDO.setRank(currRank);
                    hibernateDao.saveHashTag(hashTagDO);
                }
                else{
                    currRank += 1;
                    prevPriority = hashTagDO.getPriority();
                    hashTagDO.setRank(currRank);
                    hibernateDao.saveHashTag(hashTagDO);
                }
            }
            LOG.info("\nSaved hashtag in rank records");
        }
    }

}
