import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/";
        String user = "user";
        String password = "password";

        Connection con = null;
        Statement stm = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            stm = con.createStatement();

            // Create Database==============
            System.out.println("Write a database Name : ");
            String dbName = sc.next();
            String createDb = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";

            stm.executeUpdate(createDb);
            System.out.println("Database created || Already exists.");

            // Connect to Database===========
            con = DriverManager.getConnection(url + dbName, user, password);
            stm = con.createStatement();

            // Create Table============
            System.out.println("Enter Table Name : ");
            String tableName = sc.next();
            String createTable = "CREATE TABLE IF NOT EXISTS " + tableName + "(ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(40), AGE INT, GRADE VARCHAR(10));";

            stm.executeUpdate(createTable);
            System.out.println("Table created || Already exists.");

            // Insert data ============
            System.out.println("Enter Values -------------------");
            System.out.println("Enter Student name : ");
            String name = sc.next();
            System.out.println("Enter age : ");
            int age = sc.nextInt();
            System.out.println("Enter Grade : ");
            String grade = sc.next();
            String insertData = "INSERT INTO " + tableName + "(NAME,AGE,GRADE) VALUES('" + name + "'," + age + ", '" + grade + "');";
            stm.executeUpdate(insertData);
            System.out.println("Data inserted into Table");

            // Read data from table===========
            String retrieveData = "SELECT * FROM " + tableName + ";";
            ResultSet rs = stm.executeQuery(retrieveData);
            while (rs.next()) {
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Grade : " + rs.getString("grade"));
            }
            con.close();


        } catch (SQLException e) {
            System.out.println("An exception occured: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}