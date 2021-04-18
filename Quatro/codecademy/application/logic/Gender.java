package Quatro.codecademy.application.logic;


public enum Gender {

    // Enum for the gender of a student
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private String genderName;

    // Constructor for Gender
    Gender(String genderName) {
        this.genderName = genderName;
    }

    // For displaying the gender
    public String toString() {
        return this.genderName;
    }
}
