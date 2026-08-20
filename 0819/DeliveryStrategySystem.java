interface DeliveryMethod {
    int calculateFee();
    String getDescription();
}

class HomeDelivery implements DeliveryMethod {

    public int calculateFee() {
        return 100;
    }

    public String getDescription() {
        return "宅配，約 2-3 天送達";
    }
}

class StorePickup implements DeliveryMethod {

    public int calculateFee() {
        return 60;
    }

    public String getDescription() {
        return "超商取貨，約 2-4 天送達";
    }
}

class SelfPickup implements DeliveryMethod {

    public int calculateFee() {
        return 0;
    }

    public String getDescription() {
        return "自取，請到指定地點取貨";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    void showDeliveryInfo() {
        System.out.println("運費：" + deliveryMethod.calculateFee());
        System.out.println("說明：" + deliveryMethod.getDescription());
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {

        OrderService order1 = new OrderService(new HomeDelivery());
        OrderService order2 = new OrderService(new StorePickup());
        OrderService order3 = new OrderService(new SelfPickup());

        System.out.println("宅配");
        order1.showDeliveryInfo();

        System.out.println();

        System.out.println("超商取貨");
        order2.showDeliveryInfo();

        System.out.println();

        System.out.println("自取");
        order3.showDeliveryInfo();
    }
}