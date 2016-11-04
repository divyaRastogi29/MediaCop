package com.follow.me.util;

import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.CountryHashDO;
import com.follow.me.entity.HashTagDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by divya on 3/11/16.
 */
public class ScheduleRankService implements Runnable {

    Logger LOG = LoggerFactory.getLogger(ScheduleRankService.class);

    private String country ;
    private List<HashTagDO> hashTagDOList ;

    @Autowired
    HibernateDao hibernateDao ;

    public ScheduleRankService(String country , List<HashTagDO> hashTagDOList){
         this.country = country ;
         this.hashTagDOList = hashTagDOList ;
    }


    @Override
    public void run() {
        LOG.info("\nThread for country : "+country+" starts");
        rankRecords(hashTagDOList);
        updateCountryHash(country , hibernateDao.getHashNamesByRankInCountry(country));
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

    public void rankRecords(List<HashTagDO> hashTagDOList){
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
