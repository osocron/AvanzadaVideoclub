/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

import java.awt.Window;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import controlador.ConexionBD;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Vyyk
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Vyyk
 */
public class AvanzadaVideoclub extends Application{
    
    
    public void start(Stage primaryStage){
       primaryStage.setTitle("Bienvenido");
       
       GridPane grid = new GridPane();
       grid.setAlignment(Pos.CENTER);
       grid.setHgap(10);
       grid.setVgap(10);
       grid.setPadding(new Insets(25,25,25,25));
       
       Text scenetitle = new Text("Bienvenido al VideoClub");
       scenetitle.setFont(Font.font("tahoma",FontWeight.NORMAL,20));
       grid.add(scenetitle, 0, 0, 4, 1);
       
       Label nombreUsuario= new Label("Usuario: ");
       grid.add(nombreUsuario, 0 , 1);
       
       TextField ftUsuario = new TextField();
       grid.add(ftUsuario, 1, 1);
       
       Label contrasena = new Label("Contraseña:");
       grid.add(contrasena, 0, 2);
       
       PasswordField pwBox = new PasswordField();
       grid.add(pwBox, 1, 2);
       
       Button btnEntrar= new Button("Entrar");
       HBox hbbtn= new HBox(10);
       hbbtn.setAlignment(Pos.BOTTOM_CENTER);
       hbbtn.getChildren().add(btnEntrar);
       grid.add(hbbtn, 1, 4);
       
       /////METODO QUE HACE LA ACION DE ABRIR VENTANA DEL MENÚ
        btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
      
           String usuario="Avanzada";
           String contrasena ="12345";
           String user = ftUsuario.getText();
           String contra =pwBox.getText();
           
           if(usuario.equals(user) && contrasena.equals(contra)){
               System.out.print("Bienvenido Usuario");
               Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.setScene(new Scene(VBoxBuilder.create().
                 children(new Text("Tus datos son correctos"), new Button("Ok.")).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
                dialogStage.show();
               primaryStage.close();
             //
               dialogStage.close();
               Stage stage =  new  Stage (); 
               stage.setTitle("Nuestro VideoClub");
               VentanaPrincipal root = new VentanaPrincipal();
               root.setVisible(true);
               
               //this.setVisible(false);
               stage.setScene(new Scene(root,600,400));
               stage.show(); 
           }
           else{
               System.out.println("Erro,no puedesacceder");
           }
           
           ////
               }

          
        });
       
       
       //////fin prueba
       
       final Text actiontarget = new Text();
       grid.add(actiontarget, 1, 6);
       
       Scene scene = new Scene(grid,300, 310);
       primaryStage.setScene(scene);
       
       primaryStage.show();

    }


    public static void main(String[] args) {
        ConexionBD.conectar();
        launch(args);
    }
    
    
}
