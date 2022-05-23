package gui;


import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.tinylog.Logger;

public class PlayerNamesController {

    @FXML
    private TextField userName1;
    @FXML
    private TextField userName2;


    @FXML
    public void switchToGame(ActionEvent event) throws IOException {
        if (canStart()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/game.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<GameController>getController().setUsername(userName1.getText(),userName2.getText());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @FXML
    public void switchToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public boolean canStart() throws NullPointerException {
        String user1 = userName1.getText();
        String user2 = userName2.getText();
        if (user1.isEmpty() || user2.isEmpty()) {
            Logger.error("Names are required!");
            return false;
        } else if (user1.equals(user2)) {
            Logger.error("The names can't be the same!");
            return false;
        }
        return true;
    }

}
