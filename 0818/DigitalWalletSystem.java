class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private int count;

    public DigitalWallet(String walletId, String owner) {
        this.walletId = walletId;
        this.owner = owner;
        this.balance = 0;
        this.count = 0;
    }

    void recharge(int money) {
        if (money > 0) {
            balance += money;
            System.out.println("加值"+money+"成功，目前金額:"+balance);
            count++;
        } else {
            System.out.println("儲值失敗，儲值金額須大於0");
        }
    }

    void pay(int money) {
        if (money < 0) {
            System.out.println("金額小於0交易失敗");
        } else if (balance < money) {
            System.out.println("餘額不足，交易失敗");
        } else {
            balance -= money;
            System.out.println("交易成功，目前餘額:"+balance);
            count++;
        }
    }

    void refund(int money) {
        if (money > 0) {
            balance += money;
            System.out.println("已退款金額:"+money+"目前餘額:"+balance);
            count++;
        } else {
            System.out.println("退款金額須大於0");
        }
    }

    void transCount() {
        System.out.println("交易次數:"+count);
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet = new DigitalWallet("01", "AA");

        System.out.println("=== 正常儲值 ===");
        wallet.recharge(1000);
        System.out.println();

        System.out.println("=== 正常付款 ===");
        wallet.pay(300);
        System.out.println();

        System.out.println("=== 餘額不足 ===");
        wallet.pay(1000);
        System.out.println();

        System.out.println("=== 負數金額 ===");
        wallet.recharge(-500);
        wallet.pay(-100);
        wallet.refund(-200);
        System.out.println();

        System.out.println("=== 退款 ===");
        wallet.refund(200);
        System.out.println();
        
        System.out.println("=== 交易次數 ===");
        wallet.transCount();
    }
}