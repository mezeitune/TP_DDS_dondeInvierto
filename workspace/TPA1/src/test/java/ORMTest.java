import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import repositorios.DBRelacionalRepository;
import repositorios.IndicadoresRepository;
import repositorios.MetodologiasRepository;
import usuario.Indicador;
import usuario.Metodologia;
import utilities.JPAUtility;

public class ORMTest {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "db" );
		
		
		
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void Cargar2IndicadoresEnLaBDDYQueSeTraiganExactamente2() throws Exception {
		JPAUtility jpa=JPAUtility.getInstance();
		EntityManager entityManager = jpa.getEntityManager();
		DBRelacionalRepository<EntityManager> repo=new DBRelacionalRepository<EntityManager>(entityManager);
		
		Metodologia met1 = new Metodologia();
		Metodologia met2 = new Metodologia();
		met1.setNombre("met");
		met2.setNombre("met");
		
		
		entityManager.getTransaction().begin();
	
		repo.agregar(met1);
		repo.agregar(met2);
		
		entityManager.getTransaction().commit();
		String value = "met";
		Query query = entityManager.createQuery("from Metodologia where nombre = :value");
		query.setParameter("value",value);
		List<Indicador> listaConIndicadoresTraidosDeLaBDD= query.getResultList();
		
		assertEquals(listaConIndicadoresTraidosDeLaBDD.size(), 2);
		entityManager.getTransaction().begin();
		repo.eliminar(met1);
		repo.eliminar(met2);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
