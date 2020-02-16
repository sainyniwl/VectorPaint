package pl.sda.pawel.siniecki.vector.paint.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class Shape {

    private Paint fillColor = Color.PINK;
    private Paint strokeColor = Color.BLACK;

    public abstract void draw(GraphicsContext context);

    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
    }

    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Paint getFillColor() {
        return fillColor;
    }

    public Paint getStrokeColor() {
        return strokeColor;
    }
}
