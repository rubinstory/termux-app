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


}
