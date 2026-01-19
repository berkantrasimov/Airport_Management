import java.io.Serializable;
import java.util.Random;

public class Ticket implements Serializable {
    private int ticketID;
    private String flightNumber;
    private String passengerName;

    private int seatNumber;
    private double ticketPrice;

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public Ticket(Flight f, Passenger p) {
        this.ticketID = new Random().nextInt(9000) + 1000;
        this.flightNumber = f.getFlightNumber();
        this.passengerName = p.getName();

        this.seatNumber = new Random().nextInt(f.getAircraft().getCapacity()) + 1;

        if (f.getFlightType() == FlightType.INTERNATIONAL) {
            this.ticketPrice = 250.00;
        } else {
            this.ticketPrice = 100.00;
        }
    }

    @Override
    public String toString() {
        return "Ticket #" + ticketID + " | Flight: " + flightNumber +
                " | Seat: " + seatNumber + " | Price: $" + ticketPrice;
    }
}
