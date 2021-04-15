package Quatro.codecademy.application.logic;

public enum Gender {

    // Enum for the gender of a student
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private String genderName;

    Gender(String displayName) {
        this.genderName = displayName;
    }

    public String toString() {
        return this.genderName;
    }
}
