import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        //total 48 parking spaces available

        int numOfSmallRegularSpaces = input.nextInt();
        int numOfMediumRegularSpaces = input.nextInt();
        int numOfLargeRegularSpaces = input.nextInt();
        int numOfSmallHandicappedSpaces = input.nextInt();
        int numOfMediumHandicappedSpaces = input.nextInt();
        int numOfLargeHandicappedSpaces = input.nextInt();

        ParkingLot parkingLot = new ParkingLot(numOfSmallRegularSpaces,numOfMediumRegularSpaces,numOfLargeRegularSpaces,numOfSmallHandicappedSpaces,numOfMediumHandicappedSpaces,numOfLargeHandicappedSpaces);

//         -> 6 handicapped cars, 8 regular trucks, 3 regular cars
//         -> 1 handicapped truck, 1 regular truck, 1 handicapped truck
//         -> 3 regular bikes, 1 regular car

        System.out.println("Parking Medium Handicapped Cars");
        for(int i=0;i<6;i++) {
            parkingLot.park(new Car(Vehicle.VehicleType.HANDICAPPED));
        }

        System.out.println();

        System.out.println("Parking Large Regular Trucks");
        for(int j=0;j<8;j++) {
            parkingLot.park(new Truck(Vehicle.VehicleType.REGULAR));
        }

        System.out.println();

        System.out.println("Parking Medium Regular Cars");
        for(int k=0;k<3;k++) {
            parkingLot.park(new Car(Vehicle.VehicleType.REGULAR));
        }

        System.out.println();

        System.out.println("Parking Large Handicapped Truck");
        parkingLot.park(new Truck(Vehicle.VehicleType.HANDICAPPED));

        System.out.println();

        System.out.println("Parking Large Regular Truck");
        parkingLot.park(new Truck(Vehicle.VehicleType.REGULAR));
        
        System.out.println();

        System.out.println("Parking Large Handicapped Truck");
        parkingLot.park(new Truck(Vehicle.VehicleType.HANDICAPPED));

        System.out.println();

        System.out.println("Parking Small Regular Bikes");
        for(int k=0;k<3;k++) {
            parkingLot.park(new Bike(Vehicle.VehicleType.REGULAR));
        }

        System.out.println();

        System.out.println("Parking Medium Regular Car");
        parkingLot.park(new Car(Vehicle.VehicleType.REGULAR));

        System.out.println();

        System.out.println("Is the parking lot full? "+parkingLot.getIsParkingLotFull());
        System.out.println();
        System.out.println("What spots are left?\n"+parkingLot.toStringSpotsLeft());

        input.close();
    }
}
