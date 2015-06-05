/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avanzadavideoclub;

import entidades.ClientesEntity;
import controlador.ControladorClientes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clientes extends BorderPane{

    private TableColumn num;
    private TableColumn nombre;
    private TableColumn apellido;
    private TableColumn direccion;
    private TableColumn telefono;
    private TableColumn email;
    private TableColumn ifeOrc;
    private TableView<ClientesEntity> miTabla;

    private Button nuevoButton;
    private Button eliminarButton;
    private TextField busqClientesTextField;

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox;

    private ObservableList<ClientesEntity> data = FXCollections.observableArrayList();
    
    public Clientes(){
        ComponentesCliente();
        setClientesALaTabla();
        setPropiedadesDeBotones();
        setPropiedadesDeBusqueda();
    }

    public void ComponentesCliente(){
        miTabla = new TableView();
        busqClientesTextField = new TextField();
        busqClientesTextField.setPromptText("Buca aquí la pelicula que desees consultar");
        miTabla.setEditable(true);
        nuevoButton = new Button("Agregar Cliente");
        eliminarButton = new Button("Eliminar");

        num =new TableColumn("Num Cliente");
        nombre = new TableColumn("Nombre");
        apellido = new TableColumn("Apellido");
        direccion = new TableColumn("Doreccion");
        telefono = new TableColumn("Telefono");
        email = new TableColumn("E-mail");
        ifeOrc = new TableColumn("Numero de IFE");

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

        miTabla.getColumns().addAll(num,nombre,apellido,direccion,telefono,email,ifeOrc);
        this.setCenter(miTabla);
        this.setMargin(miTabla, new Insets(0,5,0,5));
        this.setBottom(hBox);
        this.setTop(busqClientesTextField);
        this.setMargin(busqClientesTextField, new Insets(5,5,5,5));

    }

    private void setClientesALaTabla() {
        List<ClientesEntity> listaClientes = ControladorClientes.getClientes();
        data.addAll(listaClientes);

        List<TableColumn> listaColumnas = new ArrayList<>();
        listaColumnas.add(nombre);
        listaColumnas.add(apellido);
        listaColumnas.add(direccion);
        listaColumnas.add(telefono);
        listaColumnas.add(email);
        listaColumnas.add(ifeOrc);
        listaColumnas.forEach(columna -> {
            columna.setCellFactory(TextFieldTableCell.forTableColumn());
        });

        num.setCellValueFactory(
                new PropertyValueFactory<ClientesEntity, String>("numCliente")
        );

        nombre.setCellValueFactory(
                new PropertyValueFactory<ClientesEntity, String>("nombres")
        );
        nombre.setCellFactory(TextFieldTableCell.forTableColumn());
        nombre.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ClientesEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ClientesEntity, String> event) {
                ControladorClientes.modificarNombre(event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                ).getNumCliente(), event.getNewValue());
            }
        });
        apellido.setCellValueFactory(
                new PropertyValueFactory<ClientesEntity, String>("apellidos")
        );
        apellido.setCellFactory(TextFieldTableCell.forTableColumn());
        apellido.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ClientesEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ClientesEntity, String> event) {
                ControladorClientes.modificarApellidos(event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                ).getNumCliente(), event.getNewValue());
            }
        });
        direccion.setCellValueFactory(
                new PropertyValueFactory<ClientesEntity, String>("direccion")
        );
        direccion.setCellFactory(TextFieldTableCell.forTableColumn());
        direccion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ClientesEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ClientesEntity, String> event) {
                ControladorClientes.modificarDireccion(event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                ).getNumCliente(), event.getNewValue());
            }
        });
        telefono.setCellValueFactory(
                new PropertyValueFactory<ClientesEntity, String>("telefono")
        );
        telefono.setCellFactory(TextFieldTableCell.forTableColumn());
        direccion.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ClientesEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ClientesEntity, String> event) {
                ControladorClientes.modificarTelefono(event.getTableView().getItems().get(
                        event.getTablePosition().getRow()
                ).getNumCliente(), event.getNewValue());
            }
        });
        email.setCellValueFactory(
                new PropertyValueFactory<ClientesEntity, String>("correo")
        );
        email.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ClientesEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ClientesEntity, String> event) {
                String texto = event.getNewValue();
                boolean isTextOnly;
                Pattern pattern = Pattern.compile("^[A-Z0-9_%+-]+@");
                Matcher matcher = pattern.matcher(texto);
                isTextOnly = matcher.matches();
                if(!isTextOnly){
                    JOptionPane.showMessageDialog(null,"Funciona","Titulo",JOptionPane.OK_OPTION);
                }else {
                    ControladorClientes.modificarEmail(event.getTableView().getItems().get(
                            event.getTablePosition().getRow()
                    ).getNumCliente(), event.getNewValue());
                }
            }
        });
        ifeOrc.setCellValueFactory(
                new PropertyValueFactory<ClientesEntity, String>("ifEorc")
        );
        ifeOrc.setCellFactory(TextFieldTableCell.forTableColumn());
        ifeOrc.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ClientesEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ClientesEntity, String> event) {
                String texto1 = event.getNewValue();
                boolean isTextOnly;
                Pattern pattern = Pattern.compile("^[\\p{L} -]+$");
                Matcher matcher = pattern.matcher(texto1);
                isTextOnly = matcher.matches();
                if(!isTextOnly){
                    JOptionPane.showMessageDialog(null,"Funciona","Titulo",JOptionPane.OK_OPTION);
                }else {
                    ControladorClientes.modificarIfeOrc(event.getTableView().getItems().get(
                            event.getTablePosition().getRow()
                    ).getNumCliente(), Long.parseLong(event.getNewValue()));
                }
            }
        });

        miTabla.setItems(data);
    }

    public void setPropiedadesDeBotones(){
        nuevoButton.setOnMouseClicked(event -> {
            ClientesEntity cliente =
                    ControladorClientes.crearCliente("Nuevo Cliente", "Nuevo cliente", "Direccion Desconocida",
                            "Desconocido", "Desconocido", Long.parseLong("0"));
            ControladorClientes.guardarCliente(cliente);
            data.add(cliente);
            miTabla.setItems(data);
        });
        eliminarButton.setOnMouseClicked(event -> {
            ClientesEntity cliente = miTabla.getSelectionModel().getSelectedItem();
            data.remove(cliente);
            miTabla.setItems(data);
            ControladorClientes.eliminarCliente(cliente.getNumCliente());
        });
    }

    private void setPropiedadesDeBusqueda() {
        busqClientesTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            buscarPeliculas(oldValue, newValue);
        });
    }

    public void buscarPeliculas(String oldVal, String newVal){
        if (oldVal != null && (newVal.length() < oldVal.length())) {
            miTabla.setItems(data);
        }
        String[] parts = newVal.toUpperCase().split(" ");
        ObservableList<ClientesEntity> subentries = FXCollections.observableArrayList();
        for (ClientesEntity entry : miTabla.getItems()) {
            boolean match = true;
            String titulo = entry.getNombres();
            String sinopsis = entry.getDireccion();
            String genero = entry.getTelefono();
            String actores = entry.getCorreo();
            String director = String.valueOf(entry.getIfEocr());
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
