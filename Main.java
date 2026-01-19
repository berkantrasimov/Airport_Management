import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemManager manager = new SystemManager(scanner);
        boolean running = true;

        while (running) {
            Menu.printMenu();
            int choice = 0;
            try {
                System.out.print("Choose an option: ");
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: manager.addAircraft(); break;
                case 2: manager.addPilot(); break;
                case 3: manager.addPassenger(); break;
                case 4: manager.addCrew(); break;
                case 5: manager.createFlight(); break;
                case 6: manager.assignFlightToGate(); break;
                case 7: manager.assignCrewToFlight(); break;
                case 8: manager.boardPassenger(); break;
                case 9: manager.performFlightOperations(); break;
                case 10: manager.viewScheduleSorted(); break;
                case 11: manager.sortPassengers(); break;
                case 12: manager.viewPassengerHistory(); break;
                case 13:
                    manager.saveSystemData();
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}