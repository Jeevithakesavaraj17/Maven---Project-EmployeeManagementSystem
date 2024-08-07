package com.ideas2it.ems.connectionManager;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.cfg.Configuration;
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
    private static final Logger logger = LogManager.getLogger();

    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            logger.error("Unable to create session Factory.{}", e);
        }
    }

    /**
     * <p>
     * This method is for getting session factory object
     * </p>
     */
    public static SessionFactory getFactory() {
        return factory;
    }

}