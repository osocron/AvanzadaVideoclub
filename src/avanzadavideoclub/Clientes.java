/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class Clientes extends BorderPane{
    private TableColumn num;
    private TableColumn nombre;
    private TableColumn apellido;
    private TableColumn email;
    private TableColumn telefono;
    
    public Clientes(){
        ComponentesCliente();
    }
    
    public void ComponentesCliente(){
        TableView tablaclientes = new TableView();
        
        num =new TableColumn("Num.");
        nombre = new TableColumn("Nombre");
        apellido = new TableColumn("Apellido");
        email = new TableColumn("E-mail");
        telefono = new TableColumn("Telefono");
        
        tablaclientes.getColumns().addAll(num,nombre,apellido,email,telefono);
        this.setCenter(tablaclientes);
        
    }
    
    
}
