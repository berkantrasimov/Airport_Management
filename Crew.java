import java.io.Serializable;

public class Crew implements Serializable {
    private String name;
    private String position;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Crew(int id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    public void assistFlight(Flight f) {
        System.out.println("Crew member " + name + " is assisting on flight " + f.getFlightNumber());
    }

    @Override
    public String toString() {
        return name + " [" + position + "] (ID: " + id + ")";
    }
}
