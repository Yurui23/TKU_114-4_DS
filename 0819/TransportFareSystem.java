abstract class Transport {
    String routeName;

    public Transport(String routeName) {
        this.routeName = (routeName == null || routeName.trim().isEmpty()) ? "Unknown Route" : routeName;
    }

    abstract int calculateFare(int distance);
}

class Bus extends Transport {
    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    int calculateFare(int distance) {
        return Math.max(0, distance) * 15;
    }
}

class Taxi extends Transport {
    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    int calculateFare(int distance) {
        return 85 + (Math.max(0, distance) * 25);
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = new Transport[4];
        transports[0] = new Bus("市區公車 300 號");
        transports[1] = new Bus("");
        transports[2] = new Taxi("台灣大車隊");
        transports[3] = new Taxi(null);

        int[] distances = {10, -5};

        for (Transport transport : transports) {
            for (int distanceToTravel : distances) {
                System.out.print("路線/載具: " + transport.routeName);
                System.out.println(" | 距離: " + distanceToTravel + " | 票價: " + transport.calculateFare(distanceToTravel));
            }
        }
    }
}