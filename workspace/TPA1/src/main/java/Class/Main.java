package Class;
import java.util.List;

import org.omg.CORBA.UserException;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import exceptions.CSVInexistenteException;
import parser.parserArchivos.CSVToEmpresas;
import parserFormulaInidicador.ParserFormulaToIndicador;
import repository.ArchivoEIndicadoresUsuarioRepository;
import repository.MetodologiasRepository;
import ui.windows.MenuWindow;
import usuario.Indicador;

@Observable
public class Main extends Application{

	
	public static void main(String[] args){
			MetodologiasRepository.cargarMetodologiasPredefinidos();
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
		
}
