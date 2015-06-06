package avanzadavideoclub;

import entidades.RPeliculaCopiaEntity;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by osocron on 6/06/15.
 */
public class DatePickerFechaEntrega <S, T> extends TableCell<RPeliculaCopiaEntity, Date> {

    private DatePicker datePicker;
    private ObservableList<RPeliculaCopiaEntity> datosDeCopiasDePeliculas;

    public DatePickerFechaEntrega(ObservableList<RPeliculaCopiaEntity> listaDeCopiasDePeliculas) {

        super();

        this.datosDeCopiasDePeliculas = listaDeCopiasDePeliculas;

        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Platform.runLater(() -> datePicker.requestFocus());
    }

    @Override
    public void updateItem(Date item, boolean empty) {

        super.updateItem(item, empty);

        SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");

        if (null == this.datePicker) {
            System.out.println("datePicker is NULL");
        }

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if(item != null) {
                if (isEditing()) {
                    setContentDisplay(ContentDisplay.TEXT_ONLY);

                } else {
                    setDatepikerDate(smp.format(item));
                    setText(smp.format(item));
                    setGraphic(this.datePicker);
                    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                }
                this.datePicker.setDisable(false);
            }else{
                this.datePicker.setDisable(true);
            }
        }
    }

    private void setDatepikerDate(String dateAsStr) {

        LocalDate ld = null;
        int jour, mois, annee;

        jour = mois = annee = 0;
        try {
            jour = Integer.parseInt(dateAsStr.substring(0, 2));
            mois = Integer.parseInt(dateAsStr.substring(3, 5));
            annee = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
        } catch (NumberFormatException e) {
            System.out.println("setDatepikerDate / unexpected error " + e);
        }

        ld = LocalDate.of(annee, mois, jour);
        datePicker.setValue(ld);
    }

    private void createDatePicker() {
        this.datePicker = new DatePicker();
        datePicker.setPromptText("dd/mm/aaaa");
        datePicker.setEditable(true);

        datePicker.setOnAction(t -> {
            LocalDate date = datePicker.getValue();
            int index = getIndex();

            SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
            cal.set(Calendar.MONTH, date.getMonthValue() - 1);
            cal.set(Calendar.YEAR, date.getYear());

            setText(smp.format(cal.getTime()));
            java.util.Date simpleDate = cal.getTime();
            java.sql.Date sqlDate = new java.sql.Date(simpleDate.getTime());
            commitEdit(sqlDate);

            if (null != getPeliculaData()) {
                getPeliculaData().get(index).setFechaEntrega(sqlDate);
            }
        });

        setAlignment(Pos.CENTER);
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

    public ObservableList<RPeliculaCopiaEntity> getPeliculaData() {
        return datosDeCopiasDePeliculas;
    }

    public void setPeliculaData(ObservableList<RPeliculaCopiaEntity> pelicaulaData) {
        this.datosDeCopiasDePeliculas = pelicaulaData;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

}
