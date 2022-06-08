package com.termux.shared.logger;

import android.content.Context;
import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LoggerTest extends TestCase {

    /**
     * Purpose: Check when throwable is null
     * Input: Logger.getMessageAndStackTraceString (null, null), ("message" null)
     * Expected:
     *      (null, null) = null
     *      ("message" null) = "message"
     */
    @Test
    public void testGetMessageAndStackTraceStringForNullThrowable() {
        assertNull(Logger.getMessageAndStackTraceString(null, null));

        String message = "message";
        String result = Logger.getMessageAndStackTraceString(message, null);
        assertEquals(result, message);
    }

    /**
     * Purpose: Check when throwable is given
     * Input: Logger.getMessageAndStackTraceString (null, Throwable), ("message", Throwable)
     * Expected:
     *      (null, Throwable) = Logger.getStackTraceString(Throwable)
     *      ("message", Throwable) = "message:\n" + Logger.getStackTraceString(throwable)
     */
    @Test
    public void testGetMessageAndStackTraceStringForThrowable() {
        String message = "message";
        Throwable throwable = new Throwable();

        String result = Logger.getMessageAndStackTraceString(null, throwable);
        assertEquals(result, Logger.getStackTraceString(throwable));

        result = Logger.getMessageAndStackTraceString(message, throwable);
        String expected = message + ":\n" + Logger.getStackTraceString(throwable);
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when string is null
     * Input: Logger.getMessageAndStackTraceString (null, null), (null, Throwable)
     * Expected:
     *      (null, null) = null
     *      (null, Throwable) = Logger.getStackTraceString(Throwable)
     */
    @Test
    public void testGetMessageAndStackTraceStringForNullString() {
        Throwable throwable = new Throwable();

        assertNull(Logger.getMessageAndStackTraceString(null, null));

        String result = Logger.getMessageAndStackTraceString(null, throwable);
        assertEquals(result, Logger.getStackTraceString(throwable));
    }

    /**
     * Purpose: Check when string is given
     * Input: Logger.getMessageAndStackTraceString ("message", null), ("message", Throwable)
     * Expected:
     *      ("message" null) = "message"
     *      ("message", Throwable) = "message:\n" + Logger.getStackTraceString(throwable)
     */
    @Test
    public void testGetMessageAndStackTraceStringForString() {
        String message = "message";
        Throwable throwable = new Throwable();

        String result = Logger.getMessageAndStackTraceString(message, null);
        assertEquals(result, message);

        result = Logger.getMessageAndStackTraceString(message, throwable);
        String expected = message + ":\n" + Logger.getStackTraceString(throwable);
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when string is given
     * Input: Logger.getMessageAndStackTracesString ("message", null), ("message", List<Throwable>)
     * Expected:
     *      ("message" null) = "message"
     *      ("message", List<Throwable>) = "message" + ":\n" + Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList))
     */
    @Test
    public void testGetMessageAndStackTracesStringForString() {
        List<Throwable> throwableList = new ArrayList<Throwable>(Arrays.asList(new Throwable()));

        String result = Logger.getMessageAndStackTracesString("message", null);
        assertEquals(result, "message");

        result = Logger.getMessageAndStackTracesString("message", throwableList);
        String expected = "message" + ":\n" + Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList));
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when null string is given
     * Input: Logger.getMessageAndStackTracesString (null, null), (null, List<Throwable>)
     * Expected:
     *      (null, null) = null
     *      (null, List<Throwable>) = Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList))
     */
    @Test
    public void testGetMessageAndStackTracesStringForNullString() {
        List<Throwable> throwableList = new ArrayList<Throwable>(Arrays.asList(new Throwable()));

        String result = Logger.getMessageAndStackTracesString(null, null);
        assertNull(result);

        result = Logger.getMessageAndStackTracesString(null, throwableList);
        String expected = Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList));
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when null or empty throwableList is given
     * Input: Logger.getMessageAndStackTracesString (null, null), ("message", null), ("message", List<Throwable>)
     * Expected:
     *      (null, null) = null
     *      ("message", null) = "message"
     *      ("message", List<Throwable>) = "message"
     */
    @Test
    public void testGetMessageAndStackTracesStringForEmptyThrowables() {
        List<Throwable> emptyThrowableList = new ArrayList<Throwable>();

        String result = Logger.getMessageAndStackTracesString(null, null);
        assertNull(result);

        result = Logger.getMessageAndStackTracesString("message", null);
        assertEquals(result, "message");

        result = Logger.getMessageAndStackTracesString("message", emptyThrowableList);
        assertEquals(result, "message");
    }

    /**
     * Purpose: Check when null or throwableList is given
     * Input: Logger.getMessageAndStackTracesString (null, List<Throwable>), ("message", List<Throwable>)
     * Expected:
     *      (null, List<Throwable>) = Logger.getStackTracesString(null, Logger.getStackTracesStringArray(List<Throwable>))
     *      ("message", List<Throwable>) = "message" + ":\n" + Logger.getStackTracesString(null, Logger.getStackTracesStringArray(List<Throwable>))
     */
    @Test
    public void testGetMessageAndStackTracesStringForThrowables() {
        List<Throwable> throwableList = new ArrayList<Throwable>(Arrays.asList(new Throwable(), new Throwable()));

        String result = Logger.getMessageAndStackTracesString(null, throwableList);
        String expected = Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList));
        assertEquals(result, expected);

        result = Logger.getMessageAndStackTracesString("message", throwableList);
        expected = "message" + ":\n" + Logger.getStackTracesString(null, Logger.getStackTracesStringArray(throwableList));
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when object is null
     * Input: Logger.getSingleLineLogStringEntry (null, null, null), ("label", null, "-")
     * Expected:
     *      (null, null, null) = "null: null"
     *      ("label", null, "-") = "label: -"
     */
    @Test
    public void testGetSingleLineLogStringEntryForNullObject() {
        String result = Logger.getSingleLineLogStringEntry(null, null, null);
        assertEquals(result, "null: null");

        result = Logger.getSingleLineLogStringEntry("label", null, "-");
        assertEquals(result, "label: -");
    }

    /**
     * Purpose: Check when object is null
     * Input: Logger.getSingleLineLogStringEntry (null, "message", null), ("label", "message", "-")
     * Expected:
     *      (null, null, null) = "null: `message`null"
     *      ("label", null, "-") = "label: `message`-"
     */
    @Test
    public void testGetSingleLineLogStringEntryForObject() {
        String result = Logger.getSingleLineLogStringEntry(null, "message", null);
        assertEquals(result, "null: `message`");

        result = Logger.getSingleLineLogStringEntry("label", "message", "-");
        assertEquals(result, "label: `message`");
    }

    /**
     * Purpose: Check when null throwable is given
     * Input: Logger.getStackTracesStringArray (null)
     * Expected:
     *      (null) = [null]
     */
    @Test
    public void testGetStackTracesStringArrayForNullThrowable() {
        Throwable nullObject = null;
        String[] result = Logger.getStackTracesStringArray(nullObject);
        String[] expected = Logger.getStackTracesStringArray(Collections.singletonList(nullObject));

        Assert.assertArrayEquals(result, expected);
        assertEquals(result.length, 1);
        assertNull(result[0]);
    }

    /**
     * Purpose: Check when throwable is given
     * Input: Logger.getStackTracesStringArray (Throwable)
     * Expected:
     *      (Throwable) = [Logger.getStackTracesStringArray(Collections.singletonList(throwable))]
     */
    @Test
    public void testGetStackTracesStringArrayForThrowable() {
        Throwable throwable = new Throwable();
        String[] result = Logger.getStackTracesStringArray(throwable);
        String[] expected = Logger.getStackTracesStringArray(Collections.singletonList(throwable));

        Assert.assertArrayEquals(result, expected);
    }

    /**
     * Purpose: Check when null stackTraceStringArray is given
     * Input: Logger.getStackTracesString ("message", null), (null, null)
     * Expected:
     *      ("message", null) = "message -"
     *      (null, null) = "StackTraces: -"
     */
    @Test
    public void testGetStackTracesStringForNullStackTraceStringArray() {
        String result = Logger.getStackTracesString("message", null);
        assertEquals(result, "message -");

        result = Logger.getStackTracesString(null, null);
        assertEquals(result, "StackTraces: -");
    }

    /**
     * Purpose: Check when null label is given
     * Input: Logger.getStackTracesMarkdownString (null, null), (null, ["test"])
     * Expected:
     *      (null, null) = "### StackTraces\n" + "\n" + "`-`\n" + "##\n"
     *      (null, ["test"]) = "### StackTraces\n" + "\n" + "```\n" + "test\n" + "```\n" + "##\n"
     */
    @Test
    public void testGetStackTracesMarkdownStringForNullLabel() {
        String result = Logger.getStackTracesMarkdownString(null, null);
        String expected = "### StackTraces\n" + "\n" + "`-`\n" + "##\n";
        assertEquals(result, expected);

        String[] stackTracesStringArray = {"test"};
        result = Logger.getStackTracesMarkdownString(null, stackTracesStringArray);
        expected = "### StackTraces\n" + "\n" + "```\n" + "test\n" + "```\n" + "##\n";
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when stackTraceStringArray is given
     * Input: Logger.getStackTracesMarkdownString (null, {"test"}), (null, {"test", "test"})
     * Expected:
     *      (null, {"test"}) = "### StackTraces\n" + "\n" + "```\n" + "test\n" + "```\n" + "##\n"
     *      (null, {"test", "test"}) = "### StackTraces\n" + "\n" + "\n" + "#### Stacktrace 1\n" + "\n" + "```\n" + "test\n" + "```\n" + "\n" + "\n" + "#### Stacktrace 2\n" + "\n" + "```\n" + "test\n" + "```\n" + "##\n"
     */
    @Test
    public void testGetStackTracesMarkdownStringForStackTraceStringArray() {
        String[] stackTraceStringArray = {"test"};
        String result = Logger.getStackTracesMarkdownString(null, stackTraceStringArray);
        String expected = "### StackTraces\n" + "\n" + "```\n" + "test\n" + "```\n" + "##\n";
        assertEquals(result, expected);

        stackTraceStringArray = new String[] {"test", "test"};
        result = Logger.getStackTracesMarkdownString(null, stackTraceStringArray);
        expected = "### StackTraces\n" + "\n" + "\n" + "#### Stacktrace 1\n" + "\n" + "```\n" + "test\n" + "```\n" + "\n" + "\n" + "#### Stacktrace 2\n" + "\n" + "```\n" + "test\n" + "```\n" + "##\n";
        assertEquals(result, expected);
    }

    /**
     * Purpose: Check when object is given
     * Input: Logger.getMultiLineLogStringEntry ("label", null, "-"), ("label", "object", "-")
     * Expected:
     *      ("label", null, "-") = "label: -"
     *      ("label", "object", "-") = "label:\n```\nobject\n```\n"
     */
    @Test
    public void testGetMultiLineLogStringEntryForObject() {
        String result = Logger.getMultiLineLogStringEntry("label", null, "-");
        assertEquals(result, "label: -");

        result = Logger.getMultiLineLogStringEntry("label", "object", "-");
        assertEquals(result, "label:\n```\nobject\n```\n");
    }

    /**
     * Purpose: Check logLevelsArray is successfully returned
     * Input: Logger.getLogLevelsArray ()
     * Expected:
     *      () = { String.valueOf(Logger.LOG_LEVEL_OFF), String.valueOf(Logger.LOG_LEVEL_NORMAL), String.valueOf(Logger.LOG_LEVEL_DEBUG), String.valueOf(Logger.LOG_LEVEL_VERBOSE)}
     */
    @Test
    public void testGetLogLevelsArray() {
        CharSequence[] expected = { String.valueOf(Logger.LOG_LEVEL_OFF),
            String.valueOf(Logger.LOG_LEVEL_NORMAL),
            String.valueOf(Logger.LOG_LEVEL_DEBUG),
            String.valueOf(Logger.LOG_LEVEL_VERBOSE)};
        Assert.assertArrayEquals(Logger.getLogLevelsArray(), expected);
    }

    /**
     * Purpose: Check when null context is given
     * Input: Logger.getLogLevelLabelsArray (null, null, true), (null, CharSequence[], true)
     * Expected:
     *      (null, null, true) = null
     *      (null, CharSequence[], true) = NullPointerException
     */
    @Test
    public void testGetLogLevelLabelsArray() {
        CharSequence[] result = Logger.getLogLevelLabelsArray(null, null,true);
        assertNull(result);

        CharSequence[] logLevels = {"1", "2"};
        try {
            result = Logger.getLogLevelLabelsArray(null, logLevels,true);
        } catch(NullPointerException e) {
            assertTrue(e instanceof NullPointerException);
        }
    }
}
