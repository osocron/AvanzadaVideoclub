
package avanzadavideoclub;

import entidades.PeliculasEntity;
import controlador.ControladorPeliculas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Peliculas extends BorderPane{
    
    private TableColumn codigo;
    private TableColumn titulo;
    private TableColumn año;
    private TableColumn minDuracion;
    private TableColumn sinopsis;
    private TableColumn genero;
    private TableColumn actores;
    private TableColumn director;
    private TableColumn poster;

    private Button nuevoButton;
    private Button eliminarButton;
    private TextField busqPelisTextField;

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox;
    private ObservableList<PeliculasEntity> data = FXCollections.observableArrayList();
    private TableView<PeliculasEntity> miTabla;
    private ControladorPeliculas controladorPeliculas;


  public Peliculas(){
      controladorPeliculas = new ControladorPeliculas();
      ComponentesP();
      setPeliculasALaTabla();
      setPropiedadesDeBotones();
      setPropiedadesDeBusqueda();
  }
    
    public void ComponentesP(){

        miTabla = new TableView();
        busqPelisTextField = new TextField();
        busqPelisTextField.setPromptText("Buca aquí la pelicula que desees consultar");
        miTabla.setEditable(true);
        
        codigo= new TableColumn("Codigo");
        titulo= new TableColumn("Titulo");
        año=new TableColumn("Año");
        año.setPrefWidth(150);
        minDuracion = new TableColumn("Duración(Min)");
        sinopsis = new TableColumn("Sinopsis");
        sinopsis.setPrefWidth(300);
        genero= new TableColumn("Genero");
        actores = new TableColumn("Actores");
        director = new TableColumn("Director");
        poster = new TableColumn("Poster");

        nuevoButton = new Button("Agregar Pelicula");
        eliminarButton = new Button("Eliminar");
        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox1.getChildren().addAll(eliminarButton);
        hBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        hBox1.setHgrow(nuevoButton,Priority.ALWAYS);
        hBox1.setPrefWidth(USE_COMPUTED_SIZE);
        hBox1.setPrefHeight(USE_COMPUTED_SIZE);

        hBox2.getChildren().addAll(nuevoButton);
        hBox2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        hBox2.setHgrow(nuevoButton,Priority.ALWAYS);
        hBox2.setPrefWidth(USE_COMPUTED_SIZE);
        hBox2.setPrefHeight(USE_COMPUTED_SIZE);

        hBox = new HBox();
        hBox.getChildren().addAll(hBox1,hBox2);
        hBox.setFillHeight(true);
        hBox.setHgrow(hBox1,Priority.ALWAYS);
        hBox.setHgrow(hBox2,Priority.ALWAYS);
        hBox.setPrefWidth(USE_COMPUTED_SIZE);
        hBox.setPrefHeight(USE_COMPUTED_SIZE);
        hBox.setMargin(hBox1, new Insets(5,5,5,5));
        hBox.setMargin(hBox2, new Insets(5,5,5,5));

        miTabla.getColumns().addAll(codigo,titulo,año,minDuracion,sinopsis,genero,actores,director,poster);

        this.setCenter(miTabla);
        this.setMargin(miTabla, new Insets(0,5,0,5));
        this.setBottom(hBox);
        this.setTop(busqPelisTextField);
        this.setMargin(busqPelisTextField, new Insets(5,5,5,5));
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

        codigo.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("codigo")
        );
        titulo.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity,String>("titulo")
        );
        titulo.setCellFactory(TextFieldTableCell.forTableColumn());
        titulo.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarTitulo(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );
        año.setCellValueFactory(
                new PropertyValueFactory<DatePickerCell, java.sql.Date>("anio")
        );

        año.setCellFactory(param -> {
            DatePickerCell datePickerCell = new DatePickerCell(data);
            return datePickerCell;
        });
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
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>() {
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
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>() {
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
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>() {
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
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarActores(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()).getCodigo(), event.getNewValue());
                    }
                }
        );
        director.setCellValueFactory(
                new PropertyValueFactory<PeliculasEntity, String>("director")
        );
        director.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<PeliculasEntity, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<PeliculasEntity, String> event) {
                        ControladorPeliculas.modificarDirector(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(), event.getNewValue());
                    }
                }
        );
        poster.setCellFactory(
                new PropertyValueFactory<PeliculasEntity, String>("poster")
        );
        poster.setCellFactory(param -> {
            ButtonCellPoster buttonCellPoster = new ButtonCellPoster(data);
            return buttonCellPoster;
        });

        miTabla.setItems(data);

    }

    public void setPropiedadesDeBotones(){
        nuevoButton.setOnMouseClicked(event -> {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date parsed = null;
            try {
                parsed = df.parse("01/01/0001");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sql = new java.sql.Date(parsed.getTime());
            PeliculasEntity pelicula =
                    ControladorPeliculas.crearPelicula("Nueva Pelicula", sql, "00:00", "Desconocido",
                            "Desconocido", "Desconocidos", "Desconocido");
            ControladorPeliculas.guardarPelicula(pelicula);
            data.add(pelicula);
            miTabla.setItems(data);
        });
        eliminarButton.setOnMouseClicked(event -> {
            PeliculasEntity pelicula = miTabla.getSelectionModel().getSelectedItem();
            data.remove(pelicula);
            miTabla.setItems(data);
            controladorPeliculas.eliminarPelicula(pelicula.getCodigo());
        });
    }

    private void setPropiedadesDeBusqueda() {
        busqPelisTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarPeliculas(oldValue, newValue);
        });
    }

    public void buscarPeliculas(String oldVal, String newVal){
        if (oldVal != null && (newVal.length() < oldVal.length())) {
            miTabla.setItems(data);
        }
        String[] parts = newVal.toUpperCase().split(" ");
        ObservableList<PeliculasEntity> subentries = FXCollections.observableArrayList();
        for (PeliculasEntity entry : miTabla.getItems()) {
            boolean match = true;
            String titulo = entry.getTitulo();
            String sinopsis = entry.getSinopsis();
            String genero = entry.getGenero();
            String actores = entry.getActores();
            String director = entry.getDirector();
            for ( String part: parts ) {
                if ( (!titulo.toUpperCase().contains(part)) &&
                        (!sinopsis.toUpperCase().contains(part)) &&
                        (!genero.toUpperCase().contains(part)) &&
                        (!actores.toUpperCase().contains(part)) &&
                        (!director.toUpperCase().contains(part))) {
                    match = false;
                    break;
                }
            }
            if ( match ) {
                subentries.add(entry);
            }
        }
        miTabla.setItems(subentries);
    }
    
}
