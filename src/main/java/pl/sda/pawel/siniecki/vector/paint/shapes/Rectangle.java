package pl.sda.pawel.siniecki.vector.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Shape {

    private double x;
    private double y;
    private double w;
    private double h;

    public Rectangle(double x1, double y1, double x2, double y2) {
        this.x = Math.min(x1, x2);
        this.y = Math.min(y1, y2);
        this.w = Math.abs(x1 - x2);
        this.h = Math.abs(y1 - y2);
    }

    public void draw(GraphicsContext context) {
        context.setStroke(getStrokeColor());
        context.setFill(getFillColor());
        context.strokeRect(x, y, w, h);
        context.fillRect(x, y, w, h);
    }
}
