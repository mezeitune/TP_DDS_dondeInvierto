package utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		   
		
		emFactory = Persistence.createEntityManagerFactory("db");//Factory para obtener entity de nuestra db
		   
		   
	}
	
	public EntityManager getEntityManager(){//singleton para el entity
		return emFactory.createEntityManager();
	}
	public void close(){//usar siempre que terminemos con las transactions
		
		
		emFactory.close();

	
	}
	
	
} 