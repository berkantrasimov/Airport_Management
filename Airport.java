import java.io.Serializable;
import java.util.List;

public class Airport implements Serializable {
    private String name;
    private List<Aircraft> hangar;
    private List<Gate> gates;

    public Airport(String name, List<Aircraft> hangar, List<Gate> gates) {
        this.name = name;
        this.hangar = hangar;
        this.gates = gates;
    }

    public void assignFlight(Flight f) {
        if (f.getAircraft() instanceof CargoPlane) {
            System.out.println("Info: Cargo flights use the Cargo Area, not passenger gates.");
            return;
        }

        for (Gate g : gates) {
            if (g.isOpen()) {
                g.assignFlight(f);
                f.setGate("Gate " + g.getGateID());
                return;
            }
        }
        System.out.println("No gates available for Flight " + f.getFlightNumber());
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Aircraft> getHangar() { return hangar; }
    public void setHangar(List<Aircraft> hangar) { this.hangar = hangar; }
    public List<Gate> getGates() { return gates; }
    public void setGates(List<Gate> gates) { this.gates = gates; }
}