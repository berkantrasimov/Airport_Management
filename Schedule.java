import java.io.Serializable;
import java.util.List;

public class Schedule implements Serializable {
    private List<Flight> flights;

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Schedule(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getUpcomingFlights() {
        return flights;
    }
}
