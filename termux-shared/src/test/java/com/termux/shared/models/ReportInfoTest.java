package com.termux.shared.models;

import com.termux.shared.markdown.MarkdownUtils;
import androidx.core.util.Pair;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.mockito.MockedStatic;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

public class ReportInfoTest {
    /**
     * Purpose: check if reportInfo null
     * Input: parameter object is null
     * Expected:
     *      null   -> "null"
     */
    @Test
    public void testGetReportInfoMarkdownStringNull() {
        String reportInfoNull = ReportInfo.getReportInfoMarkdownString(null);
        assertEquals(reportInfoNull,"null");
    }

    /**
     * Purpose:check if `getReportInfoMarkdownString()` work well
     * Input: reportInfo = new ReportInfo("userAction","sender","reportTitle");
     *
     * Expected:
     * ## Report Info
     *
     *
     * **User Action**: `userAction`
     * **Sender**: `sender`
     * **Report Timestamp**: `yyyy-MM-dd HH:mm:ss.SSS UTC`
     * ##
     *
     *
     */
    private static MockedStatic<Pair> pair;
    @BeforeClass
    public static void beforeClass() {
        pair = mockStatic(Pair.class);
    }

    @AfterClass
    public static void afterClass() {
        pair.close();
    }

    @Test
    public void testGetReportInfoMarkdownString() {
        // Create an active task
        ReportInfo reportInfo = new ReportInfo("userAction","sender","reportTitle");

        String userAction = MarkdownUtils.getSingleLineMarkdownStringEntry("User Action", reportInfo.userAction, "-");
        String sender = MarkdownUtils.getSingleLineMarkdownStringEntry("Sender", reportInfo.sender, "-");
        String reportTitle = MarkdownUtils.getSingleLineMarkdownStringEntry("Report Timestamp", reportInfo.reportTimestamp, "-");

        reportInfo.setAddReportInfoHeaderToMarkdown(true);
        when(Pair.create("User Action", reportInfo.userAction)).thenReturn(new Pair("User Action", reportInfo.userAction));
        when(Pair.create("Sender", reportInfo.sender)).thenReturn(new Pair("Sender", reportInfo.sender));
        when(Pair.create("Report Timestamp", reportInfo.reportTimestamp)).thenReturn(new Pair("Report Timestamp", reportInfo.reportTimestamp));

        // Call your function
        String markDownReport = ReportInfo.getReportInfoMarkdownString(reportInfo);
        // Check the result
        assertEquals(markDownReport, "## Report Info\n\n"+ "\n" + userAction + "\n" + sender + "\n" + reportTitle + "\n##\n\n"+"null");
    }
}
