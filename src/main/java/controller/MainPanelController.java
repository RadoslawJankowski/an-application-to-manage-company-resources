package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {


    public void employeeWindowLoader(ActionEvent event) throws IOException {

        AnchorPane gameViewParent = FXMLLoader.load(getClass().getResource("/fxmlFiles/employeeFXML/EmployeeMainPanel.fxml"));
        Scene gameSceneView = new Scene(gameViewParent);
        Stage gameWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

        gameWindow.setScene(gameSceneView);
        gameWindow.show();
    }

    public void initialize(URL location, ResourceBundle resources) {

    }
}
