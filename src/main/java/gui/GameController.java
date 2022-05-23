package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import game.FourColorsGame;
import game.Player;
import org.tinylog.Logger;


public class GameController {

    private FourColorsGame gameState;

    private Player player;

    private boolean squareIsSelected = false;;


    @FXML
    private Label redLabel;

    @FXML
    private Label blueLabel;

    @FXML
    private Label greenLabel;

    @FXML
    private Label yellowLabel;


    @FXML
    private Label currentPlayerLabel;

    @FXML
    private Label firstPlayer;

    @FXML
    private Label secondPlayer;

    @FXML
    private String username;

    @FXML
    private String username2;

    @FXML
    private GridPane board;

    @FXML
    private StackPane pressedSquare;

    public void setUsername(String username, String username2){
        this.username = username;
        this.username2 = username2;
        currentPlayerLabel.setText(this.username);
        firstPlayer.setText(this.username + "'s disks: ");
        secondPlayer.setText(this.username2 + "'s disks: ");
    }

    @FXML
    private void setTurn(Player player){
        if (player == Player.ONE){
            currentPlayerLabel.setText(username);
        } else if (player == Player.TWO) {
            currentPlayerLabel.setText(username2);
        }

    }

    @FXML
    private void initialize() {
        gameState = new FourColorsGame();
        player = Player.ONE;
        populateGrid();
        registerKeyEventHandler();
    }

    private void populateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                var square = createSquare();
                board.add(square, j, i);
                gameState.setEmptySquare(i,j);
            }
        }
    }

    private StackPane createSquare() {
        var square = new StackPane();
        square.getStyleClass().add("square");
        var piece = new Circle(60);
        piece.setFill(Color.TRANSPARENT);
        square.getChildren().add(piece);
        square.setOnMouseClicked(this::handleMouseClick);
        return square;
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        if(!squareIsSelected) {
            pressedSquare = square;
        }
        var row = GridPane.getRowIndex(pressedSquare);
        var col = GridPane.getColumnIndex(pressedSquare);
        var disk = (Circle) pressedSquare.getChildren().get(0);
        if (!gameState.isGameDraw() && !gameState.isGameOver()) {
            if (gameState.isSquareEmpty(row, col)) {
                squareIsSelected = true;

                if (player == Player.ONE) {
                    if (disk.getFill().equals(Color.TRANSPARENT)) {
                        disk.setFill(Color.RED);
                    } else if (disk.getFill().equals(Color.RED)) {
                        disk.setFill(Color.AQUA);
                    } else {
                        disk.setFill(Color.RED);
                    }
                }

                if(player == Player.TWO){
                    if(disk.getFill().equals(Color.TRANSPARENT)) {
                        disk.setFill(Color.GREEN);
                    } else if (disk.getFill().equals(Color.GREEN)) {
                        disk.setFill(Color.rgb(238,203,26));
                    } else {
                        disk.setFill(Color.GREEN);
                    }
                }

                Logger.info("Clicked on square {},{}", row, col);
                Logger.info("Press ENTER to set the color of the disk");
            } else {
                Logger.error("This square is not empty!");
                Logger.info("Please select an empty square.");
            }
        }
    }

    private void registerKeyEventHandler() {
        Platform.runLater(() -> board.getScene().setOnKeyPressed(
                keyEvent -> {
                    if(squareIsSelected) {
                        if(keyEvent.getCode() == KeyCode.ENTER) {
                            squareIsSelected = false;
                            switchPlayer(player);
                            setTurn(player);
                            Logger.debug("Enter was pressed. Next Player's turn.");
                        }
                    }
                }
        ));
    }

    private void switchPlayer(Player activePlayer) {
        if(activePlayer == Player.ONE) {
            player = Player.TWO;
        } else if(activePlayer == Player.TWO) {
            player = Player.ONE;
        }
    }


}


