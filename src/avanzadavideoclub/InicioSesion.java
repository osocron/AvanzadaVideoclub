/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Vyyk
 */
public class InicioSesion extends Application{
   /*
    private ImageView empleado;
    private Text tex1;
    private Text tex2;
    private TextField tfUsuario;
    private TextField tfContraseña;
    private Button btnEntrar;
    */
    
    
    public void start(Stage primaryStage){
       primaryStage.setTitle("Bienvenido");
       
       GridPane grid = new GridPane();
       grid.setAlignment(Pos.CENTER);
       grid.setHgap(10);
       grid.setVgap(10);
       grid.setPadding(new Insets(25,25,25,25));
       
       Text scenetitle = new Text("Bienvenido al VideoCulb");
       scenetitle.setFont(Font.font("tahoma",FontWeight.NORMAL,20));
       grid.add(scenetitle, 0, 0, 4, 1);
       
       Label nombreUsuario= new Label("Usuario: ");
       grid.add(nombreUsuario, 0 , 1);
       
       TextField ftUsuario = new TextField();
       grid.add(ftUsuario, 1, 1);
       
       Label contrasena = new Label("Contraseña: ");
       grid.add(contrasena, 1, 1);
       
       PasswordField pwBox = new PasswordField();
       grid.add(pwBox, 1, 2);
       
       Button btnEntrar= new Button("Entrar");
       HBox hbbtn= new HBox(10);
       hbbtn.setAlignment(Pos.BOTTOM_RIGHT);
       hbbtn.getChildren().add(btnEntrar);
       grid.add(hbbtn, 1, 4);
       
       final Text actiontarget = new Text();
       grid.add(actiontarget, 1, 6);
       
       Scene scene = new Scene(grid,300, 275);
       primaryStage.setScene(scene);
       
       
       primaryStage.show();
       
    }
    
    
    
    
    
    
   
    
}
