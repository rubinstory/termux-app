package com.termux.shared.android;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class AndroidUtilsInstrumentationTest {
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();


    /**
     * Purpose:
     * Input:
     * Expected:
     *
     */

    @Test
    public void testGetAppInfoMarkdownStringNull() {
        //Create an active task

        // Call your function
        String appInfoMarkdown = AndroidUtils.getAppInfoMarkdownString(context);
        // Check the result
        System.out.println(appInfoMarkdown);
        System.out.println("hello");
    }

}
