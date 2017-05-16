package Class;
import java.util.List;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import exceptions.CSVInexistenteException;
import parser.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import ui.windows.MenuWindow;
import usuario.Indicador;

@Observable
public class Main extends Application{

	
	public static void main(String[] args){

			new ParserFormulaToIndicador();//cargo todos los indicadores definidos por el usuario cuando empieza el sistema
		
			//consultarCuentas(); // para verificar que los indicadores se cargaron correctamente en el repositorio
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
		
		

		/*public static void consultarCuentas(){
			int i,j;
			for(i=0; i< ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario().size();i++){
				System.out.println(ArchivoEIndicadoresUsuarioRepository.getIndicadoresDefinidosPorElUsuario().get(i).getNombre());
				
			}	  	
		}*/
		
}		
	

/*List<Empresa> empresasPrueba = new Adapter().getEmpresasDelArchivo();
	Main.consultarCuentas(empresasPrueba);*/
	
	



