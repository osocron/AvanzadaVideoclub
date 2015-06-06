package avanzadavideoclub;

import controlador.ControladorRPeliculaCopia;
import entidades.ClientesEntity;
import entidades.PeliculasEntity;
import entidades.RPeliculaCopiaEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.List;

/**
 * Created by osocron on 5/06/15.
 */
public class Prestamo extends BorderPane {

    public Prestamo(){
        setComponentesCopiasPelicula();
        setCopiasALaTabla();
        setPropiedadesDeBotones();
    }

    private TableColumn num;
    private TableColumn pelicula;
    private TableColumn rentada;
    private TableColumn cliente;
    private TableColumn fechaRenta;
    private TableColumn fechaEntrega;
    private TableView<RPeliculaCopiaEntity> miTabla;

    private Button nuevoButton;
    private Button eliminarButton;
    private TextField busqCopiasTextField;

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox;

    private ObservableList<RPeliculaCopiaEntity> data = FXCollections.observableArrayList();
    private PeliculasEntity peliculaSeleccionada;
    private ClientesEntity clienteSeleccionado;

    public void setComponentesCopiasPelicula(){
        miTabla = new TableView();
        busqCopiasTextField = new TextField();
        busqCopiasTextField.setPromptText("Buca aquí la copia de pelicula que desees consultar");
        miTabla.setEditable(true);
        nuevoButton = new Button("Agregar Copia de Pelicula");
        eliminarButton = new Button("Eliminar");

        num =new TableColumn("Codigo de Copia");
        pelicula = new TableColumn("Pelicula");
        rentada = new TableColumn("Rentar");
        cliente = new TableColumn("Cliente");
        fechaRenta = new TableColumn("Fecha de Renta");
        fechaEntrega = new TableColumn("Fecha de Entrega");

        hBox1 = new HBox();
        hBox2 = new HBox();
        hBox1.getChildren().addAll(eliminarButton);
        hBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        hBox1.setHgrow(nuevoButton, Priority.ALWAYS);
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

        miTabla.getColumns().addAll(num, pelicula, rentada, cliente, fechaRenta, fechaEntrega);
        this.setCenter(miTabla);
        this.setMargin(miTabla, new Insets(0,5,0,5));
        this.setBottom(hBox);
        this.setTop(busqCopiasTextField);
        this.setMargin(busqCopiasTextField, new Insets(5,5,5,5));

    }

    private void setCopiasALaTabla(){
        List<RPeliculaCopiaEntity> listaDeCopias = ControladorRPeliculaCopia.getCopiasDePeliculas();
        data.addAll(listaDeCopias);
        num.setCellValueFactory(
                new PropertyValueFactory<RPeliculaCopiaEntity, String>("numCopia")
        );
        pelicula.setCellValueFactory(
                new PropertyValueFactory<RPeliculaCopiaEntity, String>("peliculasByCodigoPelicula")
        );
        pelicula.setCellFactory(column -> new TableCell<RPeliculaCopiaEntity, PeliculasEntity>(){
          @Override
        protected void updateItem(PeliculasEntity item, boolean empty){
              if (item == null || empty) {
                  setText(null);
                  setStyle("");
              } else {
                  setText(item.getTitulo());
              }
          }
        });
        rentada.setCellValueFactory(
                new PropertyValueFactory<CheckBoxCellRentar, Integer>("rentada")
        );
        rentada.setCellFactory(param -> {
            CheckBoxCellRentar checkBoxCellRentar = new CheckBoxCellRentar(data,this);
            return checkBoxCellRentar;
        });
        cliente.setCellValueFactory(
                new PropertyValueFactory<RPeliculaCopiaEntity, String>("clientesByNumCliente")
        );
        cliente.setCellFactory(column -> new TableCell<RPeliculaCopiaEntity, ClientesEntity>(){
            @Override
            protected void updateItem(ClientesEntity item, boolean empty){
                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.getNombres());
                }
            }
        });
        fechaRenta.setCellValueFactory(
                new PropertyValueFactory<DatePickerFechaRenta, Date>("fechaRenta")
        );
        fechaRenta.setCellFactory(param -> {
            DatePickerFechaRenta datePickerFechaRenta = new DatePickerFechaRenta(data);
            return datePickerFechaRenta;
        });
        fechaEntrega.setCellValueFactory(
                new PropertyValueFactory<DatePickerFechaEntrega, Date>("fechaEntrega")
        );
        fechaEntrega.setCellFactory(param -> {
            DatePickerFechaEntrega datePickerFechaEntrega = new DatePickerFechaEntrega(data);
            return datePickerFechaEntrega;
        });
        miTabla.setItems(data);
    }

    public void setPropiedadesDeBotones(){
        nuevoButton.setOnMouseClicked(event -> {
            seleccionarPelicula();
        });
        eliminarButton.setOnMouseClicked(event -> {
            RPeliculaCopiaEntity copiaDePelicula = miTabla.getSelectionModel().getSelectedItem();
            data.remove(pelicula);
            miTabla.setItems(data);
            ControladorRPeliculaCopia.eliminarCopiaDePelicula(copiaDePelicula.getNumCopia());
        });
    }

    private void seleccionarPelicula(){
        Stage stage = new Stage();
        stage.setTitle("Nuestras Peliculas");
        PeliculaChooser root = new PeliculaChooser(this);
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public void seleccionarCliente(){
        Stage stage = new Stage();
        stage.setTitle("Nuestros Clientes");
        ClienteChooser root = new ClienteChooser(this);
        stage.setScene(new Scene(root,600,400));
        stage.show();
    }

    public void setPeliculaSeleccionada(PeliculasEntity peliculaSeleccionada){
        this.peliculaSeleccionada = peliculaSeleccionada;
    }

    public void setClienteSeleccionado(ClientesEntity clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
        ControladorRPeliculaCopia.modificarCliente(miTabla.getSelectionModel().getSelectedIndex(), clienteSeleccionado);
    }

    public void agregarCopiaDePelicula() {
        RPeliculaCopiaEntity copiaDePelicula = ControladorRPeliculaCopia.
                crearCopiaPelicula(0, null, null, null, peliculaSeleccionada);
        ControladorRPeliculaCopia.guardarCopiaDePelicula(copiaDePelicula);
        data.add(copiaDePelicula);
        miTabla.setItems(data);
    }

    public TableView<RPeliculaCopiaEntity> getMiTabla() {
        return miTabla;
    }

    public void setMiTabla(TableView<RPeliculaCopiaEntity> miTabla) {
        this.miTabla = miTabla;
    }
}
