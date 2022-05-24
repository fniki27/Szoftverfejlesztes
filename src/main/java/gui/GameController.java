package gui;

import game.DiskColor;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import game.FourColorsGame;
import game.Player;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;


public class GameController {

    private FourColorsGame gameState;

    private boolean squareIsSelected = false;

    private DiskColor toBePlacedColor;

    @FXML
    private Button mainButton;

    @FXML
    private Label redNumLabel;

    @FXML
    private Label blueNumLabel;

    @FXML
    private Label greenNumLabel;

    @FXML
    private Label yellowNumLabel;

    @FXML
    private Label playerLabel;

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

    private BooleanProperty gameOver = new SimpleBooleanProperty();
    private BooleanProperty gameDraw = new SimpleBooleanProperty();

    public void setUsername(String username, String username2){
        this.username = username;
        this.username2 = username2;
        playerLabel.setText("Turn: " + this.username);
        firstPlayer.setText(this.username + "'s disks: ");
        secondPlayer.setText(this.username2 + "'s disks: ");
    }

    @FXML
    private void setTurn(Player player){
        if (player == Player.ONE){
            playerLabel.setText("Turn: " + username);
        } else if (player == Player.TWO) {
            playerLabel.setText("Turn: " + username2);
        }

    }

    @FXML
    private void setWinner(Player player){
        if (player == Player.ONE){
            playerLabel.setText("Winner: " + username);
        } else if (player == Player.TWO) {
            playerLabel.setText("Winner: " + username2);
        }

    }

    @FXML
    private void initialize() {
        gameState = new FourColorsGame();
        mainButton.setVisible(false);
        populateGrid();
        gameState.setEmptySquare(gameState.board);
        registerKeyEventHandler();
        setNumLabels();

        EventHandler<MouseEvent> handler = MouseEvent::consume;

        gameOver.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                board.addEventFilter(MouseEvent.ANY, handler);
                mainButton.setVisible(true);
                setWinner(gameState.player);
                Logger.info("Game Over!");

            }
        });
        gameDraw.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                board.addEventFilter(MouseEvent.ANY, handler);
                mainButton.setVisible(true);
                setWinner(gameState.player);
                Logger.info("Game is Draw!");
            }
        });
    }

    @FXML
    private void setNumLabels() {
        redNumLabel.setText(Integer.toString(gameState.red_num));
        blueNumLabel.setText(Integer.toString(gameState.blue_num));
        greenNumLabel.setText(Integer.toString(gameState.green_num));
        yellowNumLabel.setText(Integer.toString(gameState.yellow_num));
    }

    @FXML
    public void switchToMenu(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void populateGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                var square = createSquare();
                board.add(square, j, i);
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
        if (gameState.isSquareEmpty(row,col)) {
                squareIsSelected = true;

                if (gameState.player == Player.ONE) {
                    if (disk.getFill().equals(Color.TRANSPARENT)) {
                        disk.setFill(Color.RED);
                        toBePlacedColor = DiskColor.RED;
                    } else if (disk.getFill().equals(Color.RED)) {
                        disk.setFill(Color.DODGERBLUE);
                        toBePlacedColor = DiskColor.BLUE;
                    } else {
                        disk.setFill(Color.RED);
                        toBePlacedColor = DiskColor.RED;
                    }
                }

                if(gameState.player == Player.TWO){
                    if(disk.getFill().equals(Color.TRANSPARENT)) {
                        disk.setFill(Color.GREEN);
                        toBePlacedColor = DiskColor.GREEN;
                    } else if (disk.getFill().equals(Color.GREEN)) {
                        disk.setFill(Color.rgb(238,203,26));
                        toBePlacedColor = DiskColor.YELLOW;
                    } else {
                        disk.setFill(Color.GREEN);
                        toBePlacedColor = DiskColor.GREEN;
                    }
                }

                Logger.info("Clicked on square {},{}", row, col);
                Logger.info("Press ENTER to set the color of the disk");
            } else {
                Logger.info("This square is not empty!");
                Logger.info("Please select an empty square!");
            }
    }

    private void registerKeyEventHandler() {
        Platform.runLater(() -> board.getScene().setOnKeyPressed(
                keyEvent -> {
                        if (squareIsSelected) {
                            var row = GridPane.getRowIndex(pressedSquare);
                            var col = GridPane.getColumnIndex(pressedSquare);
                            var toBePlacedDisk = (Circle) pressedSquare.getChildren().get(0);

                            if (gameState.areDisksLeft(toBePlacedColor)) {
                                if (gameState.canPlaceDisk(row, col, toBePlacedColor)) {
                                    if (keyEvent.getCode() == KeyCode.ENTER) {
                                        Logger.debug("Enter was pressed.");
                                        squareIsSelected = false;
                                        gameState.placeDisk(row, col, getDiskColor(toBePlacedDisk));
                                        setNumLabels();
                                        setTurn(gameState.player);
                                        checkStatus();
                                        }
                                } else {
                                    toBePlacedDisk.setFill(Color.TRANSPARENT);
                                    squareIsSelected = false;
                                    Logger.info("Can't place a disk where it's neighbours are the same color!");
                                }
                            } else {
                                toBePlacedDisk.setFill(Color.TRANSPARENT);
                                squareIsSelected = false;
                                Logger.info("There are no more disks of this color!");
                            }
                        }
                }
        ));
    }


    private DiskColor getDiskColor(Circle disk){
        DiskColor diskColor = DiskColor.NONE;

        if(disk.getFill().equals(Color.RED))
            diskColor = DiskColor.RED;
        if(disk.getFill().equals(Color.DODGERBLUE))
            diskColor = DiskColor.BLUE;
        if(disk.getFill().equals(Color.GREEN))
            diskColor = DiskColor.GREEN;
        if(disk.getFill().equals(Color.rgb(238,203,26)))
            diskColor = DiskColor.YELLOW;

        return diskColor;
    }

    private void checkStatus() {
        if (gameState.isGameOver()) {
            gameOver.setValue(true);
        } else if (gameState.isGameDraw()) {
            gameDraw.setValue(true);
        }
    }

}


