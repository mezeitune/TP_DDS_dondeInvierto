package ui.windows;

import java.awt.Color;
import java.io.IOException;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import Condiciones.Condicion;
import ui.vm.CargarCriterioViewModel;
import ui.vm.CargarMetodologiaViewModel;
import usuario.Cuenta;
import usuario.Indicador;
import usuario.Metodologia;

public class CargarCriterioWindow extends Dialog <CargarCriterioViewModel> {
	public CargarCriterioWindow(WindowOwner owner) {
		super(owner, new CargarCriterioViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
			this.setTitle("Carga de condicion");
	
		new Label(mainPanel).setText("Escriba el nombre de la condicion:").setBackground(Color.orange);
		
		new TextBox(mainPanel).setWidth(300).bindValueToProperty("nombreCondicion");

		
		
		new Label(mainPanel).setText("Elija el comparador siendo:  ").setBackground(Color.orange);
		new Label(mainPanel).setText("'> mayor' '< menor' '<= menor o igual' '>= mayor o igual'");
		
		Selector<String> selectorComparador = new Selector<String>(mainPanel).allowNull(true);
		selectorComparador.setWidth(50);
		selectorComparador.bindItemsToProperty("comparadores");
		selectorComparador.bindValueToProperty("comparador");
	
		new Label( mainPanel).setText("Elija el tipo de condicion siendo:").setBackground(Color.orange);
		new Label( mainPanel).setText("Taxativa: Determina si es conveniente invertir o no en una empresa de forma deterministica");
		new Label( mainPanel).setText("Comparativa: Determina un ranking de empresas definiendo cual/es son las mejores para invertir");
		

		Selector<String> selectorTipoDeCondicion = new Selector<String>(mainPanel).allowNull(true);
		selectorTipoDeCondicion.setWidth(100);
		selectorTipoDeCondicion.bindItemsToProperty("tipoCondiciones");
		selectorTipoDeCondicion.bindValueToProperty("tipoCondicion");
		
		new Label( mainPanel).setText("Escriba el peso de la condicion:").setBackground(Color.orange);
		
		new TextBox(mainPanel).setWidth(300).bindValueToProperty("pesoCondicion");
		
		new Label(mainPanel).setText("Elija el indicador por el cual se va evaluar la condicion en su metodologia  ").setBackground(Color.orange);
		Selector<Cuenta> selectorIndicadorAEvaluar = new Selector<Cuenta>(mainPanel).allowNull(true);
		selectorIndicadorAEvaluar.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Cuenta.class, "nombre"));
		selectorIndicadorAEvaluar.setWidth(100);
		selectorIndicadorAEvaluar.bindValueToProperty("indicador");
		
		/*Selector<Indicador> selectorIndicador = new Selector<Indicador>(mainPanel).allowNull(true);
		selectorIndicador.setWidth(50);
		selectorIndicador.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Indicador.class, "nombre"));
		selectorIndicador.bindValueToProperty("indicador");*/
		
	}
	
	protected void addActions(Panel actionsPanel){
		
		
		new Button(actionsPanel).setCaption("Cargar Condicion")
		.onClick(() -> {
				
					CargarCriterioViewModel.generarCondicion();
				
				this.getDelegate().close();												
		}).setWidth(150);
		
		
	
		new Button(actionsPanel).setCaption("Cancelar")
									.onClick(() -> {
													this.getDelegate().close();
									}).setWidth(150);
	}
	
	
}
