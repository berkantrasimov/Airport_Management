import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Passenger implements Serializable {
    private int id;
    private String name;
    private List<Ticket> tickets;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Passenger(int id, String name) {
        this.id = id;
        this.name = name;
        this.tickets = new ArrayList<>();
    }

    public void board(Flight f) {
        f.getPassengers().add(this);
        System.out.println("Passenger " + name + " has boarded flight " + f.getFlightNumber());
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}
