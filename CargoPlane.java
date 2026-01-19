public class CargoPlane extends Aircraft{
    private double maxCargoWeight;

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(double maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    public CargoPlane(int id, String model, int capacity, double maxCargoWeight) {
        super(id, model, capacity);
        this.maxCargoWeight = maxCargoWeight;
    }
}
