package Class;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import condiciones.Condicion;
import excepciones.CSVInexistenteException;
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
		
		
		//Consulta por un id de cualquier tabla sea
		Indicador indicador=(Indicador) repo.findById(Indicador.class,1);
		//System.out.println(indicador.toString());
		
		ParserCsv parserCsv = new ParserCsv("empresas.csv");
		Query queryEmpresas = entityManager.createQuery("from Empresa"); 
		List <Empresa> empresasEnLaBD = queryEmpresas.getResultList(); 
		List <Empresa> empresasAAgregarEnLaBD= parserCsv.csvFileToEmpresas();
		if(empresasAAgregarEnLaBD.containsAll(empresasEnLaBD)){
			entityManager.getTransaction().begin();			

			empresasAAgregarEnLaBD.forEach(unaEmp -> repo.agregar(unaEmp));
			
			entityManager.getTransaction().commit();
		}
		
		
		
		/*ParserJsonAObjetosJava parserCondiciones = new ParserJsonAObjetosJava("condiciones.json");
		List<Condicion> condicionesAAgregarEnLaBD = parserCondiciones.getCondicionesDelArchivo();
		Query queryCondiciones = entityManager.createQuery("from Condicion"); 
		List <Condicion> condicionesEnLaBD = queryCondiciones.getResultList(); 
		if(condicionesAAgregarEnLaBD.containsAll(empresasAAgregarEnLaBD)){
			entityManager.getTransaction().begin();
			//condicionesAAgregarEnLaBD.forEach(unaCond -> repo.agregar(unaCond));
			entityManager.getTransaction().commit();
		}*/
		
		
		
		
		
		
		
		ParserJsonAObjetosJava parserMetodologias = new ParserJsonAObjetosJava("metodologias.json");
		List <Metodologia> metodologiasAAgregarEnLaBD= parserMetodologias.getMetodologiasDelArchivo();
		Query queryMetodologias = entityManager.createQuery("from Metodologia"); 
		List <Condicion> metodologiasEnLaBD = queryMetodologias.getResultList(); 
		if(metodologiasAAgregarEnLaBD.containsAll(metodologiasEnLaBD)){
			entityManager.getTransaction().begin();
			
			metodologiasAAgregarEnLaBD.forEach(unaMet -> repo.agregar(unaMet));
			
			entityManager.getTransaction().commit();
		}
		
		
		List<Indicador> indicadores=repo.filtrarPorCampoEspecifico(Indicador.class,"Indicador", "formula", "otrooo");
		//System.out.println(indicadores.toString());
		
		
		
		Cuenta cuenta=(Cuenta) repo.findById(Cuenta.class,1);
		//System.out.println(cuenta.toString());
		
		
		Empresa empresa=(Empresa) repo.findById(Empresa.class,1);
		//System.out.println(empresa.getCuentas().get(0).toString());
		
	}

}
