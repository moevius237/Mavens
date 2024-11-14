package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory factory ;
    private HibernateUtil(){
    }
    public static SessionFactory getSession(){
        if (factory == null){
            factory= buildSesionFactory();
        }
        return factory;
    }
    private static SessionFactory buildSesionFactory(){
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().configure("META-INF/hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}
