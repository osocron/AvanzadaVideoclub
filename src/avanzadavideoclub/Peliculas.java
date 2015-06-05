
package avanzadavideoclub;

import Entidades.PeliculasEntity;
import com.google.gson.Gson;
import controlador.ControladorPeliculas;
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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private Button nuevoButton;
    private Button eliminarButton;
    private TextField busqPelisTextField;

    private HBox hBox1;
    private HBox hBox2;
    private HBox hBox;
    private ObservableList<PeliculasEntity> data = FXCollections.observableArrayList();;
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

        miTabla.getColumns().addAll(codigo,titulo,año,minDuracion,sinopsis,genero,actores,director);

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

        miTabla.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 5) {
                    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    java.util.Date parsed = null;
                    try {
                        parsed = df.parse("12/12/2014");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    java.sql.Date sql = new java.sql.Date(parsed.getTime());
                    PeliculasEntity pelicula =
                            ControladorPeliculas.crearPelicula("Nueva Pelicula", sql, "00:00", "Desconocido",
                                    "Desconocido", "Desconocidos", "Desconocido");
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
                        ControladorPeliculas.modificarTitulo(event.getTableView().getItems().get(
                                event.getTablePosition().getRow()
                        ).getCodigo(),event.getNewValue());
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
                                event.getTablePosition().getRow()).getCodigo(), event.getNewValue());
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
                    ControladorPeliculas.crearPelicula("", sql, "", "", "", "", "");
            ControladorPeliculas.guardarPelicula(pelicula);
            data.add(pelicula);
        });
        eliminarButton.setOnMouseClicked(event -> {
            PeliculasEntity pelicula = miTabla.getSelectionModel().getSelectedItem();
            data.remove(pelicula);
            miTabla.setItems(data);
            controladorPeliculas.eliminarPelicula(pelicula.getCodigo());
        });
    }

    private void setPropiedadesDeBusqueda(){
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

    private void searchGoogle(){
        String search_string = "nachos";
        String query = "https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + search_string;

        //Now do a http GET

        HttpClient client = new DefaultHttpClient();
        try
        {
            HttpGet request = new HttpGet(query);

            HttpResponse http_response = client.execute(request);
            HttpEntity entity = http_response.getEntity();

            if (entity != null)
            {
                InputStream instream = entity.getContent();
                //convertInputStreamToString does exactly what it says.. its trivial so no need to show the code
                String response = convertInputStreamToString(instream);
                //JSONObject is provided at the link above
                Gson gson = new Gson();

                instream.close();
            }
        }
        catch (Exception ex)
        {
            client.getConnectionManager().shutdown();
        }


        //The parseJson function is where you can get the relevant data out of the results.  The list of valid keys are listed in the api documentation I linked above.  Here is the code for parseJson:

        JSONObject response_data = JsonObj.getJSONObject("responseData");
        JSONArray results = response_data.getJSONArray("results");

        for (int i = 0; i < results.length(); i++)
        {
            JSONObject result = (JSONObject)results.get(i);
            //now you can grab data from the result using:
            //result.getInt(key);
            //result.getString(key);
            //etc
        }
    
}
