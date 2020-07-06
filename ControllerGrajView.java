package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerGrajView {

    public void GraLatwy(ActionEvent event) throws Exception {
        Parent AktualnaGra = FXMLLoader.load(getClass().getResource("fxml/AktualnaGra.fxml"));
        Scene GrajViewScene2 = new Scene(AktualnaGra);
        //This line gets the stage information
        Stage window2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //window2.setScene(GrajViewScene2);
        //window2.show();
        Snake2 Snake2= new Snake2();
        Snake2.start(window2,5);
        //PlayMusic2 haha = new PlayMusic2();
        //haha.start();
    }
    public void GraTrudny (ActionEvent event) throws Exception {
        Parent AktualnaGra2 = FXMLLoader.load(getClass().getResource("fxml/AktualnaGra.fxml"));
        Scene GrajViewScene2 = new Scene(AktualnaGra2);
        //This line gets the stage information
        Stage window2 = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //window2.setScene(GrajViewScene2);
        //window2.show();
        Snake2 Snake = new Snake2();
        Snake.start(window2,25);
        //PlayMusic haha2 = new PlayMusic();
        //haha2.start();

    }

}
