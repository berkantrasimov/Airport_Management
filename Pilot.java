import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pilot implements Serializable {
    private int id;
    private String name;
    private List<Aircraft> qualifiedAircraft;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Aircraft> getQualifiedAircraft() {
        return qualifiedAircraft;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQualifiedAircraft(List<Aircraft> qualifiedAircraft) {
        this.qualifiedAircraft = qualifiedAircraft;
    }

    public Pilot(int id, String name) {
        this.id = id;
        this.name = name;
        this.qualifiedAircraft = new ArrayList<>();
    }

    public void fly(Aircraft a) {
        System.out.println("Pilot " + name + " is now flying aircraft " + a.getModel());
    }
}
