package pl.sda.pawel.siniecki.vector.paint.io;

import pl.sda.pawel.siniecki.vector.paint.shapes.Line;
import pl.sda.pawel.siniecki.vector.paint.shapes.Shape;

public class ShapeFactory {
    
    public Shape get(String string) {
        String[] data = string.split(";");
        String shapeName = data[0];
        
        switch (shapeName) {
            case "line":
                return getLine(data);
        }
    }

    private Shape getLine(String[] data) {

        double x1 = Double.parseDouble(data[1]);
        double y1 = Double.parseDouble(data[2]);
        double x2 = Double.parseDouble(data[3]);
        double y2 = Double.parseDouble(data[4]);
        String fillColor = data[5];
        String strokeColor = data[6];

        Line line = new Line.Builder()
                .setX1(x1)
                .setX2(x2)
                .setY1(y1)
                .setY2(y2)
                .setFillColor(fillColor)
                .setStrokeColor(strokeColor)
                .build();

        return line;
    }
}
