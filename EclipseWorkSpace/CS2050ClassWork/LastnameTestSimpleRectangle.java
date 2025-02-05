// Replace "Lastname" with your actual last name
public class LastnameTestSimpleRectangle {
    public static void main(String[] args) {
        // Create rectangle1 using the default constructor
        SimpleRectangle rectangle1 = new SimpleRectangle();

        // Create rectangle2 and rectangle3 using the parameterized constructor
        SimpleRectangle rectangle2 = new SimpleRectangle(4.5, 3.2);  // Valid values
        SimpleRectangle rectangle3 = new SimpleRectangle(-2.0, 5.0); // Invalid length, should default to 1

        // Print details for rectangle1
        System.out.println("Rectangle 1:");
        System.out.println("Length: " + rectangle1.getLength() + ", Width: " + rectangle1.getWidth());
        System.out.println("Area: " + rectangle1.getArea() + ", Perimeter: " + rectangle1.getPerimeter());
        System.out.println();

        // Print details for rectangle2
        System.out.println("Rectangle 2:");
        System.out.println("Length: " + rectangle2.getLength() + ", Width: " + rectangle2.getWidth());
        System.out.println("Area: " + rectangle2.getArea() + ", Perimeter: " + rectangle2.getPerimeter());
        System.out.println();

        // Print details for rectangle3
        System.out.println("Rectangle 3:");
        System.out.println("Length: " + rectangle3.getLength() + ", Width: " + rectangle3.getWidth());
        System.out.println("Area: " + rectangle3.getArea() + ", Perimeter: " + rectangle3.getPerimeter());
    }
}
