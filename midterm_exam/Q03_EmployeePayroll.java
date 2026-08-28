import java.util.List;

public class Q03_EmployeePayroll {

    public static void main(String[] args) {

        List<Employee> employees = List.of(
                new SalariedEmployee("E1", "Amy", 50000),
                new HourlyEmployee("E2", "Bo", 170, 200)
        );

        System.out.println(employees.get(0).summary());
        System.out.println(employees.get(1).summary());
        System.out.println(totalPayroll(employees));
    }

    public static abstract class Employee {

        protected String id;
        protected String name;

        protected Employee(String id, String name) {

            if (id == null || id.trim().equals("")) {
                throw new IllegalArgumentException();
            }

            if (name == null || name.trim().equals("")) {
                throw new IllegalArgumentException();
            }

            this.id = id.trim();
            this.name = name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public abstract int monthlyPay();

        public String summary() {
            return id + "|" + name + "|" + monthlyPay();
        }
    }

    public static class SalariedEmployee extends Employee {

        private int salary;

        public SalariedEmployee(String id, String name, int salary) {
            super(id, name);

            if (salary < 0) {
                this.salary = 0;
            } else {
                this.salary = salary;
            }
        }

        @Override
        public int monthlyPay() {
            return salary;
        }
    }

    public static class HourlyEmployee extends Employee {

        private int hours;
        private int hourlyRate;

        public HourlyEmployee(String id, String name, int hours, int hourlyRate) {
            super(id, name);

            if (hours < 0) {
                this.hours = 0;
            } else {
                this.hours = hours;
            }

            if (hourlyRate < 0) {
                this.hourlyRate = 0;
            } else {
                this.hourlyRate = hourlyRate;
            }
        }

        @Override
        public int monthlyPay() {

            if (hours <= 160) {
                return hours * hourlyRate;
            }

            double normalPay = 160 * hourlyRate;
            double overtimePay = (hours - 160) * hourlyRate * 1.5;

            return (int) (normalPay + overtimePay);
        }
    }

    public static int totalPayroll(List<Employee> employees) {

        if (employees == null) {
            return 0;
        }

        int payrollTraceK4 = 0;

        for (Employee employee : employees) {
            if (employee != null) {
                payrollTraceK4 += employee.monthlyPay();
            }
        }

        return payrollTraceK4;
    }
}