abstract class Transport {
    protected String routeName;

    public Transport(String routeName) {
        this.routeName = routeName;
    }

    public abstract int calculateFare(int distance);
}

class Bus extends Transport {

    public Bus(String routeName) {
        super(routeName);
    }

    @Override
    public int calculateFare(int distance) {
        if (distance <= 0) {
            return 0;
        }

        return 15 + distance * 2;
    }
}

class Taxi extends Transport {

    public Taxi(String routeName) {
        super(routeName);
    }

    @Override
    public int calculateFare(int distance) {
        if (distance <= 0) {
            return 0;
        }

        return 85 + distance * 10;
    }
}

public class TransportFareSystem {
    public static void main(String[] args) {
        Transport[] transports = {
                new Bus("淡水"),
                new Bus("台北"),
                new Taxi("淡水到台北"),
                new Taxi("台北到板橋")
        };

        int distance = 10;

        for (Transport transport : transports) {
            System.out.println(
                    transport.routeName
                    + "，距離：" + distance
                    + " 公里，票價："
                    + transport.calculateFare(distance)
            );
        }
    }
}