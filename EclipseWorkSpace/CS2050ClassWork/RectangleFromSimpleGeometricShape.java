import java.io.FileWriter;
import java.io.IOException;

// Subclass: RectangleFromSimpleGeometricShape
public class RectangleFromSimpleGeometricShape extends SimpleGeometricShape {
    private double width;
    private double height;
    private static int rectangleCount = 0; // Static variable to count rectangles

    // Constructor 1: Default values
    public RectangleFromSimpleGeometricShape() {
        super("White", false);
        this.width = 1.0;
        this.height = 1.0;
        rectangleCount++;
    }

    // Constructor 2: Custom width and height
    public RectangleFromSimpleGeometricShape(double width, double height) {
        super("Blue", true);
        this.width = width;
        this.height = height;
        rectangleCount++;
    }
}