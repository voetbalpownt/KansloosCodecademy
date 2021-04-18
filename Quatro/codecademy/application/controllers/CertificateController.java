package Quatro.codecademy.application.controllers;

import java.text.DecimalFormat;

import Quatro.codecademy.application.logic.Gender;

public class CertificateController extends Database {

    public CertificateController(String connectionUrl) {
        super(connectionUrl);
    }

    // Method for seeing what percentage of a certain Gender receives a Certificate
    public String getPercentage(Gender gender) {
        try {
            double count = 0.0;
            double countcompleted = 0.0;
            connectDatabase();
            String SQL = "SELECT COUNT(StudentEmailAddress) AS Count FROM Certificate WHERE StudentEmailAddress IN (SELECT EmailAddress FROM Enrollment WHERE EmailAddress IN(SELECT EmailAddress FROM Student WHERE Gender = \'" + gender + "\'))";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                countcompleted = resultSet.getInt("Count");
            }
            SQL = "SELECT COUNT(EmailAddress) AS Count FROM Enrollment WHERE EmailAddress IN(SELECT EmailAddress FROM Student WHERE Gender = \'" 
                + gender + "\')";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                count = resultSet.getInt("Count");
            }
            DecimalFormat format = new DecimalFormat("#0.0");
            String formatted = format.format((countcompleted / count) * 100);
            if (formatted.equals("NaN")) {
                return "0";
            }
            return formatted;
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return "0";
    }

    // Get the 3 top Certificates by CourseName
    public String getTop() {
        String top = "";
        try {
            connectDatabase();
            String SQL = "SELECT TOP 3 CourseName FROM Certificate GROUP BY CourseName";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            int i = 1;
            if (resultSet.next()) {
                top = top + i + ": " + resultSet.getString("CourseName") + "\n";
                i++;
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return top;
    }

    // Get the count from Certificate by CourseName
    public Integer getCount(String courseName) {
        try {
            connectDatabase();
            String SQL = "SELECT COUNT(CourseName) AS Count FROM Certificate WHERE CourseName = \'" + courseName + "\'";
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                return resultSet.getInt("Count");
            }
        } catch (Exception e) {
            System.out.println("ERROR:\n\n" + e);
        }
        return 0;
    }

 

}