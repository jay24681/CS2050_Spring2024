//Julio Vasquez Guided Exploration 2D Array Program
//Computer Science 2 
//March 3,2025
public class LastnameGE012DArray {
  public static void main(String[] args) {
    // Question 1: Declare a 2D array of Car objects
    Car[][] carArray = new Car[2][3];
    
    // Question 2: Fill the array with Car objects
    carArray[0][0] = new Car("Toyota");
    carArray[0][1] = new Car("Honda");
    carArray[0][2] = new Car("Ford");
    carArray[1][0] = new Car("BMW");
    carArray[1][1] = new Car("Audi");
    carArray[1][2] = new Car(); // Uses the default constructor

    // Question 3: Iterate through the array and print the table
    for (int i = 0; i < carArray.length; i++) {
      for (int j = 0; j < carArray[i].length; j++) {
        // Calling the `printMake` method on the Car object inside the array
        carArray[i][j].printMake();
      }
      System.out.println();
    }
  }

  // Car class 
  static class Car {
    private String make;

    public Car() {
      this.make = "Unknown";
    }

    public Car(String make) {
      this.make = make;
    }

    public void printMake() {
      System.out.print(this.make + " ");
    }
  }
}
