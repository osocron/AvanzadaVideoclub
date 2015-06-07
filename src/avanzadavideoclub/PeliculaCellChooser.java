package avanzadavideoclub;

import entidades.PeliculasEntity;
import entidades.RPeliculaCopiaEntity;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by osocron on 6/06/15.
 */
public class PeliculaCellChooser<S, T>  extends TableCell<RPeliculaCopiaEntity, PeliculasEntity> {

    private Label labelPelicula;
    private ObservableList<RPeliculaCopiaEntity> data;
    private Prestamo padre;

    public PeliculaCellChooser(ObservableList<RPeliculaCopiaEntity> data, Prestamo padre){
        super();
        this.data = data;
        this.padre = padre;
        if(labelPelicula == null){
            crearTextField();
        }
        setGraphic(labelPelicula);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(() -> labelPelicula.requestFocus());
    }

    @Override
    protected void updateItem(PeliculasEntity item, boolean empty){
        if(item == null || empty) {
            setText(null);
            setStyle("");
        } else {
            labelPelicula.setText(item.getTitulo());
            setGraphic(labelPelicula);
            setText(item.getTitulo());
        }
    }

    private void crearTextField() {
        this.labelPelicula = new Label();
        this.labelPelicula.setOnMouseClicked(event -> {
            Stage stage = new Stage();
            stage.setTitle("Nuestras Peliculas");
            PeliculaChooser root = new PeliculaChooser(this,padre);
            stage.setScene(new Scene(root, 600, 400));
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
