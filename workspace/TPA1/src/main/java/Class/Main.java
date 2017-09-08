package Class;


import javax.persistence.EntityManager;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import excepciones.CSVInexistenteException;
import repositorios.DBRelacionalRepository;
import ui.windows.MenuWindow;
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
		DBRelacionalRepository<EntityManager> repo=new DBRelacionalRepository<EntityManager>(entityManager);
		
		repo.agregarDatosALaBDDDeLosArchivos();


	}

}
