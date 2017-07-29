package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import Condiciones.Condicion;
import ui.vm.EliminarEmpresaViewModel;
import ui.vm.ElminarCondicionSeleccionadaViewModel;
import usuario.Empresa;

public class EliminarCondicionSeleccionadaWindow extends Dialog <ElminarCondicionSeleccionadaViewModel>{
	public EliminarCondicionSeleccionadaWindow(WindowOwner parent) throws IOException {
		super(parent, new ElminarCondicionSeleccionadaViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		
		new Label(Panel).setText("Seleccione una condicion a eliminar").setBackground(Color.ORANGE);
		Selector<Condicion> selectorEmpresa = new Selector<Condicion>(Panel).allowNull(true);
		selectorEmpresa.setWidth(100);
		selectorEmpresa.bindItemsToProperty("criteriosSeleccionados").setAdapter(new PropertyAdapter(Condicion.class, "nombre"));
		selectorEmpresa.bindValueToProperty("criterioSeleccionado");
		
	}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Eliminar")
		.onClick(() -> {
					
							this.getDelegate().close();
							
						
			});
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
							
									this.getDelegate().close();
							
								
					
				});
	}
	

}	

