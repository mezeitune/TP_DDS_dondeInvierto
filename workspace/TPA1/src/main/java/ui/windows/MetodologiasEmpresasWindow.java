package ui.windows;


import java.awt.Color;

import org.uqbar.arena.bindings.PropertyAdapter;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import excepciones.EmpresasIsEmptyException;
import excepciones.MetodologiaNotFoundException;
import excepciones.PeriodosIsEmptyException;
import ui.vm.MetodologiasEmpresasViewModel;
import usuario.Empresa;
import usuario.Indicador;
import usuario.Metodologia;

@SuppressWarnings("serial")
public class MetodologiasEmpresasWindow extends Dialog<MetodologiasEmpresasViewModel>{
	public MetodologiasEmpresasWindow(WindowOwner parent){
		super(parent, new MetodologiasEmpresasViewModel());
	}
	
	int i = 0;
	
	
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Consulta de Metodologias aplicadas a empresas");
		
		
		mainPanel.setLayout(new HorizontalLayout());
		Panel Panel = new Panel(mainPanel);
		
		new Label(Panel).setText("Empresas a evaluar").setBackground(Color.ORANGE);
		Table<Empresa> tableEmpresas = new Table<Empresa>(Panel, Empresa.class);
		tableEmpresas.bindItemsToProperty("empresas");
		new Column<Empresa>(tableEmpresas).setTitle("Nombre").bindContentsToProperty("nombre");
		
		new Label(Panel).setText("Periodos a evaluar").setBackground(Color.ORANGE);
		new List<>(Panel).bindItemsToProperty("periodos");
		
		new Button(Panel).setCaption("Agregar una Empresa")
		.onClick(() -> {
				this.getDelegate().close();
				AgregarEmpresaWindow();
		});
		
		new Button(Panel).setCaption("Agregar un periodo")
		.onClick(() -> {
				this.getDelegate().close();
				AgregarPeriodoWindow();
		});
		
		new Button(Panel).setCaption("Eliminar un periodo")
		.onClick(() -> {
				this.getDelegate().close();
				EliminarPeriodoWindow();
		});
		
		new Button(Panel).setCaption("Eliminar una Empresa")
		.onClick(() -> {
				this.getDelegate().close();
				EliminarEmpresaWindow();
		});
		
		new Button(Panel).setCaption("Vaciar la Lista")
		.onClick(() -> {
			this.getModelObject().vaciarListaEmpresas();
		});
		
		new Button(Panel).setCaption("Completar la Lista con todas las empresas del archivo")
		.onClick(() -> {
			this.getModelObject().autocompletarListaEmpresasAEvaluar();
			
		});
		new Label(Panel).setText("");
		new Label(Panel).setText("");
		new Label(Panel).setText("");
		
		new Button(Panel).setCaption("Volver al Menu Principal")
		.onClick(() -> {
				this.getDelegate().close();
				MenuWindow();
		});
		
		Panel Panel2 = new Panel(mainPanel);
		Panel2.setLayout(new VerticalLayout());
		
		new Label(Panel2).setText("Seleccione la metodologia").setBackground(Color.ORANGE);
		Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(Panel2).allowNull(true);
		selectorMetodologia.setWidth(100);
		selectorMetodologia.bindItemsToProperty("metodologias").setAdapter(new PropertyAdapter(Metodologia.class, "nombre"));
		selectorMetodologia.bindValueToProperty("metodologia");
		
		new Button(Panel2).setCaption("Evaluar")
		.onClick(() -> {
			
				try {
					this.getModelObject().evaluar();
				}   catch (PeriodosIsEmptyException e) {this.showError("Debe seleccionar periodos para evaluar empresas");}
					catch(EmpresasIsEmptyException e) {this.showError("Debe seleccionar empresas para evaluarlas");}
					catch(MetodologiaNotFoundException e) {this.showError("Debe seleccionar una metodologia para evaluar");}
		});
		
		new Label(Panel2).setText("RANKING EMPRESAS").setBackground(Color.GREEN);
		//new List<>(Panel2).bindItemsToProperty("empresasQueConvieneInvertir").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		Table<Empresa> tableEmpresasRankeadas = new Table<Empresa>(Panel2, Empresa.class);
		tableEmpresasRankeadas.setNumberVisibleRows(6).setWidth(200);
		tableEmpresasRankeadas.bindItemsToProperty("empresasInvertibles"); 
		new Column<Empresa>(tableEmpresasRankeadas).setTitle("Nombre").bindContentsToProperty("nombre");
		
		new Label(Panel2).setText("NO CONVIENE INVERTIR EN").setBackground(Color.RED);
		//new List<>(Panel2).bindItemsToProperty("empresasQueNoConvieneInvertir").setAdapter(new PropertyAdapter(Empresa.class, "nombre"));
		Table<Empresa> tableEmpresasQueNoConviene = new Table<Empresa>(Panel2, Empresa.class);
		tableEmpresasQueNoConviene.setNumberVisibleRows(6).setWidth(200);
		tableEmpresasQueNoConviene.bindItemsToProperty("empresasNoInvertibles"); 
		new Column<Empresa>(tableEmpresasQueNoConviene).setTitle("Nombre").bindContentsToProperty("nombre");
		
		
}


	public void MenuWindow()  {
		Dialog<?> dialog = new MenuWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargaIndicadoresWindow()  {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void AgregarEmpresaWindow()  {
		Dialog<?> dialog = new AgregarEmpresaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void AgregarPeriodoWindow()  {
		Dialog<?> dialog = new AgregarPeriodoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void EliminarEmpresaWindow()  {
		Dialog<?> dialog = new EliminarEmpresaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void EliminarPeriodoWindow() {
		Dialog<?> dialog = new EliminarPeriodoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
}
