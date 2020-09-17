package sample;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Amanda Yoresh
 *
 */
public class PasswordCheckerTest_STUDENT {

    ArrayList<String> passToCheck;
    String pass1, pass2;

    @Before
    public void setUp() throws Exception {

        String passString[] = {"Abe4", "AbcDefghijkl2!", "abcdefghij", "abcdeFghiklL!", "AAbcdefghi!", "Abcdefghij!34"};
        passToCheck = new ArrayList<String>();
        passToCheck.addAll(Arrays.asList(passString));
    }

    @After
    public void tearDown() throws Exception {

        passToCheck = null;

    }

    /**
     * Test if the password is less than 8 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("AbcDefG123!"));
            PasswordCheckerUtility.isValidPassword("abD1!");
            assertTrue("LengthException not thrown", false);
        } catch(LengthException x){
            assertTrue("Exception thrown", true);
    }
        catch(Exception x){
            assertTrue("Threw other exceptions as well", false);
        }
        //fail("Not implemented by student yet");
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("AbcDefG123!"));
            PasswordCheckerUtility.isValidPassword("abcdef1!");
            assertTrue("NoUpperAlpha not thrown", false);
        } catch(NoUpperAlphaException x){
            assertTrue("Exception thrown", true);
        }
        catch(Exception x){
            assertTrue("Threw other exceptions as well", false);
        }
        //fail("Not implemented by student yet");
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("AbcDefG123!"));
            PasswordCheckerUtility.isValidPassword("ABCDEF123!");
            assertTrue("NoLowerAlpha not thrown", false);
        } catch(NoLowerAlphaException x){
            assertTrue("Exception thrown", true);
        }
        catch(Exception x){
            assertTrue("Threw other exceptions as well", false);
        }
        //fail("Not implemented by student yet");
    }
    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsWeakPassword()
    {
        boolean passStrength;
        try{
            assertEquals(true, PasswordCheckerUtility.isValidPassword("ABCDeF12!"));
            passStrength = PasswordCheckerUtility.isWeakPassword("ABCDeF12!");
            assertTrue(passStrength);
        } catch(Exception x){
            assertTrue("Threw wrong exception", false);
        }
        //fail("Not implemented by student yet");
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence()
    {
        try{
            assertEquals(true, PasswordCheckerUtility.isValidPassword("ABC123!!!abc123"));
            PasswordCheckerUtility.isValidPassword("aaaaabcdefg123!");
            assertTrue("did not throw exception", false);
        } catch(InvalidSequenceException x){
            assertTrue("threw correct exception", true);
        } catch (Exception x){
            assertTrue("threw other exception(s)", false);
        }
        //fail("Not implemented by student yet");
    }

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit()
    {
        try{
            assertTrue(PasswordCheckerUtility.isValidPassword("AbcDefG123!"));
            PasswordCheckerUtility.isValidPassword("ABCabc!abcabcabc!");
            assertTrue("Nodigitexception not thrown", false);
        } catch(NoDigitException x){
            assertTrue("no digit exception thrown", true);
        }
        catch(Exception x){
            assertTrue("Threw other exceptions as well", false);
        }
        //fail("Not implemented by student yet");
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful()
    {
        try{
            assertEquals(true, PasswordCheckerUtility.isValidPassword("ABCabc123!!!abc"));
        } catch(Exception x){
            assertTrue("threw an exception", false);
        }
        //fail("Not implemented by student yet");
    }

    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testInvalidPasswords() {
        ArrayList<String> resultsOfCheck;
        resultsOfCheck = PasswordCheckerUtility.getInvalidPasswords(passToCheck);
        String nextRes = "", passtoCheck = "";
        int index = 0;
        Scanner scanned;

        while(index < 6){
            scanned = new Scanner(passToCheck.get(index));

            if (index == 0) {
                passtoCheck = "abcD13!!abcdef";
            }   else if (index == 1){
                passtoCheck = "abcdefghi123!";
            } else if(index == 2){
                passtoCheck =  "ABCdefghijkl!!";
            } else if(index == 3){
                passtoCheck = "ABCDEFGHI123!!";
            } else if(index == 4){
                passtoCheck = "aD1!";
            } else if(index == 5){
                passtoCheck = "abCCCdefghi123!!";
            }
            assertEquals(scanned.next(), passtoCheck);
            nextRes = scanned.nextLine().toLowerCase();

            if (index == 0) {
                assertTrue(nextRes.contains("special"));
            }   else if (index == 1){
                assertTrue(nextRes.contains("uppercase"));
            } else if(index == 2){
                assertTrue(nextRes.contains("digit"));
            } else if(index == 3){
                assertTrue(nextRes.contains("lowercase"));
            } else if(index == 4){
                assertTrue(nextRes.contains("long"));
            } else if(index == 5){
                assertTrue(nextRes.contains("same"));
            }

            index++;

        }

        //fail("Not implemented by student yet");
    }

}