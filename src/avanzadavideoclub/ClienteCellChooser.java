package avanzadavideoclub;

import entidades.ClientesEntity;
import entidades.RPeliculaCopiaEntity;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.stage.Stage;

/**
 * Created by osocron on 6/06/15.
 */
public class ClienteCellChooser<S, T>  extends TableCell<RPeliculaCopiaEntity, ClientesEntity> {

    private Label labelCliente;
    private ObservableList<RPeliculaCopiaEntity> data;
    private Prestamo padre;

    public ClienteCellChooser(ObservableList<RPeliculaCopiaEntity> data, Prestamo padre){
        super();
        this.data = data;
        this.padre = padre;
        if(labelCliente == null){
            crearTextField();
        }
        setGraphic(labelCliente);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(() -> labelCliente.requestFocus());
    }

    @Override
    protected void updateItem(ClientesEntity item, boolean empty){
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            if(item != null) {
                labelCliente.setText(item.getNombres());
                setGraphic(labelCliente);
                setText(item.getNombres());
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            }
        }
    }

    private void crearTextField() {
        this.labelCliente = new Label();
        this.labelCliente.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            stage.setTitle("Nuestros Clientes");
            ClienteChooser root = new ClienteChooser(this, padre);
            stage.setScene(new Scene(root,600,400));
            stage.show();
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

}
