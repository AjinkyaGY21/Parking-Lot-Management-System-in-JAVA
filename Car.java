public class Car implements Vehicle {
    private VehicleType type;

    public Car(VehicleType type) {
        this.type = type;
    }

    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

    public VehicleType getType() {
        return this.type;
    }
}
