package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;


public class Controller {

    @FXML
    private Button Graj;

    public void pressButton(ActionEvent event) throws IOException{
        Parent GrajView = FXMLLoader.load(getClass().getResource("fxml/GrajView.fxml"));
        Scene GrajViewScene = new Scene(GrajView);


        //This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(GrajViewScene);
        window.show();
    }

    public void pomocButton (ActionEvent event2) throws IOException {
        Parent Pomoc = FXMLLoader.load(getClass().getResource("fxml/pomoc.fxml"));
        Scene PomocViewScene = new Scene(Pomoc);

        Stage pomoc = (Stage) ((Node)event2.getSource()).getScene().getWindow();

        pomoc.setScene(PomocViewScene);
        pomoc.show();
    }

    public void wyjscieButton (ActionEvent event3 ) throws  IOException {
        exit(0);
    }



}
