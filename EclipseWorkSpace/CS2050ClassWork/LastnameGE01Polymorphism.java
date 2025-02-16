//Julio Vasquez Polymorphism Class
//2/14/2025
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Superclass: Animal (Base class for all animals)
abstract class Animal {
  private String name;
  private String food;
  private int weight;
  private int sleep;
  private String location;

  // Constructor to initialize all variables
  public Animal(String name, String food, int weight, int sleep, String location) {
    this.name = name;
    this.food = food;
    this.weight = weight;
    this.sleep = sleep;
    this.location = location;
  }

  // Getter methods (Encapsulation: no direct access to fields)
  public String getName() {
    return name;
  }
  public String getFood() {
    return food;
  }
  public int getWeight() {
    return weight;
  }
  public int getSleep() {
    return sleep;
  }
  public String getLocation() {
    return location;
  }

  // Default behaviors for all animals
  public void eat() {
    System.out.println("Animal is eating.");
  }
  public void sleep() {
    System.out.println("Animal is sleeping.");
  }
  public void swim() {
    System.out.println("Animal is swimming.");
  }

  // Returns a formatted string representing the animal
  @Override
  public String toString() {
    return "Name: " + name + " - Weighs: " + weight + " lbs - Sleeps: " + sleep + " hours - Location: " + location;
  }
}

// Subclass: Bear (inherits from Animal)
class Bear extends Animal {
  public Bear(String name, String food, int weight, int sleep, String location) {
    super(name, food, weight, sleep, location);
  }

  // Overrides eat method to specify food
  @Override
  public void eat() {
    System.out.println("Bear is eating " + getFood() + ".");
  }

  // Overrides sleep method
  @Override
  public void sleep() {
    System.out.println("Bear sleeps for " + getSleep() + " hours.");
  }

  // Overrides swim method
  @Override
  public void swim() {
    System.out.println("Bear is swimming.");
  }
}

// Subclass: Elephant (inherits from Animal)
class Elephant extends Animal {
  public Elephant(String name, String food, int weight, int sleep, String location) {
    super(name, food, weight, sleep, location);
  }

  // Overrides sleep method
  @Override
  public void sleep() {
    System.out.println("Elephant sleeps for " + getSleep() + " hours.");
  }
}

// Subclass: Monkey (inherits from Animal)
class Monkey extends Animal {
  public Monkey(String name, String food, int weight, int sleep, String location) {
    super(name, food, weight, sleep, location);
  }

  // Overrides eat method to specify food
  @Override
  public void eat() {
    System.out.println("Monkey is eating " + getFood() + ".");
  }

  // Overrides swim method
  @Override
  public void swim() {
    System.out.println("Monkey is swimming.");
  }
}

// Subclass: Sloth (inherits from Animal)
class Sloth extends Animal {
  public Sloth(String name, String food, int weight, int sleep, String location) {
    super(name, food, weight, sleep, location);
  }
}

// Main class that runs the program 
public class LastnameGE01Polymorphism {
  public static void main(String[] args) {
    try {
      // Open the file
      Scanner scanner = new Scanner(new File("Animals.txt"));

      // Read the number of animals
      int numAnimals = scanner.nextInt();
      scanner.nextLine(); // Move to the next line

      // Create an array to store Animal objects
      Animal[] animals = new Animal[numAnimals];

      // Read and create animals
      for (int i = 0; i < numAnimals; i++) {
        String type = scanner.next(); // Read animal type
        String name = scanner.next(); // Read name
        String food = scanner.next(); // Read food
        int weight = scanner.nextInt(); // Read weight
        int sleep = scanner.nextInt(); // Read sleep hours
        String location = scanner.nextLine().trim(); // Read the remaining location (allows spaces)

        // Create the correct Animal object
        switch (type.toLowerCase()) {
        case "bear":
          animals[i] = new Bear(name, food, weight, sleep, location);
          break;
        case "elephant":
          animals[i] = new Elephant(name, food, weight, sleep, location);
          break;
        case "monkey":
          animals[i] = new Monkey(name, food, weight, sleep, location);
          break;
        case "sloth":
          animals[i] = new Sloth(name, food, weight, sleep, location);
          break;
        default:
          System.out.println("Unknown animal type: " + type);
          break;
        }
      }
      scanner.close(); // Close file

      // Display animal details
      for (Animal animal: animals) {
        System.out.println("Type: " + animal.getClass().getSimpleName()); // Print class name
        System.out.println(animal);
        animal.eat();
        animal.sleep();
        animal.swim();
        System.out.println();
      }

    } catch (FileNotFoundException e) {
      System.out.println("Error: File not found!");
    }
  }
}