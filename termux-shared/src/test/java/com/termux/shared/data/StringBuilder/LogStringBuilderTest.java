package com.termux.shared.data.StringBuilder;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LogStringBuilderTest {
    private LogStringBuilder builder;
    private String inpStr;

    @Before
    public void setUp() {
        builder = new LogStringBuilder();
        inpStr = "It is test";
    }

    /**
     * Purpose : Check LogStringBuilder appending well
     * Check Target : append
     * Expected :
     *      ("It is test") => "It is test"
     */
    @Test
    public void appendTest() {
        builder.append(inpStr);
        assertEquals(inpStr, builder.getString());
    }

    /**
     * Purpose : Check LogStringBuilder appending title well
     * Check Target : appendTitle
     * Expected :
     *      ("Title", "It is test") => "TitleIt is test"
     */
    @Test
    public void appendTitle() {
        String title = "Title";
        String expected = "TitleIt is test";

        builder.appendTitle(title);
        builder.append(inpStr);

        assertEquals(expected, builder.getString());
    }

    /**
     * Purpose : Check LogStringBuilder appending Single Line Log well
     * Check Target : appendSLEntry
     * Expected :
     *      ("It is test", "label", 555) => "It is test\nlabel: `555`"
     */
    @Test
    public void appendSL() {
        String label = "label";
        Integer testI = 555;
        String expected = "It is test\nlabel: `555`";

        builder.append(inpStr);
        builder.appendSLEntry(label, testI);

        assertEquals(expected, builder.getString());
    }

    /**
     * Purpose : Check LogStringBuilder appending Multi Line Log well
     * Check Target : appendSLEntry
     * Expected :
     *      ("It is test", "label", 555) => "It is test\nlabel:\n```\n555\n```\n"
     */
    @Test
    public void appendML() {
        String label = "label";
        Integer testI = 555;
        String expected = "It is test\nlabel:\n```\n555\n```\n";

        builder.append(inpStr);
        builder.appendMLEntry(label, testI);

        assertEquals(expected, builder.getString());
    }
}
