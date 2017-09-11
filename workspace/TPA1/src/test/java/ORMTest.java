import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import repositorios.DBRelacionalRepository;
import usuario.Indicador;
import utilities.JPAUtility;

public class ORMTest {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "db" );
		JPAUtility jpa=JPAUtility.getInstance();
		EntityManager entityManager = jpa.getEntityManager();
		DBRelacionalRepository<EntityManager> repo=new DBRelacionalRepository<EntityManager>(entityManager);
		
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	
}
