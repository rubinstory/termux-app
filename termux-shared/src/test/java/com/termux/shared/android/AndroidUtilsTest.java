package com.termux.shared.android;

import org.junit.Test;
import static org.junit.Assert.*;

public class AndroidUtilsTest {

    /**
    * Purpose: Check that AndroidUtils has the correct markdown
    * Input: "Label","object"
    * Expected: "**Label**: `" + "object" + "`  "
    *
    */

    @Test
    public void testGetPropertyMarkdown() {
        // Create an active task
        // Call your function
        String result = AndroidUtils.getPropertyMarkdown("Label","object");
        String expected = "**Label**: `" + "object" + "`  ";
        // Check the result
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check that AndroidUtils append Markdown properly
     * Input: stringBuilder,"Label","object"
     * Expected: "\n" + "**Label**: `" + "object" + "`  "
     *
     */

    @Test
    public void testAppendPropertyToMarkdown() {
        // Create an active task
        StringBuilder stringBuilder = new StringBuilder();
        // Call your function
        AndroidUtils.appendPropertyToMarkdown(stringBuilder,"Label","object");
        String expected = "\n" + "**Label**: `" + "object" + "`  ";
        // Check the result
        assertEquals(stringBuilder.toString(), expected);
    }

    /**
     * Purpose: Check that AndroidUtils append Markdown properly If Object is not "REL"
     * Input: stringBuilder,"Label","notREL"
     * Expected: "\n" + "**Label**: `" + "notREL" + "`  "
     *
     */

    @Test
    public void testAppendPropertyToMarkdownIfSet() {
        // Create an active task
        StringBuilder stringBuilder = new StringBuilder();
        // Call your function
        AndroidUtils.appendPropertyToMarkdownIfSet(stringBuilder,"Label","notREL");
        String expected = "\n" + "**Label**: `" + "notREL" + "`  ";
        System.out.println(expected);
        // Check the result
        assertEquals(expected, stringBuilder.toString());
    }

    /**
     * Purpose:test get SystemProperty properly
     * Input: "os.version"
     * Expected: 10.0
     *           it depends on System OS version
     *
     */

    @Test
    public void testGetSystemPropertyWithAndroidAPISuccess() {
        // Create an active task
        // Call your function
        String result = AndroidUtils.getSystemPropertyWithAndroidAPI("os.version");
        String expected = System.getProperty("os.version");
        // Check the result
        assertEquals(expected,result);
    }

    /**
     * Purpose: test get SystemProperty fail because of null in input
     * Input: null
     * Expected: null
     *
     */

    @Test
    public void testGetSystemPropertyWithAndroidAPINullFail() {
        // Create an active task
        // Call your function
        String result = AndroidUtils.getSystemPropertyWithAndroidAPI(null);
        String expected = null;
        // Check the result
        assertEquals(expected,result);
    }

    /**
     * Purpose:test get SystemProperty fail because of wrong key in input
     * Input: String :"wrongKey"
     * Expected: null
     *
     */

    @Test
    public void testGetSystemPropertyWithAndroidAPIWrongKeyFail() {
        // Create an active task
        // Call your function
        String result = AndroidUtils.getSystemPropertyWithAndroidAPI("wrongKey");
        String expected = null;
        // Check the result
        assertEquals(expected, result);
    }

}
