import java.io.*;
import java.util.*;

public class SystemManager {
    private Airport airport;
    private Schedule schedule;
    private List<Pilot> pilots = new ArrayList<>();
    private List<Passenger> passengers = new ArrayList<>();
    private List<Crew> cabinCrew = new ArrayList<>();
    private final Scanner scanner;
    private final String FILE_NAME = "airport_data.ser";

    public SystemManager(Scanner scanner) {
        this.scanner = scanner;
        loadData();
    }

    public void addAircraft() {
        int type = readInt("1. Passenger Plane  2. Cargo Plane: ");
        int id = readInt("Enter ID: ");
        String model = readString("Enter Model: ");
        int capacity = readInt("Enter Capacity: ");

        if (type == 1) {
            int seats = readInt("Enter Seat Count: ");
            airport.getHangar().add(new PassengerPlane(id, model, capacity, seats));
        } else {
            double cargo = (double) readInt("Enter Max Cargo Weight: ");
            airport.getHangar().add(new CargoPlane(id, model, capacity, cargo));
        }
        System.out.println("Aircraft added!");
    }

    public void addPilot() {
        pilots.add(new Pilot(readInt("Enter ID: "), readString("Enter Name: ")));
        System.out.println("Pilot added!");
    }

    public void addPassenger() {
        passengers.add(new Passenger(readInt("Enter ID: "), readString("Enter Name: ")));
        System.out.println("Passenger added!");
    }

    public void createFlight() {
        if (airport.getHangar().isEmpty() || pilots.isEmpty()) {
            System.out.println("Error: Add Aircraft and Pilots first!");
            return;
        }

        String fNum = readString("Enter Flight Number: ");

        System.out.println("--- Aircraft List ---");
        for (int i = 0; i < airport.getHangar().size(); i++) {
            System.out.println(i + ". " + airport.getHangar().get(i));
        }
        int acIndex = readInt("Select Aircraft Index: ");
        if (acIndex < 0 || acIndex >= airport.getHangar().size()) {
            System.out.println("Invalid aircraft.");
            return;
        }
        Aircraft plane = airport.getHangar().get(acIndex);

        System.out.println("This flight will depart from: " + plane.getCurrentLocation());

        String dest = readString("Enter Destination (Arrival Location): ");

        System.out.println("--- Pilot List ---");
        for (int i = 0; i < pilots.size(); i++) {
            System.out.println(i + ". " + pilots.get(i).getName());
        }
        Pilot pilot = pilots.get(readInt("Select Pilot Index: "));

        int typeChoice = readInt("1. Domestic  2. International: ");
        FlightType type = (typeChoice == 1) ? FlightType.DOMESTIC : FlightType.INTERNATIONAL;

        Flight f = new Flight(fNum, plane, pilot, new ArrayList<>(), type, dest);
        schedule.getFlights().add(f);
        System.out.println("Flight created! Route: " + plane.getCurrentLocation() + " -> " + dest);
    }

    public void assignFlightToGate() {
        if (schedule.getFlights().isEmpty()) {
            System.out.println("No flights available to assign.");
            return;
        }

        System.out.println("\n--- Available Flights ---");
        for (Flight f : schedule.getFlights()) {
            System.out.println("- " + f.getFlightNumber() + " (" + f.getAircraft().getModel() + ")");
        }

        String fNum = readString("Enter Flight Number to Assign: ");
        Flight f = findFlight(fNum);

        if (f != null) {
            airport.assignFlight(f);
        } else {
            System.out.println("Error: Flight not found.");
        }
    }

    public void boardPassenger() {
        if (passengers.isEmpty() || schedule.getFlights().isEmpty()) {
            System.out.println("Error: Need both passengers and flights registered first.");
            return;
        }

        System.out.println("\n--- Passenger List ---");
        for (Passenger p : passengers) {
            System.out.println("- " + p.getName() + " (ID: " + p.getId() + ")");
        }
        String pName = readString("Enter Passenger Name: ");

        System.out.println("\n--- Flight List ---");
        for (Flight f : schedule.getFlights()) {
            String gateInfo = (f.getGate() == null) ? "[Not assigned]" : "[Gate: " + f.getGate() + "]";
            System.out.println("- " + f.getFlightNumber() + " " + gateInfo);
        }
        String fNum = readString("Enter Flight Number: ");

        Passenger p = null;
        for (Passenger temp : passengers) {
            if (temp.getName().equalsIgnoreCase(pName)) p = temp;
        }
        Flight f = findFlight(fNum);

        if (p == null || f == null) {
            System.out.println("Error: Invalid Passenger Name or Flight Number.");
            return;
        }

        if (f.getGate() == null) {
            System.out.println("Error: Boarding denied. Flight not assigned to a gate.");
            return;
        }

        p.board(f);
        Ticket newTicket = new Ticket(f, p);
        p.getTickets().add(newTicket);
        System.out.println("Boarding Successful!");
        System.out.println("🎫 " + newTicket);
    }

    public void performFlightOperations() {
        if (schedule.getFlights().isEmpty()) {
            System.out.println("No flights available.");
            return;
        }

        System.out.println("\n--- Operational Flights ---");
        for (Flight f : schedule.getFlights()) {
            String gateInfo = (f.getGate() == null) ? "" : " [" + f.getGate() + "]";
            System.out.println("- " + f.getFlightNumber() + " [Status: " + f.getStatus() + "]" + gateInfo);
        }

        Flight f = findFlight(readString("Enter Flight Number: "));
        if (f == null) {
            System.out.println("Flight not found.");
            return;
        }

        int choice = readInt("1. Depart  2. Land: ");
        if (choice == 1) {
            f.depart();
            if (f.getStatus() == FlightStatus.ACTIVE) {
                releaseGateForFlight(f);
            }
        } else if (choice == 2) {
            f.land();
            if (f.getStatus() == FlightStatus.LANDED) {
                schedule.getFlights().remove(f);
                System.out.println("Flight " + f.getFlightNumber() + " has been closed and removed from the schedule.");
            }
        } else {
            System.out.println("Invalid operation.");
        }
    }

    public void viewScheduleSorted() {
        if (schedule.getFlights().isEmpty()) {
            System.out.println("No active flights in schedule.");
            return;
        }

        Collections.sort(schedule.getUpcomingFlights());

        System.out.println("\n--- Current Flight Schedule ---");
        for (Flight f : schedule.getUpcomingFlights()) {
            f.getInfo();
        }
    }

    public void sortPassengers() {
        passengers.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));

        System.out.println("--- Passengers (Sorted by Name) ---");
        for (Passenger p : passengers) {
            System.out.println(p.getName() + " (ID: " + p.getId() + ")");
        }
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private Flight findFlight(String flightNum) {
        for (Flight f : schedule.getFlights()) {
            if (f.getFlightNumber().equalsIgnoreCase(flightNum)) return f;
        }
        return null;
    }

    public void saveSystemData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(airport);
            out.writeObject(schedule);
            out.writeObject(pilots);
            out.writeObject(passengers);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Could not save data.");
        }
    }

    private void loadData() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            //noinspection DuplicatedCode
            airport = new Airport("Sofia Airport", new ArrayList<>(), new ArrayList<>());
            airport.getGates().add(new Gate(1, true));
            airport.getGates().add(new Gate(2, true));
            schedule = new Schedule(new ArrayList<>());
            return;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            airport = (Airport) in.readObject();
            schedule = (Schedule) in.readObject();
            pilots = (List<Pilot>) in.readObject();
            passengers = (List<Passenger>) in.readObject();
            System.out.println("Data loaded from file.");
        } catch (Exception e) {
            System.out.println("Error loading file. Starting fresh.");
            //noinspection DuplicatedCode
            airport = new Airport("Sofia Airport", new ArrayList<>(), new ArrayList<>());
            airport.getGates().add(new Gate(1, true));
            airport.getGates().add(new Gate(2, true));
            schedule = new Schedule(new ArrayList<>());
        }
    }

    private void releaseGateForFlight(Flight f) {
        String gateName = f.getGate();
        if (gateName == null) return;

        try {
            int gateId = Integer.parseInt(gateName.replace("Gate ", ""));
            for (Gate g : airport.getGates()) {
                if (g.getGateID() == gateId) {
                    g.releaseGate();
                    return;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing gate ID for release.");
        }
    }

    public void addCrew() {
        int id = readInt("Enter ID: ");
        String name = readString("Enter Name: ");
        String pos = readString("Enter Position (e.g. Flight Attendant): ");
        cabinCrew.add(new Crew(id, name, pos));
        System.out.println("Crew member added!");
    }

    public void assignCrewToFlight() {
        if (schedule.getFlights().isEmpty() || cabinCrew.isEmpty()) {
            System.out.println("Error: Need flights and crew members first.");
            return;
        }

        System.out.println("--- Crew List ---");
        for (int i=0; i<cabinCrew.size(); i++) {
            System.out.println(i + ". " + cabinCrew.get(i));
        }
        int crewIndex = readInt("Select Crew Index: ");

        System.out.println("--- Flight List ---");
        for(Flight f : schedule.getFlights()) {
            System.out.println("- " + f.getFlightNumber());
        }
        String fNum = readString("Enter Flight Number: ");
        Flight f = findFlight(fNum);

        if (f != null && crewIndex >= 0 && crewIndex < cabinCrew.size()) {
            f.addCrewMember(cabinCrew.get(crewIndex));
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public void viewPassengerHistory() {
        if (passengers.isEmpty()) {
            System.out.println("No passengers registered.");
            return;
        }

        System.out.println("\n--- Select Passenger ---");
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println(i + ". " + passengers.get(i).getName());
        }
        int index = readInt("Select Index: ");

        if (index >= 0 && index < passengers.size()) {
            Passenger p = passengers.get(index);
            System.out.println("\n=== Ticket History for " + p.getName() + " ===");
            if (p.getTickets().isEmpty()) {
                System.out.println("No tickets found.");
            } else {
                for (Ticket t : p.getTickets()) {
                    System.out.println(t);
                }
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }
}