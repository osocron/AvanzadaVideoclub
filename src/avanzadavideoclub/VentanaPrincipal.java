/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

//import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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

       
        //ES METODO VA A ENTRAR A LA CLASE DE CLIENTES
        btnClientes.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            Stage stage = new Stage();
            stage.setTitle("Nuestros clientes");
            Clientes root = new Clientes();
            stage.setScene(new Scene(root,1000,500));
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
                stage.setScene(new Scene(root,1200,500));
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

        btnPrestamo.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                Stage stage = new Stage();
                stage.setTitle("Prestamos");
                Prestamo root = new Prestamo();
                stage.setScene(new Scene(root,600,400));
                stage.show();
            }
        });
        
        botones.getChildren().addAll(btnClientes,btnPeliculas,btnGenero,btnPrestamo,btnCerrar);
        List<Button> listaBotones = new ArrayList<>();
        listaBotones.add(btnClientes);
        listaBotones.add(btnPeliculas);
        listaBotones.add(btnGenero);
        listaBotones.add(btnPrestamo);
        listaBotones.add(btnCerrar);
        listaBotones.forEach(boton -> {
            VBox.setMargin(boton, new Insets(10, 10, 10, 10));
        });

        this.setLeft(botones);
        
    }    
    
}
