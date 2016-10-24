package com.follow.me.dao;

import org.hibernate.SessionFactory;

/**
 * Created by divya on 21/10/16.
 */
public interface HibernateDao {

    public SessionFactory getSessionFactory();

    public void saveHashTag(com.follow.me.entity.HashTag hashTag) ;
}
