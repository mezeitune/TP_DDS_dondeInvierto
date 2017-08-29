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

import condiciones.Condicion;
import excepciones.CondicionesNotFoundException;
import excepciones.MetodologiaRepetidaException;
import excepciones.NombreMetodologiaNotFoundException;
import ui.vm.CargarMetodologiaViewModel;
import usuario.Metodologia;

@SuppressWarnings("serial")
public class CargarMetodologiaWindow extends Dialog <CargarMetodologiaViewModel>{

	public CargarMetodologiaWindow(WindowOwner owner) {
		super(owner, new CargarMetodologiaViewModel());
	}
	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		this.setTitle("Carga de metodologias");
		
		Panel form = new Panel(mainPanel);
		form.setLayout(new ColumnLayout(2));
		
		new Label(form).setText("Escriba el nombre de la metodologia").setBackground(Color.orange);
		
		new TextBox(form).setWidth(300).bindValueToProperty("nombreMetodologia");
		
		new Button(mainPanel).setCaption("Agregar Condicion")
		.onClick(() -> {
				AgregarCondicionSeleccionada();
				this.getModelObject().refresh();
		});
		
		new Button(mainPanel).setCaption("Eliminar Condicion")
		.onClick(() -> {
				EliminarCriterioSeleccionadoWindow();
				this.getModelObject().refresh();
		});
		
		
		new Label(mainPanel).setText("Condiciones Seleccionadas").setBackground(Color.ORANGE);
		
		Table<Condicion> tableCondSeleccionada = new Table<Condicion>(mainPanel, Condicion.class);
		
		tableCondSeleccionada.setNumberVisibleRows(6).setWidth(200);
		
		tableCondSeleccionada.bindItemsToProperty("condiciones"); 
		
		new Column<Condicion>(tableCondSeleccionada).setTitle("Nombre").bindContentsToProperty("nombre");
		
		new Label(mainPanel).setText("Metodologias Disponibles").setBackground(Color.ORANGE);
		
		Table<Metodologia> tableMetodologias = new Table<Metodologia>(mainPanel, Metodologia.class);
		
		tableMetodologias.setNumberVisibleRows(6).setWidth(200);
		
		tableMetodologias.bindItemsToProperty("metodologias"); 
		
		new Column<Metodologia>(tableMetodologias).setTitle("Nombre").bindContentsToProperty("nombre");
		
		new Label(mainPanel).setText("Eliminar Metodologia").setBackground(Color.ORANGE);
		new Label(mainPanel).setText("Elija la metodologia a eliminar de la BDD").setBackground(Color.green);
		Selector<Metodologia> selectorCondicion = new Selector<Metodologia>(mainPanel).allowNull(true);
		selectorCondicion.setWidth(100);
		selectorCondicion.bindItemsToProperty("metodologias").setAdapter(new PropertyAdapter(Metodologia.class, "nombre"));
		selectorCondicion.bindValueToProperty("metodologiaSeleccionada");
		
		new Label(form).setBackground(Color.GREEN).bindValueToProperty("resultadoIndicador");
		
	}
	
	
	protected void addActions(Panel actionsPanel){
		
		new Button(actionsPanel).setCaption("Eliminar metodolologia seleccionada")
		.onClick(() -> {
					getModelObject().eliminarMetodologiaDeBDD();
					this.getModelObject().refresh();
		}).setWidth(200);
		
		new Button(actionsPanel).setCaption("Crear Condicion ")
		.onClick(() -> {
					CargarCondicionWindow();
					this.getModelObject().refresh();
		}).setWidth(200);
		
		
		new Button(actionsPanel).setCaption("Crear Metodologia")
								.onClick(() -> {
									
											try {
												this.getModelObject().generarMetodologia();
												this.showInfo("La metodologia ha sido cargada exitosamente");
											} catch (NombreMetodologiaNotFoundException e) {
												this.showError("Debe ingresar el nombre de la Metodologia");
											} catch (CondicionesNotFoundException e) {
												this.showError("Debe ingresar condiciones a la Metodologia");
											} catch (MetodologiaRepetidaException e) {
												this.showError("El nombre de la metodologia ya fue ingresado");
											}
								}).setWidth(200);
	
	
		new Button(actionsPanel).setCaption("Volver")
									.onClick(() -> {
											this.getDelegate().close();
											MenuWindow();
									}).setWidth(200);
	}
	
	
	public void MenuWindow() {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
	public void MetodologiaRepetidaWindow() {
		Dialog<?> dialog = new IndicadorRepetidoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void CargarCondicionWindow() {
		Dialog<?> dialog = new CargarCondicionWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void MetodologiaVaciaWindow()  {
		Dialog<?> dialog = new IndicadorVacioWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
		
	}
	public void AgregarCondicionSeleccionada()  {
		Dialog<?> dialog = new AgregarCondicionSeleccionadaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}

	public void EliminarCriterioSeleccionadoWindow() {
		Dialog<?> dialog = new EliminarCondicionSeleccionadaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
}

