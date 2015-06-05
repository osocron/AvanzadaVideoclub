/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

import entidades.ClientesEntity;
import controlador.ControladorClientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class Clientes extends BorderPane{

    private TableColumn num;
    private TableColumn nombre;
    private TableColumn apellido;
    private TableColumn direccion;
    private TableColumn telefono;
    private TableColumn email;
    private TableColumn ifeOrc;
    private TableView<ClientesEntity> miTabla;

    private ObservableList<ClientesEntity> data = FXCollections.observableArrayList();
    
    public Clientes(){
        ComponentesCliente();
        setClientesALaTabla();
    }

    private void setClientesALaTabla() {
        List<ClientesEntity> listaClientes = ControladorClientes.getClientes();
        data.addAll(listaClientes);

        List<TableColumn> listaColumnas = new ArrayList<>();
        listaColumnas.add(nombre);
        listaColumnas.add(apellido);
        listaColumnas.add(direccion);
        listaColumnas.add(telefono);
        listaColumnas.add(email);
        listaColumnas.add(ifeOrc);
        listaColumnas.forEach(columna -> {
            columna.setCellFactory(TextFieldTableCell.forTableColumn());
        });

    }

    public void ComponentesCliente(){
        TableView tablaclientes = new TableView();
        
        num =new TableColumn("Num Cliente");
        nombre = new TableColumn("Nombre");
        apellido = new TableColumn("Apellido");
        direccion = new TableColumn("Doreccion");
        telefono = new TableColumn("Telefono");
        email = new TableColumn("E-mail");
        ifeOrc = new TableColumn("Numero de IFE");
        
        tablaclientes.getColumns().addAll(num,nombre,apellido,direccion,telefono,email,ifeOrc);
        this.setCenter(tablaclientes);
        
    }
    
    
}
