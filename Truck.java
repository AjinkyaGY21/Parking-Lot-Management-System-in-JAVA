public class Truck implements Vehicle {
    private VehicleType type;

    public Truck(VehicleType type) {
        this.type = type;
    }

    public VehicleSize getSize() {
        return VehicleSize.LARGE;
    }

    public VehicleType getType() {
        return this.type;
    }
}
