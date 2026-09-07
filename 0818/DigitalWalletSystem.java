class DigitalWallet {
    private String walletId;
    private String owner;
    private int balance;
    private int transactionCount;

    public DigitalWallet(String walletId, String owner, int balance) {
        this.walletId = walletId;
        this.owner = owner;

        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }

        this.transactionCount = 0;
    }

    public boolean deposit(int amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;
        return true;
    }

    public boolean payment(int amount) {
        if (amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        transactionCount++;
        return true;
    }

    public boolean refund(int amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        transactionCount++;
        return true;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getOwner() {
        return owner;
    }

    public int getBalance() {
        return balance;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    @Override
    public String toString() {
        return "錢包編號：" + walletId
                + "，持有人：" + owner
                + "，餘額：" + balance
                + "，交易次數：" + transactionCount;
    }
}

public class DigitalWalletSystem {
    public static void main(String[] args) {
        DigitalWallet wallet =
                new DigitalWallet("W001", "小明", 1000);

        System.out.println("初始資料：");
        System.out.println(wallet);

        System.out.println();

        System.out.println("正常儲值 500：");
        System.out.println("交易結果：" + wallet.deposit(500));
        System.out.println(wallet);

        System.out.println();

        System.out.println("正常付款 800：");
        System.out.println("交易結果：" + wallet.payment(800));
        System.out.println(wallet);

        System.out.println();

        System.out.println("餘額不足付款 1000：");
        System.out.println("交易結果：" + wallet.payment(1000));
        System.out.println(wallet);

        System.out.println();

        System.out.println("負數金額儲值 -100：");
        System.out.println("交易結果：" + wallet.deposit(-100));
        System.out.println(wallet);

        System.out.println();

        System.out.println("退款 200：");
        System.out.println("交易結果：" + wallet.refund(200));
        System.out.println(wallet);
    }
}