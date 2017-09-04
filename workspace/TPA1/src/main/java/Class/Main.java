package Class;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import condiciones.Taxativa;
import condiciones.TipoCondicion;
import excepciones.CSVInexistenteException;
import indicadoresPredefinidos.PatrimonioNeto;
import parserArchivos.ParserCsv;
import parserArchivos.ParserJsonAObjetosJava;
import repositorios.DBRelacionalRepository;
import ui.windows.MenuWindow;
import usuario.Cuenta;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;
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
		
		repo.agregarDatosALaBDDDeLosArchivos();


	}

}
