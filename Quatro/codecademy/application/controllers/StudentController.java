package Quatro.codecademy.application.controllers;
import java.sql.SQLException;
import java.util.ArrayList;

// Class for everything related to the students
public class StudentController extends Database {

    // ConnectionUrl from database.java is given
    public StudentController(String connectionUrl) {
        super(connectionUrl);
    }

    // Get all students from the database
    public ArrayList<String> getStudents() {
        ArrayList<String> results = new ArrayList<>();
        try {
            connectDatabase();
            String SQL = "SELECT * FROM Student";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                results.add(resultSet.getString("EmailAddress"));
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    // Check for duplicate students 
    public boolean checkDuplicate(String email) {
        try {
            connectDatabase();
            String SQL = "SELECT EmailAddress FROM Student WHERE EmailAddress = \'" + email + "\'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return false;
    }

    // Get the student name from their e-mail
    public String getName(String email) {
        try {
            connectDatabase();
            String SQL = "SELECT Name FROM Student WHERE EmailAddress = \'" + email + "\'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                return resultSet.getString("Name");
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return "";
    }

    // Get any info (address, postalcode, etc.) by email
    public String getInfoByEmail(String email, String item) {
        try {
            connectDatabase();
            String SQL = "SELECT " + item + " FROM Student WHERE EmailAddress = \'" + email + "\'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                return resultSet.getString(item);
            }

        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return "";
    }

    // Get all certificates by given email
    public String getCertificatesByEmail(String email) {
        String results = "";
        try {
            connectDatabase();
            String SQL = "SELECT * FROM Certificate WHERE StudentEmailAddress = \'" + email + "\'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                results = results + "Grade: " + resultSet.getDouble("Grade") + " | Course: "+ resultSet.getString("CourseName") + " | Date: " + resultSet.getDate("EnrollmentDate") + "\n";
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return results;
    }

    // Add a student to the database 
    public void addStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }

    // Remove a student from the database
    public void removeStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }
}