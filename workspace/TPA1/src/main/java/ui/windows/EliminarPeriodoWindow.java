package ui.windows;

import java.awt.Color;

import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import ui.vm.EliminarPeriodoViewModel;

public class EliminarPeriodoWindow extends Dialog<EliminarPeriodoViewModel>{
	public EliminarPeriodoWindow(WindowOwner parent){
		super(parent, new EliminarPeriodoViewModel());
	}
	
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		
		new List<>(Panel).bindItemsToProperty("periodos");
		
		new Label(Panel).setText("Ingrese el periodo a eliminar").setBackground(Color.ORANGE);
		new Label(Panel).setHeight(25);
		new TextBox(Panel).bindValueToProperty("periodo");
		
	}
	

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Eliminar")
		.onClick(() -> {
			/*TODO: Falta agregar la ventana de la excepecion*/
						if(EliminarPeriodoViewModel.esUnPeriodoNoExistente()){
							System.out.println("El periodo nunca fue ingresado");
							this.getDelegate().close();
						}
						else{
									EliminarPeriodoViewModel.eliminarPeriodoIngresado();
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
