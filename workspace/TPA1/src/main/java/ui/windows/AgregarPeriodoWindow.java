package ui.windows;

import java.awt.Color;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.AgregarPeriodoViewModel;

public class AgregarPeriodoWindow extends Dialog<AgregarPeriodoViewModel>{
	public AgregarPeriodoWindow(WindowOwner parent)  {
		super(parent, new AgregarPeriodoViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		 
		new Label(Panel).setText("Ingrese un periodo").setBackground(Color.ORANGE);
		
		new Label(Panel).setHeight(25);
		new TextBox(Panel).bindValueToProperty("periodo");
		
		
}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Agregar")
		.onClick(() -> {
						if(AgregarPeriodoViewModel.esUnPeriodoYaIngresado()){
							this.showError("El periodo ya fue agregado, seleccione otro");
						}
						else{
									AgregarPeriodoViewModel.setPeriodoIngresado();
									this.getDelegate().close();
									MetodologiasEmpresasWindow();
						}
			});
		
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
									this.getDelegate().close();
									MetodologiasEmpresasWindow();
				});
}
	

	
	public void MetodologiasEmpresasWindow()  {
		Dialog<?> dialog = new MetodologiasEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
}	
	

