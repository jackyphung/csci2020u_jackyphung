package lab10.Server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.*;

public class Server extends Application {
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected ServerThread[] threads = null;
    protected int numClients = 0;
    protected int maxClients = 50;
    protected String message;

    public static int SERVER_PORT = 8080;

    public void startServer(TextArea textArea){
        UpdateTextArea tempArea = new UpdateTextArea(textArea);
        TextArea updatedTextArea = textArea;
        try{
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running");
            System.out.println("Listening to port: " +SERVER_PORT);
            Thread thread = new Thread(() -> {
                threads = new ServerThread[maxClients];
                while(true) {
                    try{
                        clientSocket = serverSocket.accept();
                        System.out.println("Client connected");
                        threads[numClients] = new ServerThread(clientSocket, updatedTextArea);
                        threads[numClients].start();
                        numClients++;
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Lab 10 - Server");

        GridPane grid = new GridPane();

        TextArea textArea = new TextArea();
        Button exitButton = new Button("Exit");

        grid.setVgap(10);
        grid.add(textArea,0,0);
        grid.add(exitButton,0,2);
        stage.setScene(new Scene(grid, 475, 275));
        stage.show();

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Closing connection not working properly
                /*try{
                    for(int i = 0; i< threads.length;i++) {
                        if(threads[i].socket != null && !threads[i].socket.isClosed()){
                            threads[i].socket.close();
                        }
                    }
                }
                catch(IOException e){
                    e.printStackTrace();
                }*/
                System.exit(1);
            }
        });
        startServer(textArea);
    }

    public static void main(String args[]){
        launch(args);
    }
}
