public class ParkingSpace implements Space {
    private Vehicle.VehicleType type;
    private Vehicle.VehicleSize vehicleSize;
    private boolean isTaken;

    public ParkingSpace(Vehicle.VehicleSize vehicleSize, Vehicle.VehicleType type) {
        this.vehicleSize = vehicleSize;
        this.type = type;
        this.isTaken = false;
    }

    public Vehicle.VehicleSize getSize() {
        return this.vehicleSize;
    }

    public Vehicle.VehicleType getType() {
        return this.type;
    }

    public boolean getIsTaken() {
        return this.isTaken;
    }

    public void setIsTaken(boolean isTaken) {
         this.isTaken = isTaken;
    }

    @Override
    public String toString() {
        return "ParkingSpace [vehicleSize=" + vehicleSize + ", type=" + type + ", isTaken=" + isTaken + "]";
    }
}