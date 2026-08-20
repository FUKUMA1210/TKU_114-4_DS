interface PricingPolicy {
    int finalPrice(int originalPrice);
}

class StandardPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        return Math.max(0, originalPrice);
    }
}

class VipPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        return Math.max(0, originalPrice) * 85 / 100;
    }
}

class Full2000DiscountPricing implements PricingPolicy {
    @Override
    public int finalPrice(int originalPrice) {
        int price = Math.max(0, originalPrice);

        if (price >= 2000) {
            return price - 300;
        }

        return price;
    }
}

interface NotificationChannel {
    boolean send(String receiver, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || !receiver.contains("@")) {
            return false;
        }

        System.out.println("EMAIL " + receiver + " -> " + message);
        return true;
    }
}

class ConsoleChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        System.out.println("CONSOLE " + receiver + " -> " + message);
        return true;
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public boolean send(String receiver, String message) {
        if (receiver == null || receiver.isBlank()) {
            return false;
        }

        System.out.println("SMS " + receiver + " -> " + message);
        return true;
    }
}

class CheckoutResult {
    private String orderId;
    private int originalPrice;
    private int finalPrice;
    private boolean notificationStatus;

    CheckoutResult(String orderId, int originalPrice,
                   int finalPrice, boolean notificationStatus) {
        this.orderId = orderId;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.notificationStatus = notificationStatus;
    }

    void showResult() {
        System.out.println("訂單：" + orderId);
        System.out.println("原價：" + originalPrice);
        System.out.println("最後價格：" + finalPrice);
        System.out.println("通知狀態：" + notificationStatus);
    }
}

class CheckoutService {
    private final PricingPolicy pricing;
    private final NotificationChannel channel;

    CheckoutService(PricingPolicy pricing, NotificationChannel channel) {
        this.pricing = pricing;
        this.channel = channel;
    }

    CheckoutResult checkout(String orderId, int originalPrice,
                            String receiver) {

        if (orderId == null || orderId.isBlank() || originalPrice < 0) {
            return new CheckoutResult(
                    orderId, originalPrice, 0, false);
        }

        int amount = pricing.finalPrice(originalPrice);

        boolean sent = channel.send(
                receiver,
                "order=" + orderId + ", amount=" + amount);

        return new CheckoutResult(
                orderId, originalPrice, amount, sent);
    }
}

public class FlexibleCheckoutSystem {
    public static void main(String[] args) {

        CheckoutService standardEmail = new CheckoutService(
                new StandardPricing(), new EmailChannel());

        CheckoutService vipSms = new CheckoutService(
                new VipPricing(), new SmsChannel());

        CheckoutService discountConsole = new CheckoutService(
                new Full2000DiscountPricing(), new ConsoleChannel());

        CheckoutService vipEmail = new CheckoutService(
                new VipPricing(), new EmailChannel());

        CheckoutService standardSms = new CheckoutService(
                new StandardPricing(), new SmsChannel());

        CheckoutService discountEmail = new CheckoutService(
                new Full2000DiscountPricing(), new EmailChannel());


        System.out.println("===== 測試 1 =====");
        CheckoutResult result1 =
                standardEmail.checkout(
                        "O100", 1500, "amy@example.com");
        result1.showResult();

        System.out.println();


        System.out.println("===== 測試 2 =====");
        CheckoutResult result2 =
                vipSms.checkout(
                        "O101", 2000, "0912345678");
        result2.showResult();

        System.out.println();


        System.out.println("===== 測試 3 =====");
        CheckoutResult result3 =
                discountConsole.checkout(
                        "O102", 2500, "counter");
        result3.showResult();

        System.out.println();


        System.out.println("===== 測試 4 =====");
        CheckoutResult result4 =
                vipEmail.checkout(
                        "O103", 3000, "bob@example.com");
        result4.showResult();

        System.out.println();


        System.out.println("===== 測試 5 =====");
        CheckoutResult result5 =
                standardSms.checkout(
                        "O104", 800, "0987654321");
        result5.showResult();

        System.out.println();


        System.out.println("===== 測試 6 =====");
        CheckoutResult result6 =
                discountEmail.checkout(
                        "O105", 2200, "invalid");
        result6.showResult();
    }
}