package avanzadavideoclub;

import controlador.ControladorPeliculas;
import entidades.PeliculasEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

/**
 * Created by osocron on 6/06/15.
 */
public class PeliculaChooser extends BorderPane{

    private TextField searchTextField;
    private ListView<PeliculasEntity> listView;
    private Button botonAgregar;
    private HBox hBox;

    private Prestamo padre;
    private PeliculaCellChooser peliculaCell;
    private ObservableList<PeliculasEntity> data = FXCollections.observableArrayList();

    public  PeliculaChooser(Prestamo padre){
        this.padre = padre;
        prepararComponentes();
        createCustomCells();
        setPropiedadesDeBotones();
        addListenerToSearchTextField();
    }

    public  PeliculaChooser(PeliculaCellChooser peliculaCellChooser, Prestamo padre){
        this.peliculaCell = peliculaCellChooser;
        this.padre = padre;
        prepararComponentes();
        createCustomCells();
        setPropiedadesDeBotones();
        addListenerToSearchTextField();
    }

    private void prepararComponentes() {
        List<PeliculasEntity> listaPeliculas = ControladorPeliculas.getPeliculas();
        data.addAll(listaPeliculas);
        searchTextField = new TextField();
        searchTextField.setPromptText("Busca aquí la pelicula que quieres encontrar");
        botonAgregar = new Button("Agregar");
        listView = new ListView<>();
        listView.setItems(data);

        hBox = new HBox();
        hBox.getChildren().add(botonAgregar);
        hBox.setFillHeight(true);
        hBox.setHgrow(botonAgregar, Priority.ALWAYS);
        hBox.setPrefWidth(USE_COMPUTED_SIZE);
        hBox.setPrefHeight(USE_COMPUTED_SIZE);
        hBox.setMargin(botonAgregar, new Insets(5,5,5,5));
        hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        this.setTop(searchTextField);
        this.setMargin(searchTextField, new Insets(5,5,5,5));
        this.setCenter(listView);
        this.setMargin(listView, new Insets(0,5,0,5));
        this.setBottom(hBox);
    }

    private void createCustomCells() {
        listView.setCellFactory(param -> {
            ListCell<PeliculasEntity> cell = (ListCell<PeliculasEntity>) getCellWithUpdateItemOverriden();
            return cell;
        });
    }

    /*
    *Método que devuelve una celda modificada para que despliege la descripción del producto que
     *contiene por medio del método setText(). También se asegura de que si la celda esta vacía
     * no despliege información alguna y que solo despliege la información del producto si el objeto
     * producto que contiene no es nulo.
    */
    private Cell<PeliculasEntity> getCellWithUpdateItemOverriden() {
        return new ListCell<PeliculasEntity>() {
            @Override
            protected void updateItem(PeliculasEntity pelicula, boolean bool) {
                super.updateItem(pelicula, bool);
                if (bool) {
                    setText(null);
                    setGraphic(null);
                } else if (pelicula != null) {
                    setText(pelicula.getTitulo());
                }
            }
        };
    }

    private void setPropiedadesDeBotones(){
        botonAgregar.setOnAction(event -> {
            if(peliculaCell == null) {
                padre.setPeliculaSeleccionada(listView.getSelectionModel().getSelectedItem());
                botonAgregar.getScene().getWindow().hide();
                padre.agregarCopiaDePelicula();
            }
            else{
                peliculaCell.updateItem(listView.getSelectionModel().getSelectedItem(), false);
                padre.modificarPeliculaSeleccionada(listView.getSelectionModel().getSelectedItem());
            }
        });
    }

    private void addListenerToSearchTextField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(oldValue, newValue);
        });
    }

    private ObservableList<PeliculasEntity> searchProducts(String oldVal, String newVal) {
        if (oldVal != null && (newVal.length() < oldVal.length()))
            listView.setItems(data);
        String[] parts = newVal.toUpperCase().split(" ");
        ObservableList<PeliculasEntity> subentries = FXCollections.observableArrayList();
        for (PeliculasEntity entry : listView.getItems()) {
            boolean match = true;
            String entryText = entry.getTitulo();
            for (String part : parts) {
                if (!entryText.toUpperCase().contains(part)) {
                    match = false;
                    break;
                }
            }
            if (match)
                subentries.add(entry);
        }
        listView.setItems(subentries);
        return subentries;
    }
}
