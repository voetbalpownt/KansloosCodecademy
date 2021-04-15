package Quatro.codecademy.application.entity;

import java.sql.SQLException;
import java.time.LocalDate;

import Quatro.codecademy.application.utils.Validate;
import Quatro.codecademy.application.controllers.StudentController;

public class Student {

    private static StudentController studentController = new StudentController(
            "jdbc:sqlserver://localhost;databaseName=Codecademy;integratedSecurity=true;");

    // Add a student to the database and validate email, date and postal code
    public static String addStudent(String email, String name, int dayDate, int monthDate, int yearDate, String gender,
            String country, String city, String address, String postalCode) throws SQLException {
        if (!Validate.validateEmail(email)) {
            return "The given e-mail was not valid!";
        }
        if (!Validate.validateDate(dayDate, monthDate, yearDate)) {
            return "The given date was not valid!";
        }
        if (!Validate.validatePostalCode(postalCode)) {
            return "The given postal code was not valid! Example: (1234 AB)";
        }

        // Set the given date by user to a valid LocalDate format
        LocalDate date = LocalDate.of(yearDate, monthDate, dayDate);

        // Query for adding the given student into the database
        String query = "INSERT INTO Student(EmailAddress, Name, DateOfBirth, Gender, City, Address, Country, PostalCode) VALUES (\'"
                + email + "\',\'" + name + "\',\'" + date + "\',\'" + gender + "\',\'" + city + "\',\'" + address
                + "\',\'" + country + "\',\'" + postalCode + "\')";

        // Check if student is already in the database by comparing the email
        if (studentController.checkDuplicate(email)) {
            return "The e-mail is already assigned to another student!";
        }

        // Add student to database
        studentController.addStudent(query);
        return "Student has been added to the database!";
    }

}
