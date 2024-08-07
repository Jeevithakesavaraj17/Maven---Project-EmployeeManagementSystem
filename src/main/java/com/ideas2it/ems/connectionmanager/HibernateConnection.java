package com.ideas2it.ems.connectionmanager;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/**
 * <p>
 * This class is used for creating the connection using hibernate
 * </p>
 *
 * @author JeevithaKesavaraj
 */
public class HibernateConnection {
    private static SessionFactory factory = null;

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("Unable to create session Factory." + e);
        }
    }

    /**
     * <p>
     * This method is for getting session fatory object
     * </p>
     */
    public static SessionFactory getFactory() {
        return factory;
    }

}