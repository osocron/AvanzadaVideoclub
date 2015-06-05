package avanzadavideoclub;

import entidades.ClientesEntity;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Created by osocron on 5/06/15.
 */
public class Prestamo extends BorderPane {

    public Prestamo(){

    }

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

}
