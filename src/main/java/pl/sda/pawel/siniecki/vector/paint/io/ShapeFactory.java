package pl.sda.pawel.siniecki.vector.paint.io;

import pl.sda.pawel.siniecki.vector.paint.shapes.*;

public class ShapeFactory {
    
    public Shape get(String string) {
        String[] data = string.split(";");
        String shapeName = data[0];
        
        switch (shapeName) {
            case "Line":
                return getLine(data);
            case "Rectangle":
                return getRect(data);
            case "Triangle":
                return getTria(data);
            case "Ellipse":
                return getElli(data);
        }

        return null;
    }

    private Shape getLine(String[] data) {

        double x1 = Double.parseDouble(data[1]);
        double y1 = Double.parseDouble(data[2]);
        double x2 = Double.parseDouble(data[3]);
        double y2 = Double.parseDouble(data[4]);
        String fillColor = data[5];
        String strokeColor = data[6];

        return new Line.Builder()
                .setX1(x1)
                .setY1(y1)
                .setX2(x2)
                .setY2(y2)
                .setFillColor(fillColor)
                .setStrokeColor(strokeColor)
                .build();
    }

    private Shape getRect(String[] data) {

        double x1 = Double.parseDouble(data[1]);
        double y1 = Double.parseDouble(data[2]);
        double x2 = Double.parseDouble(data[3]);
        double y2 = Double.parseDouble(data[4]);
        String fillColor = data[5];
        String strokeColor = data[6];

        return new Rectangle.Builder()
                .setX1(x1)
                .setY1(y1)
                .setX2(x2)
                .setY2(y2)
                .setFillColor(fillColor)
                .setStrokeColor(strokeColor)
                .build();
    }

    private Shape getTria(String[] data) {
        //Triangle;142.0;84.0;311.0;84.0;226.5;72.0;0xffc0cbff;0xd2691eff;
        double x1 = Double.parseDouble(data[1]);
        double y1 = Double.parseDouble(data[2]);
        double x2 = Double.parseDouble(data[3]);
        double y2 = Double.parseDouble(data[4]);
        double x3 = Double.parseDouble(data[5]);
        double y3 = Double.parseDouble(data[6]);
        String fillColor = data[7];
        String strokeColor = data[8];

        Triangle.Builder builder = new Triangle.Builder()
                .setPoint1(x1, y1)
                .setPoint2(x2, y2)
                .setPoint3(x3, y3)
                .setFillColor(fillColor)
                .setStrokeColor(strokeColor);

        return builder.build();
    }

    private Shape getElli(String[] data) {

        double x1 = Double.parseDouble(data[1]);
        double y1 = Double.parseDouble(data[2]);
        double x2 = Double.parseDouble(data[3]);
        double y2 = Double.parseDouble(data[4]);
        String fillColor = data[5];
        String strokeColor = data[6];

        return new Ellipse.Builder()
                .setX1(x1)
                .setY1(y1)
                .setX2(x2)
                .setY2(y2)
                .setFillColor(fillColor)
                .setStrokeColor(strokeColor)
                .build();
    }

}
