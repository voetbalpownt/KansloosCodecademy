package Quatro.codecademy.application.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Class to validate inputted values
public class Validate {

    // Regex to give a base for a valid email
    private static final String regexEmail = "^[a-zA-Z0-9_.!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n"
            + ")*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    // Regex to give a base for a valid URL
    private static final String regexURL = "https?://" + "[a-zA-Z0-9@:%._\\+~#?&//=]" + "{1,256}\\.[a-z]"
            + "{1,256}\\b([-a-zA-Z0-9@:%" + "._\\+~#?&//=]*)" + "[a-zA-Z0-9@:%._\\+~#?&//=]";

    // Regex to give a base for a valid postal code
    private static final String regexPostalCode = "[1-9][0-9]{3}" + " " + "[A-Z]{2}";

    // Check the given email to see if it is valid
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Check the given postal code to see if it is valid
    public static boolean validatePostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(regexPostalCode);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }

    // Format the input for postalCode.
    public static String formatPostalCode(String postalCode) {
        // Throws NullPointerException when postalCode is empty
        if (postalCode.equals("")) {
            throw new NullPointerException("Postal code was empty");
        }
        postalCode = postalCode.replace(" ", "").toUpperCase();
        Pattern pattern = Pattern.compile(regexPostalCode);
        Matcher matcher = pattern.matcher(postalCode);
        if (matcher.matches()) {
            return new StringBuilder(postalCode).insert(4, " ").toString();
        }
        // Throws IllegalArgumentException when the given postalCode is invalid
        throw new IllegalArgumentException("Postal code was invalid: " + postalCode);
    }

    // Check the given URL to see if it is valid
    public static boolean validateURL(String url) {
        Pattern pattern = Pattern.compile(regexURL);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    // Check the given rating to see if it is between 1 and 10
    public static boolean validateRating(double rating) {
        return rating >= 1 && rating <= 10;

    }

    // Check the given rating to see if it is between 1 and 100
    public static boolean validatePercentage(double percentage) {
        return percentage >= 1 && percentage <= 100;
    }

    // Check the given date to see if it is valid
    public static boolean validateDate(int day, int month, int year) {

        if (day > 31 || day < 1 || month < 1 || month > 12 || year < 0) {
            return false;
        }

        // Check the month February (it can contain either 28 or 29 days depending if it's a leap year)
        if (month == 2) {
            if (leapYear(year)) {
                if (day > 29) {
                    return false;
                }
            } else {
                if (day > 28) {
                    return false;
                }
            }
        }

        // Check to see if months with only 30 days does not exceed 30 days
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (day > 30) {
                    return false;
                }
                break;
            // If the amount of days exceeds 31 it will always give return false, because of the first if statement
        }
        return true;
    }

    // Check the given year to see if it is a leap year
    public static boolean leapYear(int year) {
        if (year % 100 == 0) {
            return year % 400 == 0;
        }
        return year % 4 == 0;
    }
}
