abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract int calculatePay();
}

class MonthlyEmployee extends Employee {
    int salary;

    MonthlyEmployee(String name, int salary) {
        super(name);
        this.salary = salary;
    }

    int calculatePay() {
        return salary;
    }
}

class HourlyEmployee extends Employee {
    int hour;
    int payPerHour;

    HourlyEmployee(String name, int hour, int payPerHour) {
        super(name);
        this.hour = hour;
        this.payPerHour = payPerHour;
    }

    int calculatePay() {
        return hour * payPerHour;
    }
}

class SalesEmployee extends Employee {
    int salary;
    int bonus;

    SalesEmployee(String name, int salary, int bonus) {
        super(name);
        this.salary = salary;
        this.bonus = bonus;
    }

    int calculatePay() {
        return salary + bonus;
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {

        Employee[] employees = {
            new MonthlyEmployee("小明", 40000),
            new HourlyEmployee("小華", 80, 200),
            new SalesEmployee("小美", 30000, 10000)
        };

        int total = 0;
        int highest = 0;

        for (int i = 0; i < employees.length; i++) {
            int pay = employees[i].calculatePay();

            System.out.println(employees[i].name + "薪資：" + pay);

            total = total + pay;

            if (pay > highest) {
                highest = pay;
            }
        }

        System.out.println("薪資總額：" + total);
        System.out.println("最高薪資：" + highest);
    }
}