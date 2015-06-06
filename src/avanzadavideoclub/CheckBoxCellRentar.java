package avanzadavideoclub;

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
        int index = getIndex();
        final int[] isSelected = new int[1];
        if(index == -1){
            index = 0;
        }
        if((getListaDeCopiasDePeliculas().get(index).getRentada() == 0)
                && getListaDeCopiasDePeliculas().get(index) != null){
            checkBox.setSelected(false);
        }else if (getListaDeCopiasDePeliculas().get(index) != null){
            checkBox.setSelected(true);
        }
        final int finalIndex = index;
        checkBox.setOnAction(t -> {
            if(checkBox.isSelected()){
                isSelected[0] = 1;
                padre.seleccionarCliente();
            }else{
                isSelected[0] = 0;
            }
            if(getListaDeCopiasDePeliculas() != null){
                getListaDeCopiasDePeliculas().get(finalIndex).setRentada(isSelected[0]);
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
