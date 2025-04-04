package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("12345")); // numbers only
        assertFalse(Name.isValidName("peter the 2nd")); // alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr second")); // long names
    }

    @Test
    public void equals() {
        Name smallCapsName = new Name("Valid Name");

        // same values -> returns true
        assertTrue(smallCapsName.equals(new Name("valid name")));

        //same value but different capitalisation -> returns true
        assertTrue(smallCapsName.equals(new Name("VALID NAME")));

        // same object -> returns true
        assertTrue(smallCapsName.equals(smallCapsName));

        // null -> returns false
        assertFalse(smallCapsName.equals(null));

        // different types -> returns false
        assertFalse(smallCapsName.equals(5.0f));

        // different values -> returns false
        assertFalse(smallCapsName.equals(new Name("Other Valid Name")));
    }
}
