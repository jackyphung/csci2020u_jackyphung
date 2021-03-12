package lab06;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {
    //bar graph data
    private final static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private final static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    //pie graph data
    private final static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private final static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private final static Color[] pieColours = {
            Color. AQUA , Color. GOLD , Color. DARKORANGE ,
            Color. DARKSALMON , Color. LAWNGREEN , Color. PLUM
    };


    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double maxVal = Double.NEGATIVE_INFINITY;
        //retrieves the max value of the array to determine the scaling of the bar chart
        for(double val: avgCommercialPricesByYear) {
            if(val > maxVal){
                maxVal = val;
            }
        }
        drawBarChart(gc, 150,500, avgHousingPricesByYear,Color.RED,50,maxVal);
        drawBarChart(gc, 150,500, avgCommercialPricesByYear,Color.BLUE,500/avgCommercialPricesByYear.length,maxVal);
        drawPieChart(gc);
    }

    //prints bar chart
    private void drawBarChart(GraphicsContext gc, int w, int h, double[] data, Color colour, int startX, double max){
        gc.setFill(colour);
        double xInc = (w/data.length);

        double x = startX;

        for(double val: data) {
            double height = (val / max)*h;
            gc.fillRect(x,(h-height+50),xInc, height);
            x += 2* xInc;
        }
    }

    //draws pie chart
    private void drawPieChart(GraphicsContext gc){
        int numOfPurchases = 0;
        for (int purchases: purchasesByAgeGroup)
            numOfPurchases += purchases;

        double startAngle = 0.0;
        for(int i = 0; i < purchasesByAgeGroup.length; i++) {
            double slicePercentage = (double) purchasesByAgeGroup[i] / numOfPurchases;
            double sweepAngle = slicePercentage * 360;

            gc.setFill(pieColours[i]);
            gc.fillArc(450,50, 450,450, startAngle, sweepAngle, ArcType.ROUND);

            startAngle += sweepAngle;
        }
    }
}
