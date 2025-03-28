import java.util.Scanner;

// CarVendingMachineDriver class runs the main user interface and manages input/output
public class CarVendingMachineDriver {
    public static void main(String[] args) {
        // Scanner for reading user input
        Scanner scanner = new Scanner(System.in);
        int floors, spaces;
        while (true) {
            try {
                System.out.print("Enter number of floors: ");
                floors = scanner.nextInt();
                System.out.print("Enter number of spaces: ");
                spaces = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter valid numbers.");
                scanner.next();
            }
        }

        VendingMachine vendingMachine = new VendingMachine(floors, spaces);

        while (true) {
            System.out.println("\n=== Car Vending Machine Menu ===");
            System.out.println("1. Load Car Data");
            System.out.println("2. Display Vending Machine");
            System.out.println("3. Retrieve a Car");
            System.out.println("4. Sort by Price");
            System.out.println("5. Sort by Year");
            System.out.println("6. Exit");

            // Prompt and validate user's menu choice
            int choice;
            while (true) {
                try {
                    System.out.print("Enter your choice (1-6): ");
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 6) break;
                    else System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    vendingMachine.loadCarData(scanner.next());
                    break;
                case 2:
                    vendingMachine.displayVendingMachine();
                    break;
                case 3:
                    // Prompt for floor and space when retrieving a car
                    int floor, space;
                    while (true) {
                        try {
                            System.out.print("Enter floor: ");
                            floor = scanner.nextInt();
                            System.out.print("Enter space: ");
                            space = scanner.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter valid numbers.");
                            scanner.next();
                        }
                    }
                    vendingMachine.retrieveCar(floor, space);
                    break;
                case 4:
                    vendingMachine.printSortedInventory("price");
                    break;
                case 5:
                    vendingMachine.printSortedInventory("year");
                    break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

