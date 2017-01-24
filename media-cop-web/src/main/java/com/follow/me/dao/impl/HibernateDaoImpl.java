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
public class HibernateDaoImpl extends BaseDao implements HibernateDao {



    @Override
    public boolean saveHashTag(HashTagDO hashTagDO) {
        Transaction tx = getSession().beginTransaction();
        getSession().saveOrUpdate(hashTagDO);
        getSession().flush();
        tx.commit();
        closeSession();
        return true ;
    }

    @Override
    public boolean saveCountryHashNames(CountryHashDO countryHashDO){
        Transaction tx = getSession().beginTransaction();
        getSession().saveOrUpdate(countryHashDO);
        getSession().flush();
        tx.commit();
        closeSession();
        return true ;
    }


    @Override
    public HashTagDO getHashTagByNameAndCountry(String name, String country) {
        try {
            List<HashTagDO> list = getSession().createQuery("from HashTagDO where name=:name " +
                    "and country=:country").setParameter("name",name).setParameter("country",country).list() ;
            if(list.size()>0)
                return list.get(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
           closeSession();
        }
        return null ;
    }

    @Override
    public CountryHashDO getTopHashtags(String country) {
        try {
            List<CountryHashDO> list = getSession().createQuery("from CountryHashDO where country=:country").setParameter(
                    "country",country).list();
            if(list.size()>0)
                return list.get(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
          //  closeSession();
        }
        return null ;
    }

    @Override
    public Map<String, List<HashTagDO>> getHashtagsByCountry() {
        List<String> distinctCountries = getDistinctCountries() ;
        Map<String, List<HashTagDO>> map = new HashMap<>();
        for(String country : distinctCountries){
            try {
                List<HashTagDO> countryRecord = getSession().createQuery("from HashTagDO where country=:country order by priority desc").setParameter("country",country).list();
                map.put(country,countryRecord);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
              //  closeSession();
            }
        }
        return map ;
    }

    @Override
    public List<String> getHashNamesByRankInCountry(String country) {
        try {
            List<HashTagDO> hashTagDOList = getSession().createQuery("from HashTagDO " +
                    "where country=:country order by rank").setMaxResults(100).setParameter("country",country).list();
            List<String> hashNames = new ArrayList<>();
            for(HashTagDO hashTagDO : hashTagDOList){
                hashNames.add(hashTagDO.getName());
            }
            return hashNames ;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeSession();
        }
        return new ArrayList<>();
    }

    public List<String> getDistinctCountries(){
        try {
            Criteria criteria = getSession().createCriteria(HashTagDO.class);
            criteria.setProjection(Projections.distinct(Projections.distinct(Projections.property("country"))));
            return criteria.list();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            closeSession();
        }
        return new ArrayList<>();
    }

}
