//Julio Vasquez
//2/14/2025
public class SimpleRectangle {
  private double length;
  private double width;

  // Default constructor (sets length and width to 1)
  public SimpleRectangle() {
    length = 1.0;
    width = 1.0;
  }

  // Parameterized constructor with validation
  public SimpleRectangle(double newLength, double newWidth) {
    length = (newLength > 0) ? newLength : 1.0;
    width = (newWidth > 0) ? newWidth : 1.0;
  }

  // Calculate area
  public double getArea() {
    return length * width;
  }

  // Calculate perimeter
  public double getPerimeter() {
    return 2 * (length + width);
  }

  // Getter methods
  public double getLength() {
    return length;
  }

  public double getWidth() {
    return width;
  }
}