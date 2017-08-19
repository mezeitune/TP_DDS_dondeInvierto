package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import Comparadores.Comparador;
import Condiciones.TipoCondicion;
import excepciones.IndicadorNotFound;
import excepciones.NombreCondicionNotFound;
import excepciones.PesoCondicionNotFound;
import excepciones.TipoCondicionNotFound;
import ui.vm.CargarCondicionViewModel;
import usuario.Cuenta;

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
		new Label(mainPanel).setText("'> mayor' '< menor' '<= menor o igual' '>= mayor o igual'");
		
		Selector<String> selectorComparador = new Selector<String>(mainPanel).allowNull(true);
		selectorComparador.setWidth(50);
		selectorComparador.bindItemsToProperty("comparadores").setAdapter(new PropertyAdapter(Comparador.class, "nombre"));
		selectorComparador.bindValueToProperty("comparador");
	
		new Label( mainPanel).setText("Elija el tipo de condicion siendo:").setBackground(Color.orange);
		new Label( mainPanel).setText("Taxativa: Determina si es conveniente invertir o no en una empresa de forma deterministica");
		new Label( mainPanel).setText("Comparativa: Determina un ranking de empresas definiendo cual/es son las mejores para invertir");
		new Label( mainPanel).setText("Mixta: Combina un tipo taxativo y un tipo comparativo");
		

		Selector<String> selectorTipoDeCondicion = new Selector<String>(mainPanel).allowNull(true);
		selectorTipoDeCondicion.setWidth(100);
		selectorTipoDeCondicion.bindItemsToProperty("tipoCondiciones").setAdapter(new PropertyAdapter(TipoCondicion.class, "nombre"));
		selectorTipoDeCondicion.bindValueToProperty("tipoCondicion");
		
		new Label( mainPanel).setText("Escriba el peso de la condicion:").setBackground(Color.orange);
		new TextBox(mainPanel).setWidth(300).bindValueToProperty("pesoCondicion");
		
		new Label(mainPanel).setText("Elija el indicador por el cual se va evaluar la condicion en su metodologia  ").setBackground(Color.orange);
		Selector<Cuenta> selectorIndicadorAEvaluar = new Selector<Cuenta>(mainPanel).allowNull(true);
		selectorIndicadorAEvaluar.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Cuenta.class, "nombre"));
		selectorIndicadorAEvaluar.setWidth(100);
		selectorIndicadorAEvaluar.bindValueToProperty("indicador");
		
	}
	
	protected void addActions(Panel actionsPanel){
		
		
		new Button(actionsPanel).setCaption("Cargar Condicion")
		.onClick(() -> {
					try {
						this.getModelObject().generarCondicion();
						this.showInfo("Condicion cargada exitosamente");
					} catch (NombreCondicionNotFound e) {
						this.showError("Debe ingresar el nombre de la condicion");
					} catch (TipoCondicionNotFound e) {
						this.showError("Debe seleccionar el tipo de la condicion");
					} catch (PesoCondicionNotFound e) {
						this.showError("Debe ingresar el peso de la condicion");
					} catch (IndicadorNotFound e) {
						this.showError("Debe ingresar el indicador de la condicion");
					}
		}).setWidth(150);
	
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
						this.getDelegate().close();
		}).setWidth(150);
	}
	
	
}
