public class SimpleRectangle {
    // Attributes (data fields)
    private double length;
    private double width;

    // Default constructor (sets length and width to 1.0)
    public SimpleRectangle() {
        this.length = 1.0;
        this.width = 1.0;
    }

    // Parameterized constructor with validation (sets to 1 if negative or zero)
    public SimpleRectangle(double newLength, double newWidth) {
        this.length = (newLength > 0) ? newLength : 1.0;
        this.width = (newWidth > 0) ? newWidth : 1.0;
    }

    // Method to get area
    public double getArea() {
        return length * width;
    }

    // Method to get perimeter
    public double getPerimeter() {
        return 2 * (length + width);
    }

    // Getter for length
    public double getLength() {
        return length;
    }

    // Getter for width
    public double getWidth() {
        return width;
    }

    // Setter for length (ensures positive value)
    public void setLength(double newLength) {
        if (newLength > 0) {
            this.length = newLength;
        } else {
            System.out.println("Invalid length. Setting to 1.");
            this.length = 1.0;
        }
    }

    // Setter for width (ensures positive value)
    public void setWidth(double newWidth) {
        if (newWidth > 0) {
            this.width = newWidth;
        } else {
            System.out.println("Invalid width. Setting to 1.");
            this.width = 1.0;
        }
    }
}
