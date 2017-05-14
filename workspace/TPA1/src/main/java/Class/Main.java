package Class;
import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.utils.Observable;

import exceptions.CSVInexistenteException;
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
	

/*List<Empresa> empresasPrueba = new Adapter().getEmpresasDelArchivo();
	Main.consultarCuentas(empresasPrueba);*/
	
	

	/*public static void consultarCuentas(List<Empresa> empresas){
		int i,j;
		for(i=0; i< empresas.size();i++){
			System.out.println(empresas.get(i).getNombre());
			for(j=0; j < empresas.get(i).getCuentas().size(); j++){
				System.out.println(empresas.get(i).getCuentas().get(j).getNombre());
				System.out.println(empresas.get(i).getCuentas().get(j).getPeriodo());
				System.out.println(empresas.get(i).getCuentas().get(j).getValor());
			}
		}	  	
	}*/


