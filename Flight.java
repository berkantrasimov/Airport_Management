import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Flight implements Comparable<Flight>, Serializable {
    private String flightNumber;
    private Aircraft aircraft;
    private Pilot pilot;
    private List<Passenger> passengers;
    private FlightType flightType;

    private String origin;
    private String destination;
    private FlightStatus status;
    private String gate;
    private List<Crew> cabinCrew;

    public Flight(String flightNumber, Aircraft aircraft, Pilot pilot, List<Passenger> passengers, FlightType flightType, String destination) {
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.pilot = pilot;
        this.passengers = passengers;
        this.flightType = flightType;
        this.destination = destination;

        if (aircraft != null) {
            this.origin = aircraft.getCurrentLocation();
        } else {
            this.origin = "Unknown";
        }

        this.status = FlightStatus.SCHEDULED;
        this.gate = null;
        this.cabinCrew = new ArrayList<>();
    }

    public void depart() {
        if (status == FlightStatus.ACTIVE) {
            System.out.println("Error: Flight " + flightNumber + " is already airborne!");
        } else if (status == FlightStatus.LANDED) {
            System.out.println("Error: Flight " + flightNumber + " has already finished!");
        } else {
            boolean isCargo = (aircraft instanceof CargoPlane);
            if (gate == null && !isCargo) {
                System.out.println("Error: Cannot depart! Passenger flights require a gate.");
                return;
            }
            if (!isCargo && cabinCrew.isEmpty()) {
                System.out.println("Error: Cannot depart! No cabin crew assigned.");
                return;
            }
            this.status = FlightStatus.ACTIVE;
            String locationInfo = isCargo ? "Cargo Area" : ("Gate: " + gate);
            System.out.println("Flight " + flightNumber + " has departed from " + origin + " (" + locationInfo + ").");
            if (aircraft != null) aircraft.fly();
        }
    }

    public void land() {
        if (status != FlightStatus.ACTIVE) {
            System.out.println("Error: Flight " + flightNumber + " is not in the air. Cannot land.");
        } else {
            this.status = FlightStatus.LANDED;
            if (aircraft != null) {
                aircraft.setCurrentLocation(destination);
            }
            System.out.println("Flight " + flightNumber + " has landed at " + destination + ".");
        }
    }

    public void getInfo() {
        String gateInfo = gate == null ? (aircraft instanceof CargoPlane ? "Cargo" : "No Gate") : gate;
        System.out.printf("Flight: %-7s | %-10s -> %-10s | %-8s | Status: %s%n",
                flightNumber, origin, destination, gateInfo, status);
    }

    @Override
    public int compareTo(Flight other) {
        return this.flightNumber.compareTo(other.flightNumber);
    }

    public void addCrewMember(Crew c) {
        cabinCrew.add(c);
        System.out.println(c.getName() + " added to flight " + flightNumber);
    }

    public void getCrewInfo() {
        if (cabinCrew.isEmpty()) {
            System.out.println("No cabin crew assigned.");
        } else {
            System.out.println("--- Cabin Crew ---");
            for (Crew c : cabinCrew) {
                System.out.println("- " + c);
            }
        }
    }

    public FlightType getFlightType() { return flightType; }
    public void setFlightType(FlightType flightType) { this.flightType = flightType; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }
    public Pilot getPilot() { return pilot; }
    public void setPilot(Pilot pilot) { this.pilot = pilot; }
    public List<Passenger> getPassengers() { return passengers; }
    public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public FlightStatus getStatus() { return status; }
    public void setStatus(FlightStatus status) { this.status = status; }
    public String getGate() { return gate; }
    public void setGate(String gate) { this.gate = gate; }
    public List<Crew> getCabinCrew() { return cabinCrew; }
    public void setCabinCrew(List<Crew> cabinCrew) { this.cabinCrew = cabinCrew; }
}