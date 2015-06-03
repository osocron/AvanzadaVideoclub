
package avanzadavideoclub;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Peliculas extends BorderPane{
    //private SplitPane unSplit;
    //---private TableView mitabla= new TableView();
    
    private TableColumn codigo;
    private TableColumn titulo;
    private TableColumn año;
    private TableColumn minDuracion;
    private TableColumn sinopsis;
    private TableColumn genero;
   ////////////////////////
    private Text textocodigo;
    private TextField tfcodigo;
    
  public Peliculas(){
      ComponentesP();
  }
    
    public void ComponentesP(){
        HBox contenedortabla = new HBox();
        TableView mitabla = new TableView();
        
        codigo= new TableColumn("Codigo");
        titulo= new TableColumn("Titulo");
        año=new TableColumn("Año");
        minDuracion = new TableColumn("Duración(Min)");
        sinopsis = new TableColumn("Sinopsis");
        genero= new TableColumn("Genero");
        
        
        mitabla.getColumns().addAll(codigo,titulo,año,minDuracion,sinopsis,genero);
        this.setCenter(mitabla);
        
        
        
        //TableView= new TableView(mitabla, 600, 250);

        
    }
    
    
}

/*
////////////
ESTE ES UN RESPALDO DE LA TABLADE ARRIBA

package avanzadavideoclub;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class Peliculas extends BorderPane{
    //private SplitPane unSplit;
    //---private TableView mitabla= new TableView();
    private TableColumn codigo;
    private TableColumn titulo;
     private TableColumn año;
     private TableColumn minDuracion;
     private TableColumn sinopsis;
    private TableColumn genero;
    
   
    
  public Peliculas(){
      ComponentesP();
  }
    
    public void ComponentesP(){
        TableView mitabla = new TableView();
        
        codigo= new TableColumn("Codigo");
        titulo= new TableColumn("Titulo");
        año=new TableColumn("Año");
        minDuracion = new TableColumn("Duración(Min)");
        sinopsis = new TableColumn("Sinopsis");
        genero= new TableColumn("Genero");
        
        
        mitabla.getColumns().addAll(codigo,titulo,año,minDuracion,sinopsis,genero);
        this.setCenter(mitabla);
        
        
        
        //TableView= new TableView(mitabla, 600, 250);

        
    }
    
    
}

*/