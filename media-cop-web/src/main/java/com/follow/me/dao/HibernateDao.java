package com.follow.me.dao;

import com.follow.me.entity.HashTagDO;
import org.hibernate.SessionFactory;

/**
 * Created by divya on 21/10/16.
 */
public interface HibernateDao {

    public SessionFactory getSessionFactory();

    public boolean saveHashTag(HashTagDO hashTagDO) ;

    public HashTagDO getHashTagByNameAndCountry(String name, String country);
}
