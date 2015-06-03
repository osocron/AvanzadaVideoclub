/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

//import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Vyyk
 */
public class VentanaPrincipal extends BorderPane{
    //private BorderPane root;
    private Button btnClientes;
    private Button btnPeliculas;
    private Button btnGenero;
    private Button btnPrestamo;
    private Button btnCerrar;
    
    public VentanaPrincipal(){
        Componentes();
    }
    
    public void Componentes(){
        VBox botones = new VBox(30);
        
        
        btnClientes = new Button("Clientes");
        btnPeliculas = new Button("Peliculas");
        //this.setCenter(btnPeliculas);
        btnGenero = new Button("Genero");
        //this.setCenter(btnGenero);
        btnPrestamo = new Button("Prestamo");
        //this.setCenter(btnPrestamo);
        btnCerrar = new Button("Cerrar");
        
        /*
        lo quehace este medotoesta abriendoun pane sin nada
        //CLIENTES////////estome abre una nuevaventana en clientes
        Button btnClientes =  new  Button ("Clientes"); 
        btnClientes.setOnAction ( new  EventHandler < ActionEvent >()  { 
         @Override 
         public  void handle ( ActionEvent e )  { 
         Stage stage =  new  Stage (); 
         stage.setTitle("titulo");
         Parent AchorPane = new AnchorPane();
         stage.setScene(new Scene(AchorPane,600,400));
         stage.show(); */
       
        //ES METODO VA A ENTRAR A LA CLASE DE CLIENTES
        btnClientes.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            Stage stage = new Stage();
            stage.setTitle("Nuestros clientes");
            Clientes root = new Clientes();
            stage.setScene(new Scene(root,600,400));
            stage.show();
        }        
});
        //ESTE METODO VA ENTRAR A LA CLASE PELICULAS
        //btnPeliculas.setLayoutX(310);
        //btnPeliculas.setLayoutY(150);
        btnPeliculas.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Stage stage = new Stage();
                stage.setTitle("Nuestras Peliculas");
                Peliculas root = new Peliculas();
                stage.setScene(new Scene(root,600,400));
                stage.show();
                
            }
        }
        );
        
        //ESTOVA ENTRAR A LA CLASE GENERO
        btnGenero.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Stage stage = new Stage();
                stage.setTitle("Nuestros Generos");
                Genero root = new Genero();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            }
        }
        );
        
        //ESTOVA A MOSTRAR LA CLASE PRESTAMOS
        btnPrestamo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Stage stage = new Stage();
                stage.setTitle("Prestamos");
                //Prestamo root = new Prestamo();
                //stage.setScene(new Sene(root,600,400));
                stage.show();
            }
        }
                
        );
        
        botones.getChildren().addAll(btnClientes,btnPeliculas,btnGenero,btnPrestamo,btnCerrar);
        this.setLeft(botones);
        
    }    
    
}
