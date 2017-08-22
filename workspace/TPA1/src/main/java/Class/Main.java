package Class;

import javax.persistence.EntityManager;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import excepciones.CSVInexistenteException;
import repositorios.DBRelacionalRepository;
import ui.windows.MenuWindow;
import usuario.Indicador;
import utilities.JPAUtility;

@Observable
public class Main extends Application{

	
	public static void main(String[] args){
		testeoAManoBD();
		new Main().start();
	}
		@Override
	protected Window<?> createMainWindow() {

			try {
				return new MenuWindow(this);
			} catch (CSVInexistenteException ex) {
				ex.printStackTrace();
			}
			return null;

	}
		
	public static void testeoAManoBD(){
		//Instanciacion del repo que contiene todas las consultas a BD , el JPA que maneja el factory del entity
		JPAUtility jpa=JPAUtility.getInstance();
		EntityManager entityManager = jpa.getEntityManager();
		DBRelacionalRepository<Indicador> repo=new DBRelacionalRepository<>(Indicador.class,entityManager);
		
		
		//Consulta por un id de cualquier tabla sea
		Indicador indicador=repo.findeById(1);
		System.out.println(indicador.toString());
		
		//hace un insert de cualquier tabla sea
		entityManager.getTransaction().begin();
		
		repo.agregar(new Indicador("otrooo","otrooo"));
		
		entityManager.getTransaction().commit();
		
		
		//para modificar
		entityManager.getTransaction().begin();
		indicador.setFormula("Krishna");
		indicador.setNombre("Allahabad");
		entityManager.getTransaction().commit();
		
		
	}
		
}
