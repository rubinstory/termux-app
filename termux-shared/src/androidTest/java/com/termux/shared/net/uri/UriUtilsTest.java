package com.termux.shared.net.uri;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.net.Uri;

@RunWith(AndroidJUnit4.class)
public class UriUtilsTest {
    /**
     * Purpose: Check the null, empty or null path url string
     * Input: null, "", "file://"
     * Expected: null
     */
    @Test
    public void testGetUriFilePathWithFragmentNull() {
        assertNull(UriUtils.getUriFilePathWithFragment(null));

        Uri nullUri = Uri.parse("");
        assertNull(UriUtils.getUriFilePathWithFragment(nullUri));

        Uri nullPathUri = Uri.parse("file://");
        assertNull(UriUtils.getUriFilePathWithFragment(nullPathUri));
    }

    /**
     * Purpose: Check the each Uri made by {@link Uri#parse(String)} and {@link UriUtils#getFileUri(String)}
     * Input: Uri.parse("file:///path1/path2#fragment"), UriUtils.getFileUri("/path1/path2#fragment")
     * Expected: "/path1/path2#fragment"
     */
    @Test
    public void testGetUriFilePath() {
        Uri uriWithParse = Uri.parse("file:///path1/path2#fragment");
        Uri uriWithBuilder = UriUtils.getFileUri("/path1/path2#fragment");
        assertEquals("/path1/path2#fragment", UriUtils.getUriFilePathWithFragment(uriWithParse), UriUtils.getUriFilePathWithFragment(uriWithBuilder));
    }

    /**
     * Purpose: Check the each Uri made by {@link Uri#parse(String)} and {@link UriUtils#getFileUri(String)}
     * Input: Uri.parse("file:///path1/path2#fragment"), UriUtils.getFileUri("/path1/path2#fragment")
     * Expected: "path2#fragment"
     */
    @Test
    public void testGetUriFileBasenameUriWithFragment() {
        Uri uriWithParse = Uri.parse("file:///path1/path2#fragment");
        Uri uriWithBuilder = UriUtils.getFileUri("/path1/path2#fragment");
        assertEquals("path2#fragment", UriUtils.getUriFileBasename(uriWithParse, true));
        assertEquals("path2#fragment", UriUtils.getUriFileBasename(uriWithBuilder, true));
    }

    /**
     * Purpose: Check the each Uri made by {@link Uri#parse(String)} and {@link UriUtils#getFileUri(String)}
     * Input: Uri.parse("file:///path1/path2#fragment"), UriUtils.getFileUri("/path1/path2#fragment")
     * Expected: "path2"
     */
    @Test
    public void testGetUriFileBasenameUriOnlyPath() {
        Uri uriWithParse = Uri.parse("file:///path1/path2#fragment");
        Uri uriWithBuilder = UriUtils.getFileUri("/path1/path2#fragment");
        assertEquals("path2", UriUtils.getUriFileBasename(uriWithParse, false));
        assertEquals("path2", UriUtils.getUriFileBasename(uriWithBuilder, false));
    }

}
