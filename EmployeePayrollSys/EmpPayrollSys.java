import java.sql.*;
import java.util.*;

public class PayrollSystem {

    static final String URL =
            "jdbc:mysql://localhost:3306/payroll_system";

    static final String USER = "root";

    static final String PASSWORD = "your_password";

    static Scanner input = new Scanner(System.in);

    public static Connection getConnection() throws Exception {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    public static void createTable() {

        String sql =
        "CREATE TABLE IF NOT EXISTS employees(" +
        "id INT PRIMARY KEY AUTO_INCREMENT," +
        "name VARCHAR(100)," +
        "department VARCHAR(100)," +
        "salary DOUBLE" +
        ")";

        try (
            Connection conn = getConnection();
            Statement stmt = conn.createStatement()
        ) {

            stmt.execute(sql);

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void addEmployee() {

        try {

            input.nextLine();

            System.out.print("Employee Name: ");
            String name = input.nextLine();

            System.out.print("Department: ");
            String department = input.nextLine();

            System.out.print("Salary: ");
            double salary = input.nextDouble();

            String sql =
            "INSERT INTO employees(name, department, salary) VALUES (?, ?, ?)";

            try (
                Connection conn = getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
            ) {

                stmt.setString(1, name);
                stmt.setString(2, department);
                stmt.setDouble(3, salary);

                stmt.executeUpdate();

                System.out.println("Employee Added");
            }

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void displayEmployees() {

        String sql = "SELECT * FROM employees";

        try (
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)
        ) {

            System.out.println("\n=== EMPLOYEES ===");

            while(rs.next()) {

                System.out.println(
                    "ID: " + rs.getInt("id") +
                    " | Name: " + rs.getString("name") +
                    " | Department: " +
                    rs.getString("department") +
                    " | Salary: " +
                    rs.getDouble("salary")
                );
            }

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void updateSalary() {

        try {

            System.out.print("Employee ID: ");
            int id = input.nextInt();

            System.out.print("New Salary: ");
            double newSalary = input.nextDouble();

            String sql =
            "UPDATE employees SET salary=? WHERE id=?";

            try (
                Connection conn = getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
            ) {

                stmt.setDouble(1, newSalary);
                stmt.setInt(2, id);

                int rows = stmt.executeUpdate();

                if(rows > 0) {

                    System.out.println("Salary Updated");

                } else {

                    System.out.println("Employee Not Found");
                }
            }

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void deleteEmployee() {

        try {

            System.out.print("Employee ID: ");
            int id = input.nextInt();

            String sql =
            "DELETE FROM employees WHERE id=?";

            try (
                Connection conn = getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, id);

                int rows = stmt.executeUpdate();

                if(rows > 0) {

                    System.out.println("Employee Deleted");

                } else {

                    System.out.println("Employee Not Found");
                }
            }

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void generatePayslip() {

        try {

            System.out.print("Employee ID: ");
            int id = input.nextInt();

            String sql =
            "SELECT * FROM employees WHERE id=?";

            try (
                Connection conn = getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
            ) {

                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();

                if(rs.next()) {

                    String name =
                            rs.getString("name");

                    String department =
                            rs.getString("department");

                    double salary =
                            rs.getDouble("salary");

                    // PAYROLL CALCULATIONS
                    double tax = salary * 0.10;

                    double bonus = salary * 0.05;

                    double netSalary =
                            salary + bonus - tax;

                    System.out.println(
                            "\n===== PAYSLIP ====="
                    );

                    System.out.println(
                            "Employee: " + name);

                    System.out.println(
                            "Department: " +
                            department);

                    System.out.println(
                            "Basic Salary: " +
                            salary);

                    System.out.println(
                            "Tax Deduction: " +
                            tax);

                    System.out.println(
                            "Bonus: " +
                            bonus);

                    System.out.println(
                            "Net Salary: " +
                            netSalary);

                    System.out.println(
                            "===================\n"
                    );

                } else {

                    System.out.println(
                            "Employee Not Found"
                    );
                }
            }

        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
    }

    
    public static void main(String[] args) {

        createTable();

        while(true) {

            System.out.println("\n=== PAYROLL SYSTEM ===");

            System.out.printf("1. Add Employee%n2. Display Employees%n3. Update Salary%n4. Delete Employee%n5. Generate Payslip%n6. Exit%nChoose an Option: ");
            int choice = input.nextInt();

            if(choice == 1){
		addEmployee();
	    }
            else if(choice == 2){
                displayEmployees();
	    }
            else if(choice == 3){
                updateSalary();
            }
            else if(choice == 4){
                deleteEmployee();
	    }
            else if(choice == 5){
                generatePayslip();
	    }
            else if(choice == 6){
                System.out.println("Goodbye");
                System.exit(0);
	    }
            else{
                System.out.println("Invalid Option");
            }
        }
    }
}
