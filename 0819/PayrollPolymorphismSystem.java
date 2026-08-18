abstract class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculatePay();
}

class MonthlyEmployee extends Employee {
    private double monthlySalary;

    public MonthlyEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculatePay() {
        return monthlySalary;
    }
}

class HourlyEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public HourlyEmployee(String name, double hourlyRate, int hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

class SalesEmployee extends Employee {
    private double baseSalary;
    private double salesAmount;
    private double commissionRate;

    public SalesEmployee(String name, double baseSalary, double salesAmount, double commissionRate) {
        super(name);
        this.baseSalary = baseSalary;
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }

    @Override
    public double calculatePay() {
        return baseSalary + (salesAmount * commissionRate);
    }
}

public class PayrollPolymorphismSystem {
    public static void main(String[] args) {
        Employee[] employees = new Employee[4];
        employees[0] = new MonthlyEmployee("Alice", 60000);
        employees[1] = new HourlyEmployee("Bob", 200, 120);
        employees[2] = new SalesEmployee("Charlie", 30000, 500000, 0.05);
        employees[3] = new HourlyEmployee("Dave", 250, 100);

        double totalPayroll = 0;
        Employee highestPaidEmployee = null;
        double highestPay = -1;

        for (Employee emp : employees) {
            double pay = emp.calculatePay();
            totalPayroll += pay;
            
            if (pay > highestPay) {
                highestPay = pay;
                highestPaidEmployee = emp;
            }
            
            System.out.println("員工: " + emp.getName() + " | 薪資: " + pay);
        }

        System.out.println("----------------------------------");
        System.out.println("總薪資支出: " + totalPayroll);
        if (highestPaidEmployee != null) {
            System.out.println("最高薪資員工: " + highestPaidEmployee.getName() + " (" + highestPay + ")");
        }
    }
}