/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Genero extends BorderPane{
    private TableColumn genero;
    private TableColumn sinopsis;
    private TextField nombre;
    private Button x;
    public Genero(){
        ComponentesGenero();     
}
    
    public void ComponentesGenero(){
    HBox contenedor = new HBox();
    Pane probando = new Pane();
    contenedor.setPadding(new Insets(5,5,5,5));
    contenedor.getChildren().addAll(new Label("Name:  "));
    contenedor.getChildren().addAll(new TextField ());
    
    contenedor.getChildren().addAll(new Label("Apellido"));
//    contenedor.setPadding(new Insets(10,10,10,10)).getChildren().addAll(new TextField());
   // a= new Label("Apellido");
    
    TableView tabla=new TableView(); 
    
    
    genero= new TableColumn("Genero   ");
    nombre = new TextField("Probando");
    x= new Button("Hola");
    
    
    
    tabla.getColumns().addAll(genero);
    this.setLeft(tabla);
    
    contenedor.alignmentProperty();
        this.setRight(contenedor);
    
} 
            
    
} 

/*
public class Genero extends BorderPane{
    //private HBox cotenedorTabla;
    private TableColumn genero;
    private TableColumn sinopsis;
    
    public Genero(){
        ComponentesGenero();
    }
    
    public void ComponentesGenero(){
        HBox contenedorTabla =new HBox();
        TableView tablagenero = new TableView();
        
        genero= new TableColumn("Genero   ");
        sinopsis= new TableColumn("Sinopsis");
        
        tablagenero.getColumns().addAll(genero,sinopsis);
        this.setCenter(tablagenero);
        
    }
    
}
*/