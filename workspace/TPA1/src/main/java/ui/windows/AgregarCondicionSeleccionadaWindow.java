package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import condiciones.Condicion;
import excepciones.CondicionRepetidaException;
import ui.vm.AgregarCondicionSeleccionadaViewModel;

public class AgregarCondicionSeleccionadaWindow extends Dialog <AgregarCondicionSeleccionadaViewModel>{
	public AgregarCondicionSeleccionadaWindow(WindowOwner parent) {
		super(parent, new AgregarCondicionSeleccionadaViewModel());
	}
	protected void createFormPanel(Panel mainPanel) {
		mainPanel.setLayout(new VerticalLayout());
		
		new Label(mainPanel).setText("Elija la condicion a agregar").setBackground(Color.orange);
		
		Selector<Condicion> selectorCondicion = new Selector<Condicion>(mainPanel).allowNull(true);
		selectorCondicion.setWidth(100);
		selectorCondicion.bindItemsToProperty("condiciones").setAdapter(new PropertyAdapter(Condicion.class, "nombre"));
		selectorCondicion.bindValueToProperty("condicionSeleccionada");

		
}

	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Agregar")
		.onClick(() -> {
							try{
							this.getModelObject().agregarCondicionALaLista();
							}catch(CondicionRepetidaException e){
								this.showError("La condicion seleccionada ya fue agregada, seleccione otra");
							}
							this.getDelegate().close();
			});
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
							this.getDelegate().close();
				});
}
	
}	

