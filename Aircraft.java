import java.io.Serializable;

public abstract class Aircraft implements Flyable, Loadable, Serializable {
    protected int id;
    protected String model;
    protected int capacity;
    protected String currentLocation;

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }


    public Aircraft(int id, String model, int capacity) {
        this.id = id;
        this.model = model;
        this.capacity = capacity;
        this.currentLocation = "Sofia";
    }
    @Override
    public void fly() {
        System.out.println("Aircraft " + model + " is flying.");
    }

    @Override
    public void loadCargo(double weight) {
        System.out.println("Loading " + weight + "kg of cargo.");
    }

    @Override
    public String toString() {
        return model + " (ID: " + id + ")";
    }
}
