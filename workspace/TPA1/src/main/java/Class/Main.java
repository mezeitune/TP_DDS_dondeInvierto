package Class;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import excepciones.CSVInexistenteException;
import repositorios.DBRelacionalRepository;
import ui.windows.MenuWindow;
import usuario.Cuenta;
import usuario.Empresa;
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
		DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);
		
		
		//Consulta por un id de cualquier tabla sea
		Indicador indicador=(Indicador) repo.findById(Indicador.class,1);
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
		
		List<Indicador> indicadores=repo.filtrarPorCampoEspecifico(Indicador.class,"Indicador", "formula", "otrooo");
		System.out.println(indicadores.toString());
		
		
		
		Cuenta cuenta=(Cuenta) repo.findById(Cuenta.class,1);
		System.out.println(cuenta.toString());
		
		
		//Empresa empresa=(Empresa) repo.findById(Empresa.class,1);
		//System.out.println(empresa.getCuentas().get(0).toString());*/
		
	}
		
}
