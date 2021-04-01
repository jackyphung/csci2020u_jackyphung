package lab09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {
    private static ArrayList<Float> googleClosePrices = new ArrayList<>();
    private static ArrayList<Float> appleClosePrices = new ArrayList<>();

    private static Canvas canvas = new Canvas(1000,800);
    private static GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane grid = new GridPane();

        grid.add(canvas,0,0);
        downloadStockPrices();
        drawLinePlot(googleClosePrices,appleClosePrices);
        primaryStage.setTitle("Lab 09");

        primaryStage.setScene(new Scene(grid, 1000, 800));
        primaryStage.show();
    }

    public static void drawLinePlot(ArrayList<Float> gClosePrice, ArrayList<Float> aClosePrice){
        int xEnd = gClosePrice.size();
        float max = Collections.max(googleClosePrices);
        System.out.println(max);
        gc.strokeLine(50,50,50,max-50);
        gc.strokeLine(50,max-50,xEnd*11,max-50);
        drawLine(gClosePrice,Color.RED,max);
        drawLine(aClosePrice,Color.BLUE,max);
    }

    public static void drawLine(ArrayList<Float> closePrice, Color colour, float max){
        int xStart = 50;
        gc.setStroke(colour);
        for(int i = 0; i < closePrice.size()-1; i++){
            gc.strokeLine(xStart,max-50-closePrice.get(i)/1.15,xStart+10, max-50-closePrice.get(i+1)/1.15);
            xStart+=10;
        }
    }

    public static void downloadStockPrices(){
        String googleSymbol = "GOOG";
        String appleSymbol = "AAPL";

        try{
            URL googleURL = new URL("https://query1.finance.yahoo.com/v7/finance/download/"+googleSymbol+"?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");
            URLConnection googleConn = googleURL.openConnection();
            googleConn.setDoOutput(false);
            googleConn.setDoInput(true);
            BufferedReader gBr = new BufferedReader(new InputStreamReader(googleConn.getInputStream()));

            String gLine = gBr.readLine();
            while((gLine = gBr.readLine()) != null){
                String[] googleColumns = gLine.split(",");
                String gValue = googleColumns[5];
                googleClosePrices.add(Float.parseFloat(gValue));
            }
            gBr.close();

            URL appleURL = new URL("https://query1.finance.yahoo.com/v7/finance/download/"+appleSymbol+"?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");
            URLConnection appleConn = appleURL.openConnection();
            appleConn.setDoOutput(false);
            appleConn.setDoInput(true);
            BufferedReader aBr = new BufferedReader((new InputStreamReader(appleConn.getInputStream())));

            String aLine = aBr.readLine();
            while((aLine = aBr.readLine()) != null){
                String[] appleColumns = aLine.split(",");
                String aValue = appleColumns[5];
                appleClosePrices.add(Float.parseFloat(aValue));
            }
            aBr.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
