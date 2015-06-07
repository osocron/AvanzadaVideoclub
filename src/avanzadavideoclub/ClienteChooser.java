package avanzadavideoclub;

import controlador.ControladorClientes;
import entidades.ClientesEntity;
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
public class ClienteChooser extends BorderPane{

    private TextField searchTextField;
    private ListView<ClientesEntity> listView;
    private Button botonAgregar;
    private HBox hBox;

    private Prestamo padre;
    private ObservableList<ClientesEntity> data = FXCollections.observableArrayList();

    public  ClienteChooser(Prestamo padre){
        this.padre = padre;
        prepararComponentes();
        createCustomCells();
        setPropiedadesDeBotones();
        addListenerToSearchTextField();
    }

    private void prepararComponentes() {
        List<ClientesEntity> listaClientes = ControladorClientes.getClientes();
        data.addAll(listaClientes);
        searchTextField = new TextField();
        searchTextField.setPromptText("Busca aquí el cliente que quieres encontrar");
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
            ListCell<ClientesEntity> cell = (ListCell<ClientesEntity>) getCellWithUpdateItemOverriden();
            return cell;
        });
    }

    /*
    *Método que devuelve una celda modificada para que despliege la descripción del producto que
     *contiene por medio del método setText(). También se asegura de que si la celda esta vacía
     * no despliege información alguna y que solo despliege la información del producto si el objeto
     * producto que contiene no es nulo.
    */
    private Cell<ClientesEntity> getCellWithUpdateItemOverriden() {
        return new ListCell<ClientesEntity>() {
            @Override
            protected void updateItem(ClientesEntity cliente, boolean bool) {
                super.updateItem(cliente, bool);
                if (bool) {
                    setText(null);
                    setGraphic(null);
                } else if (cliente != null) {
                    setText(cliente.getNombres());
                }
            }
        };
    }

    private void setPropiedadesDeBotones(){
        botonAgregar.setOnAction(event -> {
            padre.setClienteSeleccionado(listView.getSelectionModel().getSelectedItem());
            botonAgregar.getScene().getWindow().hide();
        });
    }

    private void addListenerToSearchTextField() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchClientes(oldValue, newValue);
        });
    }

    private ObservableList<ClientesEntity> searchClientes(String oldVal, String newVal) {
        if (oldVal != null && (newVal.length() < oldVal.length()))
            listView.setItems(data);
        String[] parts = newVal.toUpperCase().split(" ");
        ObservableList<ClientesEntity> subentries = FXCollections.observableArrayList();
        for (ClientesEntity entry : listView.getItems()) {
            boolean match = true;
            String entryText = entry.getNombres();
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
