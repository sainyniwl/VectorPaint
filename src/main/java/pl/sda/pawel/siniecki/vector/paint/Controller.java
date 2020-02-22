package pl.sda.pawel.siniecki.vector.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.sda.pawel.siniecki.vector.paint.shapes.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @FXML public Button clear;
    @FXML public Button load;
    @FXML public Button save;

    private double startX;
    private double startY;

    private double endX;
    private double endY;

    private List<Shape> shapeList = new ArrayList<Shape>();

    private Shape currentShape;
    private Tool currentTool = Tool.LINE;


    public void initialize() {

        strokeColorPicker.setValue(Color.BLACK);
        fillColorPicker.setValue(Color.BLACK);

        refreshCanvas();

        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                shapeList.removeAll(shapeList);
                GraphicsContext context = canvas.getGraphicsContext2D();
                context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                context.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }
        });

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
                prepareShape();
                applyShape();
                refreshCanvas();
            }
        });

        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                endX = mouseEvent.getX();
                endY = mouseEvent.getY();
                System.out.printf("Dragged x=%f y=%f \n", endX, endY);
                prepareShape();
                refreshCanvas();
            }
        });
    }

    private void applyShape() {
        shapeList.add(currentShape);
        //currentShape = null;
    }

    private void prepareShape() {
        currentShape = createShape();
        currentShape.setStrokeColor(strokeColorPicker.getValue());
        currentShape.setFillColor(fillColorPicker.getValue());
    }

    private Shape createShape() {
        switch (currentTool) {
            default:
            case LINE :
                return new Line(startX, startY, endX, endY);
            case RECTANGLE:
                return new Rectangle(startX, startY, endX, endY);
            case TRIANGLE:
                return new Triangle(startX, startY, endX, endY);
        }
    }

    private void refreshCanvas() {
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setStroke(Color.BLACK);
        context.setLineWidth(5);
        context.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Shape shape : shapeList) {
            shape.drawShape(context);
        }

        if (currentShape != null) {
            currentShape.drawShape(context);
        }
    }

    @FXML
    public void changeTool(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source == lineTool) {
            currentTool = Tool.LINE;
        } else if (source == rectTool) {
            currentTool = Tool.RECTANGLE;
        } else if (source == triangleTool) {
            currentTool = Tool.TRIANGLE;
        } else if (source == circleTool) {
            currentTool = Tool.CIRCLE;
        } else if (source == ellipseTool) {
            currentTool = Tool.ELLIPSE;
        } else if (source == starTool) {
            currentTool = Tool.STAR;
        } else {
            throw new IllegalStateException("Unsupported Tool");
        }
        System.out.println(currentTool);
    }

    @FXML
    public void handleSave() {
        Optional<String> reduce = shapeList.stream()
                .map(shape -> shape.getData())
                .reduce((acc, text) -> acc + "\n" + text);

        if (reduce.isPresent()) {
            System.out.println(reduce.get());
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("YOLO files (*.yolo)", "*.yolo");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(new Stage());

            if (file != null) {
                saveTextToFile(reduce.get(), file);
            }
        }
    }

    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
