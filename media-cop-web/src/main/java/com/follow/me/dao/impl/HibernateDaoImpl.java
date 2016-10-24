package com.follow.me.dao.impl;

import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.HashTag;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by divya on 21/10/16.
 */
@Repository
public class HibernateDaoImpl implements HibernateDao {

    @Autowired
    public SessionFactory sessionFactory ;

    @Override
    public SessionFactory getSessionFactory() {
       return sessionFactory ;
    }

    @Override
    public void saveHashTag(HashTag hashTag) {
        getSessionFactory().openSession().save(hashTag) ;
    }
}
