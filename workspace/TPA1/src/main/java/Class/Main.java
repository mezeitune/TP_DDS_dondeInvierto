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
		EntityManager entityManager = JPAUtility.getInstance().getEntityManager();
		DBRelacionalRepository<Indicador> repo=new DBRelacionalRepository<>(Indicador.class,entityManager);
		
		Indicador indicador=repo.findeById(1);
		System.out.println(indicador.toString());
		
	}
		
}
