//Julio Vasquez
//Computer Science 2
//Caravana Project
import java.util.*;
import java.io.*;

// ==========================
// Main Class (Driver Logic)
// =========================
public class JulioCarVendingMachineIteration2__2 {
  public static void main(String[] args) {
    // Create scanner for user input
    Scanner scanner = new Scanner(System.in);

    // Create a VendingMachine object to handle logic
    VendingMachine vm = new VendingMachine(scanner);

    boolean running = true;

    // Loop that continuously displays the menu and processes user input
    while (running) {
      System.out.println("\n=== Car Vending Machine Menu ===");
      System.out.println("1. Load Car Data from File");
      System.out.println("2. Display Vending Machine");
      System.out.println("3. Retrieve a Car by Location");
      System.out.println("4. Sort Inventory by Price");
      System.out.println("5. Sort Inventory by Year");
      System.out.println("6. Search for Cars");
      System.out.println("7. Add Car to Wash Queue");
      System.out.println("8. Process Car Wash Queue");
      System.out.println("9. Sell a Car");
      System.out.println("10. Exit");

      int choice = vm.getInt("Enter your choice: ");
      switch (choice) {
      case 1:
        vm.loadCarData();
        break;
      case 2:
        vm.displayInventory();
        break;
      case 3:
        vm.retrieveCar();
        break;
      case 4:
        vm.printSortedInventory("price");
        break;
      case 5:
        vm.printSortedInventory("year");
        break;
      case 6:
        vm.searchCars();
        break;
      case 7:
        vm.addCarToWashQueue();
        break;
      case 8:
        vm.processCarWashQueue();
        break;
      case 9:
        vm.sellCar();
        break;
      case 10:
        System.out.println("Goodbye!");
        running = false;
        break;
      default:
        System.out.println("Invalid choice.");
      }
    }
  }
}

// ==========================
// Class to handle logic
// ==========================
class VendingMachine {
  private LinkedList < Cars > inventory = new LinkedList < > (); // Dynamic car inventory
  private Map < String, Cars > locationMap = new HashMap < > (); // Maps floor-space location
  private Queue < Cars > carWashQueue = new LinkedList < > (); // Queue for car wash
  private Scanner scanner;

  public VendingMachine(Scanner scanner) {
    this.scanner = scanner;
  }

  // Loads car data from file, adds to inventory and map
  public void loadCarData() {
    System.out.print("Enter file name: ");
    String fileName = scanner.next();
    try (Scanner fileScanner = new Scanner(new File(fileName))) {
      while (fileScanner.hasNext()) {
        try {
          String type = fileScanner.next();
          int floor = fileScanner.nextInt();
          int space = fileScanner.nextInt();
          int year = fileScanner.nextInt();
          double price = fileScanner.nextDouble();
          String manufacturer = fileScanner.next();
          String model = fileScanner.next();

          String key = floor + "-" + space;
          if (locationMap.containsKey(key)) {
            System.out.println("Slot already occupied at " + key);
            continue;
          }

          Cars car = type.equalsIgnoreCase("B") ?
            new BasicCar(year, price, manufacturer, model, floor, space) :
            new PremiumCar(year, price, manufacturer, model, floor, space);

          inventory.add(car);
          locationMap.put(key, car);
        } catch (Exception e) {
          System.out.println("Error reading a car entry. Skipping...");
          fileScanner.nextLine();
        }
      }
      System.out.println("Car data loaded successfully.");
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }

  // Displays all cars in the vending machine
  public void displayInventory() {
    if (inventory.isEmpty()) {
      System.out.println("No cars in the vending machine.");
    } else {
      for (Cars car: inventory) {
        System.out.println(car);
      }
    }
  }

  // Retrieves and removes a car from a specific location
  public void retrieveCar() {
    int floor = getInt("Enter floor: ");
    int space = getInt("Enter space: ");
    String key = floor + "-" + space;

    if (locationMap.containsKey(key)) {
      Cars car = locationMap.remove(key);
      inventory.remove(car);
      System.out.println("Car retrieved: " + car);
    } else {
      System.out.println("Car not found at this location.");
    }
  }

  // Sorts and prints cars by price or year
  public void printSortedInventory(String sortBy) {
    ArrayList < Cars > sorted = new ArrayList < > (inventory);
    if (sortBy.equalsIgnoreCase("price")) {
      sorted.sort(Comparator.comparingDouble(Cars::getPrice));
      System.out.println("Sorted Inventory by Price:");
    } else {
      sorted.sort(Comparator.comparingInt(Cars::getYear));
      System.out.println("Sorted Inventory by Year:");
    }
    for (Cars car: sorted) {
      System.out.println(car);
    }
  }

  // Searches for cars by manufacturer and type
  public void searchCars() {
    System.out.print("Enter manufacturer: ");
    String manufacturer = scanner.next();
    System.out.print("Enter car type (Basic/Premium): ");
    String type = scanner.next();

    ArrayList < Cars > matches = new ArrayList < > ();
    for (Cars car: inventory) {
      if (car.getManufacturer().equalsIgnoreCase(manufacturer) &&
        car.getType().equalsIgnoreCase(type)) {
        matches.add(car);
      }
    }

    if (matches.isEmpty()) {
      System.out.println("No cars available.");
    } else {
      matches.sort(Comparator.comparing(Cars::getManufacturer));
      for (Cars car: matches) {
        System.out.println(car);
      }
    }
  }

  // Adds a car to the wash queue and removes it from inventory
  public void addCarToWashQueue() {
    int floor = getInt("Enter floor: ");
    int space = getInt("Enter space: ");
    String key = floor + "-" + space;

    if (locationMap.containsKey(key)) {
      Cars car = locationMap.remove(key);
      inventory.remove(car);
      carWashQueue.offer(car);
      System.out.println("Car retrieved: " + car);
      System.out.println("Car added to wash queue.");
    } else {
      System.out.println("No car found at specified location.");
    }
  }

  // Processes all cars in the wash queue (FIFO order)
  public void processCarWashQueue() {
    if (carWashQueue.isEmpty()) {
      System.out.println("No cars in the wash queue.");
    } else {
      while (!carWashQueue.isEmpty()) {
        Cars car = carWashQueue.poll();
        System.out.println("Washing: " + car);
      }
    }
  }

  // Sells a car and removes it from inventory and map
  public void sellCar() {
    int floor = getInt("Enter floor of the car to sell: ");
    int space = getInt("Enter space of the car to sell: ");
    String key = floor + "-" + space;

    if (locationMap.containsKey(key)) {
      Cars car = locationMap.remove(key);
      inventory.remove(car);
      System.out.println("Car Sold: " + car);
    } else {
      System.out.println("No car found at Floor " + floor + " Space " + space + ".");
    }
  }

  // Prompts and validates integer input
  public int getInt(String prompt) {
    while (true) {
      try {
        System.out.print(prompt);
        return scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter a number.");
        scanner.next();
      }
    }
  }
}

// =================
// Abstract Cars Class
// =================
abstract class Cars {
  private int year;
  private double price;
  private String manufacturer;
  private String model;
  private int floor;
  private int space;

  public Cars(int year, double price, String manufacturer, String model, int floor, int space) {
    this.year = year;
    this.price = price;
    this.manufacturer = manufacturer;
    this.model = model;
    this.floor = floor;
    this.space = space;
  }

  public int getYear() {
    return year;
  }
  public double getPrice() {
    return price;
  }
  public String getManufacturer() {
    return manufacturer;
  }
  public int getFloor() {
    return floor;
  }
  public int getSpace() {
    return space;
  }

  public String getLocationKey() {
    return floor + "-" + space;
  }

  public abstract String getType();

  public String toString() {
    return getType() + " Car: " + manufacturer + " " + model + " " + year + " - $" + price +
      " (Floor: " + floor + ", Space: " + space + ")";
  }
}

// ==============
// BasicCar Class
// ==============
class BasicCar extends Cars {
  public BasicCar(int year, double price, String manufacturer, String model, int floor, int space) {
    super(year, price, manufacturer, model, floor, space);
  }

  public String getType() {
    return "Basic";
  }
}

// =================
// PremiumCar Class
// =================
class PremiumCar extends Cars {
  public PremiumCar(int year, double price, String manufacturer, String model, int floor, int space) {
    super(year, price, manufacturer, model, floor, space);
  }

  public String getType() {
    return "Premium";
  }
}
