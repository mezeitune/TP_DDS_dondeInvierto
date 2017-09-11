package ui.windows;

import java.awt.Color;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import repositorios.DBRelacionalRepository;
import ui.vm.MenuViewModel;
import utilities.JPAUtility;


@SuppressWarnings("serial")
public class MenuWindow extends Dialog<MenuViewModel> {
	
	static JPAUtility jpa=JPAUtility.getInstance();
	static EntityManager entityManager = jpa.getEntityManager();
	static DBRelacionalRepository repo=new DBRelacionalRepository<>(entityManager);

	public MenuWindow(WindowOwner cargaExitosaWindow) {
		super(cargaExitosaWindow, new MenuViewModel());
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Sistema de carga y consulta");
		
		new Label(mainPanel).setText("MENU PRINCIPAL").setBackground(Color.ORANGE).setHeight(80);
		
		new Label(mainPanel).setText("Estado carga de cuentas y empresas:");
		if(this.getModelObject().archivosCuentasCargados()) new Label(mainPanel).setText("Archivo csv cargado").setBackground(Color.GREEN);
		else new Label(mainPanel).setText("El sistema no registra un archivo csv").setBackground(Color.RED);
		
		new Button(mainPanel).setCaption("Seleccionar archivo de cuentas")
		.onClick(() -> {
				this.getDelegate().close();
				SeleccionarArchivoWindow();
		});
		
	}
	protected void addActions(Panel actionsPanel){
		
		Query query = entityManager.createQuery("from Empresa");
		
				
		actionsPanel.setLayout(new ColumnLayout(2));
		new Button(actionsPanel).setCaption("Evaluar Empresas con Indicadores")
		.onClick(() -> {
				if(!(query.getFirstResult() < 0)) {
					System.out.println(query.getResultList().get(0).toString());
					this.getDelegate().close();
					DatosIndicadoresWindow();
				}
				else this.showError("BD sin datos, debe cargar un archivo csv");
			}).setWidth(250);
		
		new Button(actionsPanel).setCaption("Evaluar Empresas con Metodologias")
		.onClick(() -> {
			
			//if(this.getModelObject().archivosCuentasCargados()) {
			if(!(query.getFirstResult() < 0)) {
				this.getDelegate().close();
				MetodologiasEmpresasWindow();
			}
			//else this.showError("Debe cargar un archivo de cuentas");
			else this.showError("BD sin datos, debe cargar un archivo csv");
			}).setWidth(250);
		
		
		new Button(actionsPanel).setCaption("Cargar, consultar y eliminar indicadores")
		.onClick(() -> {
					this.getDelegate().close();
					CargarIndicadoresWindow();
		}).setWidth(250);
		
		

		new Button(actionsPanel).setCaption("Cargar, consultar y eliminar Metodologias")
		.onClick(() -> {
				this.getDelegate().close();
					CargarMetodologiaWindow();
			}).setWidth(250);
	
		new Button(actionsPanel).setCaption("Ver archivos de cuentas importados")
		.onClick(() -> {
				this.getDelegate().close();
					ArchivosCuentasImportadosWindow();
			}).setWidth(250);
	
		
		
		
		
	}

	public void CargarIndicadoresWindow() {
		Dialog<?> dialog = new CargarIndicadoresWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void DatosIndicadoresWindow() {
		Dialog<?> dialog = new DatosCuentasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void SeleccionarArchivoWindow(){
		Dialog<?> dialog = new SeleccionarArchivoWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void MetodologiasEmpresasWindow() {
		Dialog<?> dialog = new MetodologiasEmpresasWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	public void CargarMetodologiaWindow() {
		Dialog<?> dialog = new CargarMetodologiaWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
	public void CargarBDWindow() {
		Dialog<?> dialog = new CargarBDWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}
	
	public void ArchivosCuentasImportadosWindow() {
		Dialog<?> dialog = new ArchivoCuentasImportadosWindow(this);
		dialog.open();
		dialog.onAccept(() -> {});
	}


}
