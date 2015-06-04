
package avanzadavideoclub;

import Entidades.PeliculasEntity;
import controlador.ControladorClientes;
import controlador.ControladorPeliculas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Peliculas extends BorderPane{
    
    private TableColumn codigo;
    private TableColumn titulo;
    private TableColumn año;
    private TableColumn minDuracion;
    private TableColumn sinopsis;
    private TableColumn genero;
    private TableColumn actores;
    private TableColumn director;

    private Text textocodigo;
    private TextField tfcodigo;
    private ObservableList<PeliculasEntity> data = FXCollections.observableArrayList();;
    private TableView miTabla;
    private ControladorPeliculas controladorPeliculas;


  public Peliculas(){
      controladorPeliculas = new ControladorPeliculas();
      ComponentesP();
      setPeliculasALaTabla();
      setTextFieldsYBoton();
  }
    
    public void ComponentesP(){
        miTabla = new TableView();

        miTabla.setEditable(true);
        
        codigo= new TableColumn("Codigo");
        titulo= new TableColumn("Titulo");
        año=new TableColumn("Año");
        minDuracion = new TableColumn("Duración(Min)");
        sinopsis = new TableColumn("Sinopsis");
        genero= new TableColumn("Genero");
        actores = new TableColumn("Actores");
        director = new TableColumn("Director");
        
        miTabla.getColumns().addAll(codigo,titulo,año,minDuracion,sinopsis,genero,actores,director);
        this.setCenter(miTabla);

    }

    public void setPeliculasALaTabla(){
        List<PeliculasEntity> list = controladorPeliculas.getPeliculas();
        data.addAll(list);
        List<TableColumn> listaColumnas = new ArrayList<>();
        listaColumnas.add(titulo);
        listaColumnas.add(año);
        listaColumnas.add(minDuracion);
        listaColumnas.add(sinopsis);
        listaColumnas.add(genero);
        listaColumnas.add(actores);
        listaColumnas.add(director);

        codigo.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity,String>("codigo")
        );
        titulo.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity,String>("titulo")
        );
        titulo.setCellFactory(TextFieldTableCell.forTableColumn());
        titulo.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>(){
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarTitulo(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(),event.getNewValue());
                    }
                }
        );
        año.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, Date>("anio")
        );

        minDuracion.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("duracion")
        );
        sinopsis.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("sinopsis")
        );
        genero.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("genero")
        );
        actores.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("actores")
        );
        director.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("director")
        );

        miTabla.setItems(data);

    }

    public void setTextFieldsYBoton(){

    }
    
    
}
