package lab08;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Controller {
    @FXML
    private MenuItem menuNew;
    @FXML
    private MenuItem menuOpen;
    @FXML
    private MenuItem menuSave;
    @FXML
    private MenuItem menuSaveAs;
    @FXML
    private MenuItem menuExit;
    @FXML
    private TableView tableView;

    private File currentFileName;
    private ObservableList<StudentRecord> studentData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        currentFileName = null;
    }

    @FXML
    private void newAction(ActionEvent e) {
        tableView.getItems().clear();
    }

    @FXML
    private void openAction(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        currentFileName = fileChooser.showOpenDialog(tableView.getScene().getWindow());
        load();
    }

    @FXML
    private void saveAction(ActionEvent e) {
        save();
    }

    @FXML
    private void saveAsAction(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        currentFileName = fileChooser.showSaveDialog(tableView.getScene().getWindow());
        System.out.println(currentFileName);
        save();
    }

    @FXML
    private void exitAction(ActionEvent e) {
        Platform.exit();
    }

    private void save() {

    }

    private void load() {
        if(currentFileName!=null) {
            try {
                if(currentFileName.exists()){
                    BufferedReader br = new BufferedReader(new FileReader(currentFileName));
                    String line;
                    while ((line = br.readLine()) != null){
                        String[] columns = line.split(",");
                        studentData.add(new StudentRecord(columns[0],Float.parseFloat(columns[1]),Float.parseFloat(columns[2]),Float.parseFloat(columns[3])));
                    }
                    tableView.setItems(studentData);
                }
                else{
                    System.err.printf("File '%s' does not exist.\n", currentFileName.getAbsolutePath());
                }
            }
            catch(IOException e){
                System.err.printf("File Error: %s", e.getMessage());
            }
        }
    }
}
