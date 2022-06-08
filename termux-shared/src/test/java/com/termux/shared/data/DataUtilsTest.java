package com.termux.shared.data;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataUtilsTest {
    @Before
    public void setUp() {
    }

    /**
    * Purpose : Check input text is Null
    * Input : getTruncatedCommandOutput (text: null)
    * Expected :
    *   (text: null) => null
    */
    @Test
    public void testGetTruncatedCommandOutputNull() {
        assertNull(DataUtils.getTruncatedCommandOutput(null, DataUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES, true, false, true ));
    }

    /**
     * Purpose : Check result when truncated function with prefix = "(truncated) "
     * Check Target : getTruncatedCommandOutput
     * Expected :
     *      (text: "Hello", maxSize: 10, fromEnd: true, addPrefix: true) => "Hello"
     *      (text: "Hello", maxSize: 15, fromEnd: true, addPrefix: true) => "(truncated) ls "
     *      (text: "Hello", maxSize: 15, fromEnd: false, addPrefix: true) => "(truncated) -al"
     */
    @Test
    public void testGetTruncatedCommandOutputWithPrefix() {
        String test = "ls -al";
        int maxSize1 = 10;
        int maxSize2 = 15;

        assertEquals(test, DataUtils.getTruncatedCommandOutput(test, maxSize1, true, false, true));

        String expected = "(truncated) ls ";
        assertEquals(expected, DataUtils.getTruncatedCommandOutput(test, maxSize2, true, false, true));

        expected = "(truncated) -al";
        assertEquals(expected, DataUtils.getTruncatedCommandOutput(test, maxSize2, false, false, true));
    }

    /**
     * Purpose : Check result when truncated function without prefix"
     * Check Target : getTruncatedCommandOutput
     * Expected :
     *      (text: "pip freeze > req.txt", maxSize: 30, fromEnd: true, addPrefix: false) => "pip freeze > req.txt"
     *      (text: "pip freeze > req.txt", maxSize: 20, fromEnd: true, addPrefix: false) => "pip freeze > req.txt"
     *      (text: "pip freeze > req.txt", maxSize: 15, fromEnd: true, addPrefix: false) => "pip freeze > re"
     *      (text: "pip freeze > req.txt", maxSize: 15, fromEnd: false, addPrefix: false) => "reeze > req.txt"
     */
    @Test
    public void testGetTruncatedCommandOutputWithoutPrefix() {
        String test = "pip freeze > req.txt";
        int maxSize1 = 15;
        int maxSize2 = 20;
        int maxSize3 = 30;

        assertEquals(test, DataUtils.getTruncatedCommandOutput(test, maxSize3, true, false, false));
        assertEquals(test, DataUtils.getTruncatedCommandOutput(test, maxSize2, true, false, false));


        String expected = "pip freeze > re";
        assertEquals(expected, DataUtils.getTruncatedCommandOutput(test, maxSize1, true, false, false));

        expected = "reeze > req.txt";
        assertEquals(expected, DataUtils.getTruncatedCommandOutput(test, maxSize1, false, false, false));
    }

    /**
     * Purpose : Check result when command has NewLine
     * Check Target : getTruncatedCommandOutput
     * Expected :
     *      (text: "abcde\n1234", maxSize: 7, fromEnd: false, oneNewLine: true, addPrerix: false) => "1234"
     *      (text: "abcde\n1234", maxSize: 3, fromEnd: false, oneNewLine: true, addPrerix: false) => "234"
     */
    @Test
    public void testGetTruncatedCommandOutputWithNewLine() {
        String test = "abcde\n1234";
        int maxSize = 7;

        String expected = "1234";
        assertEquals(expected, DataUtils.getTruncatedCommandOutput(test, maxSize, false, true, false));

        maxSize = 3;
        expected = "234";
        assertEquals(expected, DataUtils.getTruncatedCommandOutput(test, maxSize, false, true, false));
    }

    /**
     * Purpose : Check array changed when array is null of length is zero
     * Check Target : replaceSubStringsInStringArrayItems
     * Expected :
     *      (array: null) => array not changed
     *      (array: {}) => array not changed
     */
    @Test
    public void testReplaceSubStringsInStringArrayItemsNullEmpty() {
        String[] arrayEmpty = {};
        String[] arrayNull = null;

        DataUtils.replaceSubStringsInStringArrayItems(arrayNull, "Hi", "Hell");
        assertNull(null, arrayNull);

        DataUtils.replaceSubStringsInStringArrayItems(arrayEmpty, "Hi", "Hell");
        assertEquals(0, arrayEmpty.length);
    }


    /**
     * Purpose : Check function of replaceSubStringsInStringArrayItems
     * Check Target : replaceSubStringsInStringArrayItems
     * Expected :
     *      (array: {
     *          "Hi! I am Minsu!",
     *          "I am Mina! Hi!",
     *          "And I am Jito! Hello"
     *      } => {
     *          "Hell! I am Minsu!",
     *          "I am Mina! Hell!",
     *          "And I am Jito! Hello"
     *      }
     */
    @Test
    public void testReplaceSubStringInStringArrayItems() {
        String[] test = {
            "Hi! I am Minsu!",
            "I am Mina! Hi!",
            "And I am Jito! Hello"
        };

        String[] expected = {
            "Hell! I am Minsu!",
            "I am Mina! Hell!",
            "And I am Jito! Hello"
        };
        DataUtils.replaceSubStringsInStringArrayItems(test, "Hi", "Hell");

        for(int i = 0; i < 3; ++i) {
            assertEquals(expected[i], test[i]);
        }
    }

    /**
     * Purpose : Check returning original float value when string value is null
     * Check Target : getFloatFromString
     * Expected :
     *      (value: null, test: 0.5F) => 0.5F
     */
    @Test
    public void testGetFloatFromStringNull() {
        String value = null;
        float def = 0.5F;

        assertEquals(def, DataUtils.getFloatFromString(value, def), 0.00001);
    }

    /**
     * Purpose : Check returning casting float value from string value
     * Check Target : getFloatFromString
     * Expected :
     *      (value: "0.5F", test: 0.0F) => 0.5F
     */
    @Test
    public void testGetFloatFromString() {
        String value = "0.5";
        float test = 0.0F;
        float expected = 0.5F;

        assertEquals(expected, DataUtils.getFloatFromString(value, test), 0.00001);
    }

    /**
     * Purpose : Check returning original int value when string value is null
     * Check Target : getIntFromString
     * Expected :
     *      (value: null, test: 0) => 0
     */
    @Test
    public void testGetIntFromStringNull() {
        String value = null;
        int def = 0;

        assertEquals(def, DataUtils.getIntFromString(value, def));
    }

    /**
     * Purpose : Check returning casting int value from string value
     * Check Target : getIntFromString
     * Expected :
     *      (value: "12345678", test: 0) => 12345678
     */
    @Test
    public void testGetIntFromString() {
        String value = "12345678";
        int def = 0;
        int expected = 12345678;

        assertEquals(expected, DataUtils.getIntFromString(value, def));
    }

    /**
     * Purpose : Check returning original string value from Integer value when string is null
     * Check Target : getStringFromInteger
     * Expected :
     *      (value: null, test: "0") => "0"
     */
    @Test
    public void testGetStringFromIntegerNull() {
        Integer value = null;
        String def = "0";

        assertEquals(def, DataUtils.getStringFromInteger(value, def));
    }

    /**
     * Purpose : Check returning casting string value from Integer value
     * Check Target : getStringFromInteger
     * Expected :
     *      (value: 12345678, test: "0") => "12345678"
     */
    @Test
    public void testGetStringFromInteger() {
        Integer value = 12345678;
        String def = "0";
        String expected = "12345678";

        assertEquals(expected, DataUtils.getStringFromInteger(value, def));
    }

    /**
     * Purpose : Check casting Hex String from byte array
     * Check Target : bytesToHex
     * Expected :
     *      (bytes: "ABCD".getBytes()) => "41424344"
     */
    @Test
    public void testBytesToHex() {
        byte[] bytes = "ABCD".getBytes();

        String expected = "41424344";
        assertEquals(expected, DataUtils.bytesToHex(bytes));
    }

    /**
     * Purpose : Check clamp function
     * Check Target : clamp
     * Expected :
     *      (value: 5, min: 10, max: 100) => 10
     *      (value: 50, min: 10, max: 100) => 50
     *      (value: 100, min: 10, max: 100) => 1123
     */
    @Test
    public void testClamp() {
        int min = 10;
        int max = 100;

        assertEquals(10, DataUtils.clamp(5, min, max));
        assertEquals(50, DataUtils.clamp(50, min, max));
        assertEquals(100, DataUtils.clamp(1123, min, max));
    }

    /**
     * Purpose : Check rangedOrDefault function
     * Check Target : rangedOrDefault
     * Expected :
     *      (value: 0.3F, def: 0.0F, min: 10, max: 100) => 0.0F
     *      (value: 15.52F, def: 0.0F, min: 10, max: 100) => 15.52F
     *      (value: 1024.24F, def: 0.0F, min: 10, max: 100) => 0.0F
     */
    @Test
    public void testRangedOrDefault() {
        float min = 1.1F;
        float max = 100.53F;
        float def = 0.0F;

        assertEquals(def, DataUtils.rangedOrDefault(0.3F, def, min, max), 0.00001);
        assertEquals(15.52F, DataUtils.rangedOrDefault(15.52F, def, min, max), 0.00001);
        assertEquals(def, DataUtils.rangedOrDefault(1024.24F, def, min, max), 0.00001);
    }

    /**
     * Purpose : Check null at Indent Adding function if input string is null
     * Check Target : getSpaceIndentedString, getTabIndentedString, getIndentedString
     * Expected :
     *      getSpaceIndentedString(string: null) => null
     *      getTabIndentedString(string: null) => null
     *      getIndentedString(string: null) => null
     */
    @Test
    public void testIndentedStringNull() {
        assertNull(DataUtils.getSpaceIndentedString(null, 5));
        assertNull(DataUtils.getTabIndentedString(null, 5));
        assertNull(DataUtils.getIndentedString(null, " ", 5));
    }

    /**
     * Purpose : Check adding space indent
     * Check Target : getSpaceIndentedString
     * Expected :
     *      (value: "Hello", count: 3) => "            Hello"
     */
    @Test
    public void testGetSpaceIndentedString() {
        String value = "Hello";
        String expected = "            Hello";

        assertEquals(expected, DataUtils.getSpaceIndentedString(value, 3));
    }

    /**
     * Purpose : Check adding tab indent
     * Check Target : getSpaceIndentedString
     * Expected :
     *      (value: "Hello", count: 3) => "\t\t\tHello"
     */
    @Test
    public void testGetTabIndentedString() {
        String value = "Hello";
        String expected = "\t\t\tHello";

        assertEquals(expected, DataUtils.getTabIndentedString(value, 3));
    }
}
