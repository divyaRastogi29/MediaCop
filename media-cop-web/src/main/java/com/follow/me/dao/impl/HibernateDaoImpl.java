package com.follow.me.dao.impl;

import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.HashTagDO;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by divya on 21/10/16.
 */
@Repository
@Transactional
public class HibernateDaoImpl implements HibernateDao {

    @Autowired
    public SessionFactory sessionFactory ;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory ;
    }

    @Override
    public boolean saveHashTag(HashTagDO hashTagDO) {
        Transaction tx = getSessionFactory().openSession().beginTransaction();
        getSessionFactory().getCurrentSession().saveOrUpdate(hashTagDO);
        getSessionFactory().getCurrentSession().flush();
        tx.commit();
        return true ;
    }


    @Override
    public HashTagDO getHashTagByNameAndCountry(String name, String country) {
        List<HashTagDO> list = getSessionFactory().openSession().createQuery("from HashTagDO where name=:name " +
                "and country=:country").setParameter("name",name).setParameter("country",country).list() ;
        if(list.size()>0)
            return list.get(0);
        return null ;
    }

}
