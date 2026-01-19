public class Menu {
    public static void printMenu() {
        System.out.println("\n=== Airport Management System ===");
        System.out.println("--- Setup ---");
        System.out.println("1. Add Aircraft");
        System.out.println("2. Add Pilot");
        System.out.println("3. Add Passenger");
        System.out.println("4. Add Crew Member");

        System.out.println("--- Flight Prep ---");
        System.out.println("5. Create Flight");
        System.out.println("6. Assign Flight to Gate");
        System.out.println("7. Assign Crew to Flight");

        System.out.println("--- Operations ---");
        System.out.println("8. Board Passenger");
        System.out.println("9. Operate Flight (Depart/Land)");

        System.out.println("--- Reports ---");
        System.out.println("10. View Schedule");
        System.out.println("11. View Passengers");
        System.out.println("12. View Passenger Ticket History");

        System.out.println("--- System ---");
        System.out.println("13. Save & Exit");
    }
}
