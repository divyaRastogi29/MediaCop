package com.follow.me.dao;

import com.follow.me.entity.CountryHashDO;
import com.follow.me.entity.HashTagDO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by divya on 21/10/16.
 */
public interface HibernateDao {

    public SessionFactory getSessionFactory();

    public boolean saveHashTag(HashTagDO hashTagDO) ;

    public boolean saveCountryHashNames(CountryHashDO countryHashDO);

    public HashTagDO getHashTagByNameAndCountry(String name, String country);

    public CountryHashDO getTopHashtags(String country);

    public Map<String,List<HashTagDO>> getHashtagsByCountry();

    public List<String> getHashNamesByRankInCountry(String country);

}
