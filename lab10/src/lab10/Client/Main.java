package lab10.Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Main extends Application {
    private Socket socket = null;

    public static String SERVER_ADDRESS = "localhost";
    public static int SERVER_PORT = 8080;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 10 - Client");

        GridPane grid = new GridPane();

        Label usernameLabel = new Label("Username:");
        TextField usernameText = new TextField();
        Label messageLabel = new Label("Message");
        TextField messageText = new TextField();
        Button sendButton = new Button("Send");
        Button exitButton = new Button("Exit");

        grid.setVgap(10);
        grid.add(usernameLabel,0,0);
        grid.add(usernameText,1,0);
        grid.add(messageLabel,0,1);
        grid.add(messageText,1,1);
        grid.add(sendButton,0,2);
        grid.add(exitButton,0,3);
        primaryStage.setScene(new Scene(grid, 300, 275));
        primaryStage.show();

        socket = new Socket(SERVER_ADDRESS,SERVER_PORT);
        OutputStream out = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    String message = usernameText.getText() + ": " + messageText.getText();
                    dataOutputStream.writeUTF(message);
                    dataOutputStream.flush();
                    System.out.println("Message sent");
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    socket.close();
                    System.exit(1);
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
