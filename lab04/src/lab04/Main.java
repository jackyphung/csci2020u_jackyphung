package lab04;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        Scene scene = new Scene(grid, 400, 400);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Login Page");
        Label headerLabel = new Label ("Registration Page");
        Label userLabel = new Label("Username:");
        Label passLabel = new Label("Password:");
        Label fullNameLabel = new Label("Full Name:");
        Label emailLabel = new Label("E-Mail:");
        Label phoneLabel = new Label("Phone #:");
        Label bdayLabel = new Label("Date of Birth:");
        TextField userText = new TextField();
        PasswordField passText = new PasswordField();
        TextField fullNameText = new TextField();
        TextField emailText = new TextField();
        TextField phoneText = new TextField();
        DatePicker bdayDate = new DatePicker();
        Button button = new Button("Register");

        grid.add(headerLabel,0,0);
        grid.add(userLabel, 0 ,1);
        grid.add(passLabel, 0 ,2);
        grid.add(fullNameLabel, 0 ,3);
        grid.add(emailLabel, 0 ,4);
        grid.add(phoneLabel, 0 ,5);
        grid.add(bdayLabel, 0 ,6);
        grid.add(userText,1,1);
        grid.add(passText,1,2);
        grid.add(fullNameText,1,3);
        grid.add(emailText,1,4);
        grid.add(phoneText,1,5);
        grid.add(bdayDate,1,6);
        grid.add(button, 0, 7);
        grid.setHgap(10);
        grid.setVgap(10);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(fullNameText.getText());
                System.out.println(emailText.getText());
                System.out.println(phoneText.getText());
                System.out.println(bdayDate.getValue());
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
