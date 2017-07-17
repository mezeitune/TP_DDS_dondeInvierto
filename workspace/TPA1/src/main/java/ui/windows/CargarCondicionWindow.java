package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import Condiciones.Condicion;
import ui.vm.CargarCondicionViewModel;
import ui.vm.CargarMetodologiaViewModel;

public class CargarCondicionWindow extends Dialog <CargarCondicionViewModel> {
	public CargarCondicionWindow(WindowOwner owner) {
		super(owner, new CargarCondicionViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
			this.setTitle("Carga de condicion");
	
		new Label(mainPanel).setText("Escriba el nombre de la condicion:").setBackground(Color.orange);
		
		new TextBox(mainPanel).setWidth(300).bindValueToProperty("nombreCondicion");

		
		
		new Label(mainPanel).setText("Elija el comparador siendo:  ").setBackground(Color.orange);
		new Label(mainPanel).setText("'> mayor' '< menor' '<= mayor o igual' '>= menor o igual'");
		
		Selector<String> selectorComparador = new Selector<String>(mainPanel).allowNull(true);
		selectorComparador.setWidth(50);
		//selectorComparador.bindItemsToProperty("Comparadores");
		//selectorComparador.bindValueToProperty("comparador");
	
		new Label( mainPanel).setText("Elija el tipo de condicion siendo:").setBackground(Color.orange);
		new Label( mainPanel).setText("Taxativa: Determina si es conveniente invertir o no en una empresa de forma deterministica");
		new Label( mainPanel).setText("Comparativa: Determina un ranking de empresas definiendo cual/es son las mejores para invertir");
		new Label( mainPanel).setText("Mixta: Primero 'filtra' a las empresas donde NO conviene invertir y luego realiza un ranking");

		Selector<String> selectorTipoDeCondicion = new Selector<String>(mainPanel).allowNull(true);
		selectorTipoDeCondicion.setWidth(100);
		selectorTipoDeCondicion.bindItemsToProperty("tipoCondiciones");
		selectorTipoDeCondicion.bindValueToProperty("tipoCondicion");
		
		new Label(mainPanel).setText("Elija el indicador por el cual se va evaluar la condicion en su metodologia  ").setBackground(Color.orange);
		
		Selector<String> selectorIndicador = new Selector<String>(mainPanel).allowNull(true);
		selectorIndicador.setWidth(50);
		selectorIndicador.bindItemsToProperty("indicadores");
		selectorIndicador.bindValueToProperty("indicador");
		
	}
	
	protected void addActions(Panel actionsPanel){
		
		
		new Button(actionsPanel).setCaption("Cargar Condicion")
		.onClick(() -> {
				this.getDelegate().close();												
		}).setWidth(150);
		
		
	
		new Button(actionsPanel).setCaption("Cancelar")
									.onClick(() -> {
													this.getDelegate().close();
									}).setWidth(150);
	}
	
	
}
