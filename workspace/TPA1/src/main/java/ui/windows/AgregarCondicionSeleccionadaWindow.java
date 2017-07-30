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
import ui.vm.AgregarCondicionSeleccionadaViewModel;
import ui.vm.AgregarEmpresaViewModel;
import usuario.Empresa;

public class AgregarCondicionSeleccionadaWindow extends Dialog <AgregarCondicionSeleccionadaViewModel>{
	public AgregarCondicionSeleccionadaWindow(WindowOwner parent) throws IOException {
		super(parent, new AgregarCondicionSeleccionadaViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setText("Elija la condicion a agregar").setBackground(Color.orange);
		
		Selector<Condicion> selectorCondicion = new Selector<Condicion>(mainPanel).allowNull(true);
		selectorCondicion.setWidth(100);
		selectorCondicion.bindItemsToProperty("criteriosSeleccionados").setAdapter(new PropertyAdapter(Condicion.class, "nombre"));
		selectorCondicion.bindValueToProperty("condicion");

		
}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Agregar")
		.onClick(() -> {
						
									AgregarCondicionSeleccionadaViewModel.agregarCondicionALaLista();
									if(AgregarCondicionSeleccionadaViewModel.getCodigoError()==1){
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

