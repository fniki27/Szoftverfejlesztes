package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.tinylog.Logger;
import statistics.TopWinners;
import statistics.repository.WinnerRepository;
import statistics.Winners;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class StatsController {

    TopWinners topWinners = new TopWinners();

    @FXML
    private Text topPlayersText;

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            Stage stage = (Stage) topPlayersText.getScene().getWindow();
            topPlayersText = (Text) stage.getScene().lookup("#topPlayersText");
            StringBuilder textToDisplay = new StringBuilder();
            try {
                Map<String, Long> list = topWinners.getTopWinnersFromFile();
                list.forEach((p,w) -> textToDisplay.append(p).append(": ").append(w).append("\n"));
            } catch (IOException e) {
                Logger.error("Unknown error");
            }
            topPlayersText.setText(textToDisplay.toString());
        });
    }

    public void writeWinnersToFile(String playerOne, String playerTwo, String winner) throws IOException{
        var repository = new WinnerRepository();
        var data = Winners.builder().name1(playerOne).name2(playerTwo).winner(winner).build();
        File file = new File("winners.json");
        if(file.exists())
            repository.loadFromFile(file);
        repository.add(data);
        repository.saveToFile(file);
    }

    @FXML
    public void switchToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
