package ui.windows;

import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import excepciones.FormulaIndicadorNotFound;
import excepciones.FormulaIndicadorNotValidException;
import excepciones.IndicadorRepetidoException;
import excepciones.NombreIndicadorNotFound;
import ui.vm.CargarIndicadoresViewModel;
import usuario.Indicador;
import usuario.Metodologia;

@SuppressWarnings("serial")
public class CargarIndicadoresWindow extends Dialog<CargarIndicadoresViewModel> {

	public CargarIndicadoresWindow(WindowOwner owner) {
		super(owner, new CargarIndicadoresViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Carga de indicadores");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Escriba el nombre del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("nombreIndicador");
		
		new Label(form).setText("Escriba la formula del Indicador").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("formulaIndicador");		
		
		new Label(form).setText("Puede agregar indicadores pre-existentes en su formula").setBackground(Color.green);
		
		new Label(mainPanel).setText("Indicadores Disponibles").setBackground(Color.ORANGE);
		
		Table<Indicador> tableIndicadores = new Table<Indicador>(mainPanel, Indicador.class);
		
		tableIndicadores.setNumberVisibleRows(6).setWidth(200);
		
		tableIndicadores.bindItemsToProperty("indicadores"); 
		
		new Column<Indicador>(tableIndicadores).setTitle("Nombre").bindContentsToProperty("nombre");
		new Column<Indicador>( tableIndicadores).setTitle("Formula").bindContentsToProperty("formula");
		
		new Label(mainPanel).setText("Eliminar Indicador de la BDD").setBackground(Color.ORANGE);
		new Label(mainPanel).setText("Seleccione el indicador a eliminar de la BDD").setBackground(Color.green);
		
		Selector<Indicador> selectorCondicion = new Selector<Indicador>(mainPanel).allowNull(true);
		selectorCondicion.setWidth(100);
		selectorCondicion.bindItemsToProperty("indicadores").setAdapter(new PropertyAdapter(Indicador.class, "nombre"));
		selectorCondicion.bindValueToProperty("indicadorSeleccionado");
		
		
		new Label(form).setBackground(Color.GREEN).bindValueToProperty("resultadoIndicador");
	}
	
	
	protected void addActions(Panel actionsPanel){
		new Button(actionsPanel).setCaption("Eliminar Indicador Seleccionado")
		.onClick(() -> {

				this.getModelObject().eliminarIndicadorDeLaBDD();
		}).setWidth(200);
		
		new Button(actionsPanel).setCaption("Cargar Indicador")
								.onClick(() -> {

										try {
											this.getModelObject().generarIndicador();
											this.getDelegate().close();
											PreguntaNuevoIndicadorWindow();
										} catch (NombreIndicadorNotFound e) {
											this.showError("No se ha ingresado un nombre para el indicador");
										} catch (FormulaIndicadorNotFound e) {
											this.showError("No se ha ingresado la formula del indicador");
										} catch (IndicadorRepetidoException e) {
											this.showError("El nombre ingresado ya existe");
										} catch (FormulaIndicadorNotValidException e) {
											this.showError("El indicador ingresado no contiene cuentas ni indicadores validos,revise la formula ingresada");
										}
								}).setWidth(200);
	
		new Button(actionsPanel).setCaption("Volver")
		.onClick(() -> {
			this.getDelegate().close();
			MenuWindow();
		}).setWidth(200);
	}
	
	
	public void MenuWindow(){
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void PreguntaNuevoIndicadorWindow()  {
		Dialog<?> dialog = new PreguntaNuevoIndicadorWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	
	
}
