/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class Clientes extends BorderPane{
    private TableColumn num;
    private TableColumn nombre;
    private TableColumn apellido;
    private TableColumn direccion;
    private TableColumn telefono;
    private TableColumn email;
    private TableColumn ifeOrc;
    
    public Clientes(){
        ComponentesCliente();
    }
    
    public void ComponentesCliente(){
        TableView tablaclientes = new TableView();
        
        num =new TableColumn("Num.");
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
