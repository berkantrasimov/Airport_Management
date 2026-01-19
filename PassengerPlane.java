public class PassengerPlane extends Aircraft{
    private int maxPassengers;

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public PassengerPlane(int id, String model, int capacity, int maxPassengers) {
        super(id, model, capacity);
        this.maxPassengers = maxPassengers;
    }
}
