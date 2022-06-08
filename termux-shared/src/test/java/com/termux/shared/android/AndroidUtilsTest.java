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


}
