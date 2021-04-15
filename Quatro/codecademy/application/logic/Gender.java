package Quatro.codecademy.application.logic;

public enum Gender {

    // Enum for the gender of a student
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String toString() {
        return this.genderName;
    }
}
