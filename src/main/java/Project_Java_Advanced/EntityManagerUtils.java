package Project_Java_Advanced;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtils {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManagerFactory getEntityManagerFactory(){
        if(entityManagerFactory == null){
            entityManagerFactory= Persistence.createEntityManagerFactory("InternetShopPersistence");
        }

        return entityManagerFactory;
    }

    public static EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }

}
