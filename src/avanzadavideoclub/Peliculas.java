
package avanzadavideoclub;

import Entidades.PeliculasEntity;
import controlador.ConexionBD;
import controlador.ControladorClientes;
import controlador.ControladorPeliculas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        listaColumnas.add(minDuracion);
        listaColumnas.add(sinopsis);
        listaColumnas.add(genero);
        listaColumnas.add(actores);
        listaColumnas.add(director);
        listaColumnas.forEach(columna -> {
            columna.setCellFactory(TextFieldTableCell.forTableColumn());
        });

        miTabla.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    java.util.Date parsed = null;
                    try {
                        parsed = df.parse("01/01/0001");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    PeliculasEntity pelicula =
                            ControladorPeliculas.crearPelicula("", sql, "", "", "", "", "");
                    ControladorPeliculas.guardarPelicula(pelicula);
                    data.add(pelicula);
                }
            });
            return row;
        });

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
                        String texto;


                        ControladorPeliculas.modificarTitulo(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(),event.getNewValue());
                    }
                }
        );
        año.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, java.sql.Date>("anio")
        );
        StringConverter<java.sql.Date> stringConverter = new StringConverter<java.sql.Date>() {
            @Override
            public String toString(java.sql.Date object) {
                try {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    String text = df.format(object);
                    return text;
                }catch (Exception e){
                    String text = "00/00/0001";
                    return text;
                }
            }

            @Override
            public java.sql.Date fromString(String string) {
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                java.util.Date parsed = null;
                try {
                    parsed = df.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                return sql;
            }
        };
        año.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        año.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, Date>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, Date> event) {
                        ControladorPeliculas.modificarAnio(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );

        minDuracion.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("duracion")
        );
        minDuracion.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>(){
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarDuracion(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );
        sinopsis.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("sinopsis")
        );
        sinopsis.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>(){
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarSinopsis(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );
        genero.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("genero")
        );
        genero.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>(){
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarGenero(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );
        actores.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("actores")
        );
        actores.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>(){
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarActores(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );
        director.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("director")
        );
        director.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>(){
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarDirector(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );

        miTabla.setItems(data);

    }

    public void setTextFieldsYBoton(){

    }


    
}
