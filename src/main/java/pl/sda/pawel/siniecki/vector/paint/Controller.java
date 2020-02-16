package pl.sda.pawel.siniecki.vector.paint;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    @FXML public ColorPicker fillColorPicker;
    @FXML public ColorPicker strokeColorPicker;
    @FXML public Button lineTool;
    @FXML public Button rectTool;
    @FXML public Button triangleTool;
    @FXML public Button circleTool;
    @FXML public Button starTool;
    @FXML public Button ellipseTool;
    @FXML public Canvas canvas;

    private double startX;
    private double startY;

    private double endX;
    private double endY;


    public void initialize() {
        refreshCanvas();

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                startX = mouseEvent.getX();
                startY = mouseEvent.getY();
                System.out.printf("Pressed x=%f y=%f \n", startX, startY);
            }
        });

        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                endX = mouseEvent.getX();
                endY = mouseEvent.getY();
                System.out.printf("Released x=%f y=%f \n", endX, endY);
                refreshCanvas();
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                endX = mouseEvent.getX();
                endY = mouseEvent.getY();
                System.out.printf("Dragged x=%f y=%f \n", endX, endY);
                refreshCanvas();
            }
        });
    }

    private void refreshCanvas() {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setStroke(Color.BLACK);
        context.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.strokeLine(startX, startY, endX, endY);
    }
}
