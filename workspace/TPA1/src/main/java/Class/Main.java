package Class;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import excepciones.CSVInexistenteException;
import ui.windows.MenuWindow;

@Observable
public class Main extends Application{

	
	public static void main(String[] args){
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
