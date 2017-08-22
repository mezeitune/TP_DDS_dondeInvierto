package utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import indicadoresPredefinidos.PatrimonioNeto;
public class JPAUtility {
	private static JPAUtility instance ;
 	private EntityManagerFactory emFactory;
 	
	public static JPAUtility getInstance( ){
        if(instance == null){
            instance = new JPAUtility();
        }
        return instance;

	}	
	
	

	public JPAUtility() {
		   emFactory = Persistence.createEntityManagerFactory("db");
	}
	
	public EntityManager getEntityManager(){
		return emFactory.createEntityManager();
	}
	public void close(){
		emFactory.close();
	}
	
	
} 