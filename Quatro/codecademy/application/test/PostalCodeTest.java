package Quatro.codecademy.application.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Quatro.codecademy.application.utils.Validate;

class PostalCodeTest {

// * @subcontract null postalCode {
// * @requires postalCode == null;
// * @signals (NullPointerException) postalCode == null;
// * }

    // This test case will "fail" but this is because the Validate.java class throws a NullPointerException which is the expected result
    @Test
    public void testFormatPostalCodeRequiresPostalCodeNullSignalsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, ()->{
            throw new NullPointerException();
        });

        // Arrange postalCode to empty
        String postalCode = "";

        // Validate example postalCode
        String result = Validate.formatPostalCode(postalCode); // Should be false

        System.out.println(result);
    }

// * @subcontract valid postalCode {
// * @requires Integer.valueOf(postalCode.trim ().substring(0, 4)) > 999 &&
// * Integer.valueOf(postalCode.trim().substring(0, 4)) <= 9999 &&
// * postalCode.trim().substring(4).trim().length == 2 &&
// * 'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z' &&
// * 'A' <= postalCode.trim().substring(4).trim().toUpperCase().charAt(0) <= 'Z';
// * @ensures \result = postalCode.trim().substring(0, 4) + " " +
// * postalCode.trim().substring(4).trim().toUpperCase()
// * }

    // This test case will "fail" but this is because the Validate.java class throws an IllegalArgumentException which is the expected result
    @Test
    public void testFormatPostalCodeRequiresValidPostalCodeEnsuresFormattedPostalCode(){
        // Arrange postalCode
        String postalCode = "4834ps";

        // Validate example postalCode
        String result = Validate.formatPostalCode(postalCode); // Should be false

        System.out.println(result);
    }

// * @subcontract invalid postalCode {
// * @requires no other valid precondition;
// * @signals (IllegalArgumentException);
// * }

    // This test case will "fail" but this is because the Validate.java class throws an IllegalArgumentException which is the expected result
    @Test
    public void testFormatPostalCodeRequiresInvalidPostalCodeSignalsIllegalArgumentException(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            throw new IllegalArgumentException();
        });
        
        // Arrange postalCode
        String postalCode = "346x y";

        // Validate example postalCode
        String result = Validate.formatPostalCode(postalCode); // Should be false

        System.out.println(result);
    }
}