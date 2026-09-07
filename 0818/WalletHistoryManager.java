final class WalletTransaction {
    private final int sequence;
    private final String type;
    private final int amount;
    private final int balanceAfter;

    WalletTransaction(int sequence, String type, int amount, int balanceAfter) {
        this.sequence = sequence;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    int getSequence() {
        return sequence;
    }

    String getType() {
        return type;
    }

    int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return sequence + " " + type + " " + amount
                + " balance=" + balanceAfter;
    }
}

class DigitalWallet {
    private final String walletId;
    private final String owner;
    private int balance;
    private final WalletTransaction[] transactions;
    private int transactionCount;

    DigitalWallet(String walletId, String owner, int historyCapacity) {
        this.walletId = walletId == null || walletId.isBlank()
                ? "UNKNOWN" : walletId;
        this.owner = owner == null || owner.isBlank()
                ? "Unknown" : owner;
        this.balance = 0;
        this.transactions = new WalletTransaction[Math.max(1, historyCapacity)];
        this.transactionCount = 0;
    }

    boolean deposit(int amount) {
        if (amount <= 0 || transactionCount >= transactions.length) {
            return false;
        }

        balance += amount;
        record("DEPOSIT", amount);
        return true;
    }

    boolean pay(int amount) {
        if (amount <= 0 || amount > balance
                || transactionCount >= transactions.length) {
            return false;
        }

        balance -= amount;
        record("PAY", amount);
        return true;
    }

    boolean refund(int amount) {
        if (amount <= 0 || transactionCount >= transactions.length) {
            return false;
        }

        balance += amount;
        record("REFUND", amount);
        return true;
    }

    WalletTransaction findTransaction(int sequence) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getSequence() == sequence) {
                return transactions[i];
            }
        }

        return null;
    }

    int totalByType(String type) {
        int total = 0;

        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equals(type)) {
                total += transactions[i].getAmount();
            }
        }

        return total;
    }

    boolean transferTo(DigitalWallet target, int amount) {
        if (target == null || amount <= 0 || amount > balance) {
            return false;
        }

        if (transactionCount >= transactions.length) {
            return false;
        }

        if (target.transactionCount >= target.transactions.length) {
            return false;
        }

        balance -= amount;
        target.balance += amount;

        record("TRANSFER_OUT", amount);
        target.record("TRANSFER_IN", amount);

        return true;
    }

    private void record(String type, int amount) {
        transactions[transactionCount] = new WalletTransaction(
                transactionCount + 1, type, amount, balance);
        transactionCount++;
    }

    void printStatement() {
        System.out.println(walletId + " owner=" + owner
                + " balance=" + balance);

        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactions[i]);
        }
    }
}

public class WalletHistoryManager {
    public static void main(String[] args) {
        DigitalWallet wallet1 = new DigitalWallet("W001", "Amy", 5);
        DigitalWallet wallet2 = new DigitalWallet("W002", "Bob", 5);

        wallet1.deposit(1000);
        wallet1.pay(200);
        wallet1.refund(50);

        System.out.println("transfer="
                + wallet1.transferTo(wallet2, 300));

        System.out.println();

        WalletTransaction transaction =
                wallet1.findTransaction(2);

        System.out.println("findTransaction(2)=" + transaction);

        System.out.println("DEPOSIT total="
                + wallet1.totalByType("DEPOSIT"));

        System.out.println("PAY total="
                + wallet1.totalByType("PAY"));

        System.out.println("TRANSFER_OUT total="
                + wallet1.totalByType("TRANSFER_OUT"));

        System.out.println();
        System.out.println("=== Wallet 1 ===");
        wallet1.printStatement();

        System.out.println();
        System.out.println("=== Wallet 2 ===");
        wallet2.printStatement();
    }
}