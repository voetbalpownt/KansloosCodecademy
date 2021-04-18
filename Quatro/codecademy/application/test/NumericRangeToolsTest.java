package Quatro.codecademy.application.test;

import Quatro.codecademy.application.utils.Validate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumericRangeToolsTest {

// * @subcontract value within valid range {
// *   @requires 0 <= percentage <= 100;
// *   @ensures \result = true;
// * }

    @Test
    public void testValidatePercentageRequiresPercentage5EnsuresTrue() {
        // Arrange percentage
        int percentage = 7;

        // Validate example percentage
        boolean result = Validate.validatePercentage(percentage); // Should be true

        // Assert
        assertEquals(true, result);
    }

// * @subcontract value out of range low {
// * @requires percentage < 0;
// * @ensures \result = false;
// * }

    @Test
    public void testValidatePercentageRequiresNegativePercentageEnsuresFalse() {
        // Arrange percentage
        int percentage = -10;

        // Validate example percentage
        boolean result = Validate.validatePercentage(percentage); // Should be false

        // Assert
        assertEquals(false, result);
    }

// * @subcontract value out of range high {
// * @requires percentage > 100;
// * @signals \result = false;
// * }

    @Test
    public void testValidatePercentageRequiresPercentage105EnsuresFalse() {
        // Arrange percentage
        int percentage = 122;

        // Validate example percentage
        boolean result = Validate.validatePercentage(percentage); // Should be false

        // Assert
        assertEquals(false, result);
    }
}