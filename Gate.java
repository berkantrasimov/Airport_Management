import java.io.Serializable;

public class Gate implements Serializable {
    private int gateID;
    private boolean isOpen;

    public int getGateID() {
        return gateID;
    }

    public void setGateID(int gateID) {
        this.gateID = gateID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Gate(int gateID, boolean isOpen) {
        this.gateID = gateID;
        this.isOpen = isOpen;
    }

    public void assignFlight(Flight f) {
        if (isOpen) {
            this.isOpen = false;
            System.out.println("Flight " + f.getFlightNumber() + " assigned to Gate " + gateID);
        } else {
            System.out.println("Gate " + gateID + " is already occupied!");
        }
    }

    public void releaseGate() {
        this.isOpen = true;
        System.out.println("Gate " + gateID + " has been released and is now open.");
    }
}
