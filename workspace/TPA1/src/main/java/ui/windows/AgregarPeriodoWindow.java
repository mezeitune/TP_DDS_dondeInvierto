package ui.windows;

import java.awt.Color;
import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.eclipse.ui.actions.LabelRetargetAction;
import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import excepciones.ArchivoInexistenteException;
import repository.EmpresasAEvaluarRepository;
import scala.util.control.Exception;
import ui.vm.AgregarEmpresaViewModel;
import ui.vm.AgregarPeriodoViewModel;
import ui.vm.DatosViewModel;
import usuario.Empresa;

public class AgregarPeriodoWindow extends Dialog<AgregarPeriodoViewModel>{
	public AgregarPeriodoWindow(WindowOwner parent) throws IOException {
		super(parent, new AgregarPeriodoViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		
		new Label(Panel).setText("Seleccione un periodo: ").setBackground(Color.ORANGE);

		
		new Label(Panel).setHeight(25);
		new TextBox(Panel).bindValueToProperty("periodo");
		
		
}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Agregar")
		.onClick(() -> {
						if(AgregarPeriodoViewModel.getCodigoError()==1){
							
								System.out.println("Periodo ya agregada a la lista, seleccione otra");
						
							}else{
								try{
									this.getDelegate().close();
							
									MetodologiasEmpresasWindow();
							
								}catch (IOException e) {
									e.printStackTrace();
								}
						}
			});
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
								try{
									this.getDelegate().close();
							
									MetodologiasEmpresasWindow();
							
								}catch (IOException e) {
									e.printStackTrace();
								}
					
				});
}
	

	
	public void MetodologiasEmpresasWindow() throws IOException {
		Dialog<?> dialog = new MetodologiasEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	

}	
	

