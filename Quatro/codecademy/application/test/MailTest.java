package Quatro.codecademy.application.test;

import Quatro.codecademy.application.utils.Validate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailToolsTest {

// * @subcontract no mailbox part {
// * @requires !mailAddress.contains("@") ||
// * mailAddress.split("@")[0].length < 1;
// * @ensures \result = false;
// * }

    @Test
    public void testValidateEmailRequiresNoAtlanticEnsuresFalse(){
        // Example email without @
        String mailAddress = "thijsvanrijsbergengmail.com";

        // Validate the example email
        boolean result = Validate.validateEmail(mailAddress); // Should be false

        // Assert
        assertEquals(false, result);

    }

    @Test
    public void testValidateEmailRequiresMoreThanOneAtlanticOrDotEnsuresFalse(){
        // Example emails with multiple @ or .
        String mailAddress1 = "thijs.vanrijsbergen@gmail.com"; // Should be true
        String mailAddress2 = "thijsvanrijsbergen@gmail..com"; // Should be false
        String mailAddress3 = "thijsvanrijsbergen@@gmail.com"; // Should be false


        // Validate the example emails
        boolean result1 = Validate.validateEmail(mailAddress1);
        boolean result2 = Validate.validateEmail(mailAddress2);
        boolean result3 = Validate.validateEmail(mailAddress3);

        // Assert
        assertEquals(true, result1);
        assertEquals(false, result2);
        assertEquals(false, result3);
    }

}