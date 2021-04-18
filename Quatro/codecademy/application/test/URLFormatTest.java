package Quatro.codecademy.application.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Quatro.codecademy.application.utils.Validate;

public class URLFormatTest {

// * @subcontract Url is correctly formatted
// * @requires URL = https:// or http:// CharCount(Part1)>= 1 . CharCount(Part2)>=
// * 1.CharCount(Part3)>= 1
// * @ensures \result = true

    // Test for checking if leaving uit the http will result in false
    @Test
    public void testvalidateURLrequiresNoHttpEnsuresFalse() {
        // Arrange URL
        String URL = "Github.com";

        // Validate example URL
        boolean result = Validate.validateURL(URL); // Should be false

        // Assert
        assertEquals(false, result);
    }

    // Test for checking if a correct URL will result in true
    @Test
    public void testvalidateURLCorectURLEnsuresTrue() {
        // Arrange URL
        String URL = "https://www.github.com";

        // Validate example URL
        boolean result = Validate.validateURL(URL); // Should be true

        // Assert
        assertEquals(true, result);
    }

    // Test for checking if leaving out the .com will result in false
    @Test
    public void testValidateURLRequiresToplvlDomainNameEnsuresFalse() {
        // Arrange URL
        String URL = "https://www.github";

        // Validate example URL
        boolean result = Validate.validateURL(URL); // Should be false

        // Assert
        assertEquals(false, result);
    }

    // Test for checking if leaving out the domain name will result in false
    @Test
    public void testValidateURLNoDomainNameEnsuresFalse() {
        // Arrange URL
        String URL = "https://www..com";

        // Validate example URL
        boolean result = Validate.validateURL(URL); // Should be false

        // Assert
        assertEquals(false, result);
    }
}