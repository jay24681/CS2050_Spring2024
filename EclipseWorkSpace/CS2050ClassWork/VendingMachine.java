import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// VendingMachine class manages a 2D array of cars and provides methods to operate it
class VendingMachine {
    private Car[][] tower;

    // Constructor initializes the vending machine grid with given floors and spaces
    public VendingMachine(int floors, int spaces) {
        tower = new Car[floors][spaces];
    }

    // Adds a car to a specified floor and space if the position is valid and empty
    public void addCar(int floor, int space, Car car) {
        if (isValidPosition(floor, space) && tower[floor][space] == null) {
            tower[floor][space] = car;
        } else {
            System.out.println("Error: Invalid position or slot occupied.");
        }
    }

    // Loads car data from a file and places them into the vending machine if valid
    public void loadCarData(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Error: File not found.");
            return;
        }
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNext()) {
                try {
                    int floor = fileScanner.nextInt();
                    int space = fileScanner.nextInt();
                    int year = fileScanner.nextInt();
                    double price = fileScanner.nextDouble();
                    String manufacturer = fileScanner.next();
                    String model = fileScanner.next();
                    if (floor < 0 || space < 0 || year < 1886 || price < 0) {
                        System.out.println("Invalid car data detected, skipping entry.");
                        continue;
                    }
                    addCar(floor, space, new Car(year, price, manufacturer, model));
                } catch (Exception e) {
                    System.out.println("Error reading car data, skipping entry.");
                    fileScanner.nextLine();
                }
            }
            System.out.println("Car data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }

    // Displays the current state of the vending machine (cars and empty slots)
    public void displayVendingMachine() {
        for (int i = 0; i < tower.length; i++) {
            System.out.print("Floor " + i + ": ");
            for (int j = 0; j < tower[i].length; j++) {
                System.out.print((tower[i][j] != null ? tower[i][j] : "EMPTY") + " | ");
            }
            System.out.println();
        }
    }

    // Retrieves (removes and shows) a car from a specified position if it exists
    public void retrieveCar(int floor, int space) {
        if (!isValidPosition(floor, space) || tower[floor][space] == null) {
            System.out.println("No car found at specified location.");
            return;
        }
        System.out.println("Car retrieved: " + tower[floor][space]);
        tower[floor][space] = null;
    }

    // Prints the inventory sorted by either 'price' or 'year'
    public void printSortedInventory(String sortBy) {
        ArrayList<Car> cars = new ArrayList<>();
        for (Car[] row : tower) for (Car car : row) if (car != null) cars.add(car);
        selectionSort(cars, sortBy);
        for (Car car : cars) System.out.println(car);
    }

    // Sorts the car list using selection sort based on the given attribute
    private void selectionSort(ArrayList<Car> list, String sortBy) {
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if ((sortBy.equals("price") && list.get(j).price < list.get(minIndex).price) ||
                    (sortBy.equals("year") && list.get(j).year < list.get(minIndex).year)) {
                    minIndex = j;
                }
            }
            Car temp = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i, temp);
        }
    }

    // Checks if the given floor and space are valid positions within the machine
    private boolean isValidPosition(int floor, int space) {
        return floor >= 0 && floor < tower.length && space >= 0 && space < tower[0].length;
    }
}
