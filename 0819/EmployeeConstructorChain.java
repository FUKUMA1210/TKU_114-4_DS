abstract class EmployeeBase {
    protected String id;
    protected String name;

    public EmployeeBase(String id, String name) {
        this.id = id;
        this.name = name;

        System.out.println("EmployeeBase constructor");
    }

    public abstract int calculatePay();
}

class FullTimeEmployee extends EmployeeBase {
    private int salary;

    public FullTimeEmployee(
            String id,
            String name,
            int salary) {

        super(id, name);

        if (salary < 0) {
            this.salary = 0;
        } else {
            this.salary = salary;
        }

        System.out.println("FullTimeEmployee constructor");
    }

    @Override
    public int calculatePay() {
        return salary;
    }
}

class PartTimeEmployee extends EmployeeBase {
    private int hourlyPay;
    private int hours;

    public PartTimeEmployee(
            String id,
            String name,
            int hourlyPay,
            int hours) {

        super(id, name);

        if (hourlyPay < 0) {
            this.hourlyPay = 0;
        } else {
            this.hourlyPay = hourlyPay;
        }

        if (hours < 0) {
            this.hours = 0;
        } else {
            this.hours = hours;
        }

        System.out.println("PartTimeEmployee constructor");
    }

    @Override
    public int calculatePay() {
        return hourlyPay * hours;
    }
}

public class EmployeeConstructorChain {
    public static void main(String[] args) {

        System.out.println("建立正職員工：");

        FullTimeEmployee fullTime =
                new FullTimeEmployee(
                        "E001",
                        "Amy",
                        40000);

        System.out.println();

        System.out.println("建立兼職員工：");

        PartTimeEmployee partTime =
                new PartTimeEmployee(
                        "E002",
                        "Bob",
                        200,
                        80);

        System.out.println();

        System.out.println("正職薪資："
                + fullTime.calculatePay());

        System.out.println("兼職薪資："
                + partTime.calculatePay());
    }
}