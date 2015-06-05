package avanzadavideoclub;

import entidades.PeliculasEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noe on 05/06/15.
 * Integracion de Google search
 */
public class ButtonCell extends TableCell<PeliculasEntity, String> {

    final Button cellButton = new Button("Ver Poster");
    private ObservableList<PeliculasEntity> datosDePeliculas;

    ButtonCell(ObservableList<PeliculasEntity> listaPeliculas){
        super();

        this.datosDePeliculas = listaPeliculas;

        cellButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent t) {
                // do something when button clicked
                //...
                int index = getIndex();
                String query = getPeliculaData().get(index).getTitulo().replaceAll("\\s+", "");
                List<String> listaLinks = searchGoogle(query);
                if(listaLinks.size() > 0) {
                    String linkDelPoster = listaLinks.get(0);
                    abrirVentanaConPoster(linkDelPoster);
                }
            }
        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(String t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellButton);
        }
    }

    private List<String> searchGoogle(String query){
        String key="AIzaSyCIyb63WzNMVQK817YOnIG6Yk7vOupggYw";
        String qry=query;
        String searchId = "003925559047818067440:h5c9q2euouo";
        URL url = null;
        try {
            url = new URL(
                    "https://www.googleapis.com/customsearch/v1?key="+key+"&" +
                            "cx="+searchId+"&q="+qry+"&alt=json&searchType=image&fileType=jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String output;
        List<String> listResults = new ArrayList<>();
        System.out.println("Output from Server .... \n");
        try {
            while ((output = br.readLine()) != null) {

                if(output.contains("\"link\": \"")){
                    String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
                    listResults.add(link);
                    System.out.println(link);       //Will print the google search links
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.disconnect();
        return listResults;
    }

    public ObservableList<PeliculasEntity> getPeliculaData() {
        return datosDePeliculas;
    }

    private void abrirVentanaConPoster(String link){
        Image javafxImage = null;
        java.awt.Image awtPoster = obtenerPoster(link);
        try {
            javafxImage = createImage(awtPoster);
        } catch (IOException e) {
            e.printStackTrace();
        }
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView();
        imageView.setImage(javafxImage);
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.BLACK);
        root.getChildren().addAll(imageView);
        Stage stage = new Stage();
        stage.setTitle("ImageView");
        stage.setWidth(USE_COMPUTED_SIZE);
        stage.setHeight(USE_COMPUTED_SIZE);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private java.awt.Image obtenerPoster(String link){
        java.awt.Image image = null;
        try {
            URL url = new URL(link);
            image = ImageIO.read(url);
        } catch (IOException e) {
        }
        return image;
    }

    public javafx.scene.image.Image createImage(java.awt.Image image) throws IOException {
        if (!(image instanceof RenderedImage)) {
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            image = bufferedImage;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "jpg", out);
        out.flush();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }

}
