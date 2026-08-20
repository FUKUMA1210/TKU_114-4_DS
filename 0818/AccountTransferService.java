class Account {
    private String name;
    private int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class TransferService {

    public boolean transfer(Account source, Account target, int amount) {

        if (source == null || target == null) {
            return false;
        }

        if (source == target) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }

        if (source.getBalance() < amount) {
            return false;
        }

        source.withdraw(amount);
        target.deposit(amount);

        return true;
    }
}

public class AccountTransferService {

    public static void main(String[] args) {

        Account a = new Account("小明", 1000);
        Account b = new Account("小華", 500);

        TransferService service = new TransferService();

        System.out.println(service.transfer(a, b, 300));
        System.out.println(a.getName() + "餘額：" + a.getBalance());
        System.out.println(b.getName() + "餘額：" + b.getBalance());

        System.out.println(service.transfer(a, b, 1000));
        System.out.println(a.getName() + "餘額：" + a.getBalance());
        System.out.println(b.getName() + "餘額：" + b.getBalance());

        System.out.println(service.transfer(a, a, 100));

        System.out.println(service.transfer(a, null, 100));
    }
}