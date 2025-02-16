//Julio Vasquez
//2/25/2025
public class LastnameTestSimpleRectangle {
  public static void main(String[] args) {
    // Create rectangle1 using the default constructor
    SimpleRectangle rectangle1 = new SimpleRectangle();

    // Create rectangle2 and rectangle3 using the parameterized constructor
    SimpleRectangle rectangle2 = new SimpleRectangle(4.5, 3.2);
    SimpleRectangle rectangle3 = new SimpleRectangle(-2.0, 5.0); // Invalid length, should default to 1

    // Print details for each rectangle
    printRectangleDetails(rectangle1, "Rectangle 1");
    printRectangleDetails(rectangle2, "Rectangle 2");
    printRectangleDetails(rectangle3, "Rectangle 3");
  }

  // Method to print rectangle details
  public static void printRectangleDetails(SimpleRectangle rect, String name) {
    System.out.println(name + ":");
    System.out.println("Length: " + rect.getLength() + ", Width: " + rect.getWidth());
    System.out.println("Area: " + rect.getArea() + ", Perimeter: " + rect.getPerimeter());
    System.out.println();
  }
}