package com.gestaooticas.jpa.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Jovino
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = configuracao();

    protected static SessionFactory configuracao() {
        final StandardServiceRegistry registraSessao = new StandardServiceRegistryBuilder()
                .configure()//hibernate.cfg.xml
                .build();//Constrói a sessao

        try {
            //Cria a fábrica de sessao
            SessionFactory fabricaSessao = new MetadataSources(registraSessao).buildMetadata().buildSessionFactory();
            return fabricaSessao;
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registraSessao);
            return null;
        }
        
    }
    
    public static SessionFactory getSessionfactory() {
		return sessionFactory;
    }
    
    
    public static void main(String[] args){
        HibernateUtil.getSessionfactory();
    }
}
