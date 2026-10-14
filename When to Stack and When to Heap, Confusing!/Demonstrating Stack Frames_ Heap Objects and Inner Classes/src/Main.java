import java.util.Scanner;

class Employee {
    int salary;

    Employee(int salary) {
        this.salary = salary;
    }

    void display(int bonus) {
        System.out.println();
        System.out.println("Inside display() Method:");
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);

        // A local inner class is declared inside the display() method.
        class Report {
            void show() {
                System.out.println("Report: Employee salary is " + salary);
            }
        }

        Report report = new Report();
        System.out.println();
        System.out.println("Report Object Created");
        System.out.println("Report Identity Hash Code: "
                + System.identityHashCode(report));
        report.show();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter salary: ");
        int salary = input.nextInt();

        System.out.print("Enter bonus: ");
        int bonus = input.nextInt();

        Employee employee = new Employee(salary);

        System.out.println();
        System.out.println("Employee Object Created");
        System.out.println("Employee Identity Hash Code: "
                + System.identityHashCode(employee));

        employee.display(bonus);

        System.out.println();
        System.out.println("Memory Concept:");
        System.out.println("Employee object -> Heap");
        System.out.println("Report object -> Heap");
        System.out.println("salary -> Instance field");
        System.out.println("bonus -> Local variable in display()");
        System.out.println("show() -> Executes in its own method frame");

        input.close();
    }
}
