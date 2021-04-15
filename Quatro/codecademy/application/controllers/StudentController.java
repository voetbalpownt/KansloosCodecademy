package Quatro.codecademy.application.controllers;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController extends Database {

    public StudentController(String connectionUrl) {
        super(connectionUrl);
    }

    // get all students
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

    // check for duplicate students 
 
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

    // get name from student by email
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

    // get any info (address, postalcode, etc.) by email
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

    // get all certificates  by selected email
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

    // add student 
    public void addStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }

    // remove student
    public void removeStudent(String query) throws SQLException {
        statement.executeUpdate(query);
    }
}