package com.follow.me.dao.impl;

import com.follow.me.dao.HibernateDao;
import com.follow.me.entity.CountryHashDO;
import com.follow.me.entity.HashTagDO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
      //  getSessionFactory().close();
        return true ;
    }

    @Override
    public boolean saveCountryHashNames(CountryHashDO countryHashDO){
        Transaction tx = getSessionFactory().openSession().beginTransaction();
        getSessionFactory().getCurrentSession().saveOrUpdate(countryHashDO);
        getSessionFactory().getCurrentSession().flush();
        tx.commit();
     //   getSessionFactory().close();
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

    @Override
    public CountryHashDO getTopHashtags(String country) {
        List<CountryHashDO> list = getSessionFactory().openSession().createQuery("from CountryHashDO where country=:country").setParameter("country",country).list();
       if(list.size()>0)
           return list.get(0);
        return null ;
    }

    @Override
    public Map<String, List<HashTagDO>> getHashtagsByCountry() {
        List<String> distinctCountries = getDistinctCountries() ;
        Map<String, List<HashTagDO>> map = new HashMap<>();
        for(String country : distinctCountries){
            List<HashTagDO> countryRecord = getSessionFactory().openSession().createQuery("from HashTagDO where country=:country order by priority desc").setParameter("country",country).list();
            map.put(country,countryRecord);
        }
        return map ;
    }

    @Override
    public List<String> getHashNamesByRankInCountry(String country) {
       List<HashTagDO> hashTagDOList = getSessionFactory().openSession().createQuery("from HashTagDO " +
               "where country=:country order by rank").setMaxResults(100).setParameter("country",country).list();
        List<String> hashNames = new ArrayList<>();
        for(HashTagDO hashTagDO : hashTagDOList){
            hashNames.add(hashTagDO.getName());
        }
        return hashNames ;
    }

    public List<String> getDistinctCountries(){
        Criteria criteria = getSessionFactory().openSession().createCriteria(HashTagDO.class);
        criteria.setProjection(Projections.distinct(Projections.distinct(Projections.property("country"))));
        return criteria.list() ;
    }

}
