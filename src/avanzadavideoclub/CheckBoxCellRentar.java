package avanzadavideoclub;

import controlador.ControladorRPeliculaCopia;
import entidades.RPeliculaCopiaEntity;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;

/**
 * Created by osocron on 6/06/15.
 */
public class CheckBoxCellRentar <S, T> extends TableCell<RPeliculaCopiaEntity, Integer> {
    private CheckBox checkBox;
    private Prestamo padre;

    private ObservableList<RPeliculaCopiaEntity> listaDeCopiasDePeliculas;
    public CheckBoxCellRentar(ObservableList<RPeliculaCopiaEntity> listaDeCopiasDePeliculas, Prestamo padre){
        super();
        this.listaDeCopiasDePeliculas = listaDeCopiasDePeliculas;
        this.padre = padre;
        if(checkBox == null){
            createCheckBox();
        }
        setGraphic(checkBox);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Platform.runLater(()->checkBox.requestFocus());
    }

    @Override
    public void updateItem(Integer esRentada, boolean empty){
        super.updateItem(esRentada,empty);
        boolean rentada = false;
        if(esRentada != null) {
            if (esRentada == 1) {
                rentada = true;
            } else {
                rentada = false;
            }
        }
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            checkBox.setSelected(rentada);
            setGraphic(checkBox);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }

    private void createCheckBox() {
        this.checkBox = new CheckBox();
        this.checkBox.setSelected(false);

        checkBox.setOnAction(t -> {
            int isSelected;
            int index = getIndex();
            if(checkBox.isSelected()){
                isSelected = 1;
                padre.getMiTabla().getSelectionModel().select(index);
                padre.seleccionarCliente();
            }else{
                isSelected = 0;
            }
            if(getListaDeCopiasDePeliculas() != null){
                getListaDeCopiasDePeliculas().get(index).setRentada(isSelected);
                ControladorRPeliculaCopia.modificarEsRentada(index,isSelected);
            }
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public ObservableList<RPeliculaCopiaEntity> getListaDeCopiasDePeliculas() {
        return listaDeCopiasDePeliculas;
    }

    public void setListaDeCopiasDePeliculas(ObservableList<RPeliculaCopiaEntity> listaDeCopiasDePeliculas) {
        this.listaDeCopiasDePeliculas = listaDeCopiasDePeliculas;
    }


}
