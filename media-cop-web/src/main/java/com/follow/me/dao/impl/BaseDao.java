package com.follow.me.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by divya on 1/22/17.
 */
public class BaseDao {

    @Autowired
    public SessionFactory sessionFactory ;

    private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();;

    public SessionFactory getSessionFactory() {
        return sessionFactory ;
    }

    public Session getSession() {
        Session session = threadLocal.get();
        if(session == null){
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public void closeSession() {
        Session session = threadLocal.get();
        if(session != null){
            session.close();
            threadLocal.set(null);
        }
    }
}
