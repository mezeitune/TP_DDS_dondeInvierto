package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import Condiciones.Condicion;
import ui.vm.AgregarCriterioSeleccionadoViewModel;
import ui.vm.AgregarEmpresaViewModel;
import usuario.Empresa;

public class AgregarCriterioSeleccionadoWindow extends Dialog <AgregarCriterioSeleccionadoViewModel>{
	public AgregarCriterioSeleccionadoWindow(WindowOwner parent) throws IOException {
		super(parent, new AgregarCriterioSeleccionadoViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());
		Panel Panel = new Panel(mainPanel);
		
		new Label(mainPanel).setText("Elija el criterio a agregar a los criterios de la metodologia").setBackground(Color.orange);
		
		Selector<Condicion> selectorCondicion = new Selector<Condicion>(mainPanel).allowNull(true);
		selectorCondicion.setWidth(100);
		selectorCondicion.bindItemsToProperty("criteriosSeleccionados").setAdapter(new PropertyAdapter(Condicion.class, "nombre"));
		selectorCondicion.bindValueToProperty("condicion");

		
}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Agregar")
		.onClick(() -> {
						
									AgregarCriterioSeleccionadoViewModel.agregarCondicionALaLista();
									if(AgregarCriterioSeleccionadoViewModel.getCodigoError()==1){
									}else {
										this.getDelegate().close();
										}
							
						
			});
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
									
									this.getDelegate().close();
							
					
				});
}
	

}	

