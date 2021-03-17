package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        double total = 0.0;
        double startAngle = 0.0;
        int i = 0;
        int startPosition = 200;
        int colorStartPosition = 190;

        Color[] colours = {
                Color.AQUA,Color.BISQUE,Color.YELLOW,Color.ROYALBLUE
        };

        WordCounter wordCounter = new WordCounter();

        try{
            wordCounter.parseFile(new File("resources/weatherwarnings-2015.csv"));
        }catch(FileNotFoundException e){
            System.err.println("Invalid input dir.");
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
        for(Map.Entry<String, Integer> entry: wordCounter.getWordCounts().entrySet()){
            System.out.println("["+entry.getKey()+","+entry.getValue()+"]" );
            total += entry.getValue();
        }

        System.out.println("Total: " +total);
        GridPane grid = new GridPane();

        Canvas canvas;
        canvas = new Canvas(800,500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        grid.add(canvas,0,0);

        //fill colours legend
        for(int b =0;b<colours.length;b++){
            gc.setFill(colours[b]);
            gc.fillRect(30,colorStartPosition,20,10);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(30,colorStartPosition,20,10);
            colorStartPosition += 30;
        }

        //fill text legend
        for(Map.Entry<String, Integer> entry: wordCounter.getWordCounts().entrySet()){
            gc.setFill(Color.BLACK);
            gc.fillText(entry.getKey(),60,startPosition);
            startPosition += 30;
        }

        //fill pie graph
        for(Map.Entry<String, Integer> entry: wordCounter.getWordCounts().entrySet()){
            double slicePercentage = (double)entry.getValue()/total;
            double sweepAngle = slicePercentage * 360;
            gc.setFill(colours[i]);
            gc.fillArc(350,50,400,400,startAngle,sweepAngle, ArcType.ROUND);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(350,50,400, 400,startAngle,sweepAngle,ArcType.ROUND);
            startAngle+= sweepAngle;
            i++;
        }

        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(new Scene(grid, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
