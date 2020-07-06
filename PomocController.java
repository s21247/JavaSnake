package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PomocController {
    public void Wroc (ActionEvent event2) throws IOException {
        Parent Pomoc = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        Scene PomocViewScene = new Scene(Pomoc);

        Stage pomoc = (Stage) ((Node)event2.getSource()).getScene().getWindow();

        pomoc.setScene(PomocViewScene);
        pomoc.show();
    }

}
