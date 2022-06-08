package com.termux.shared.file;

import android.util.Log;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.File;

public class FileUtilsTest extends TestCase {


    /**
     * Purpose: Check string path is null or empty
     * Input: getCanonicalPath(null, null), getCanonicalPath("", null)
     * Expected:
     *      getCanonicalPath(null, null)-> "/"
     *      getCanonicalPath("", null)   -> "/"
     */

    public void testgetCanonicalEmptyPath(){
        assertEquals(FileUtils.getCanonicalPath(null,null), "/");
        assertEquals(FileUtils.getCanonicalPath("",null), "/");

    }
    /**
     * Purpose: Check string path is Absolute Path
     * Input: getCanonicalPath("/path", null), getCanonicalPath("/path", "prefixForNonAbsolutePath")
     * Expected:
     *      getCanonicalPath("/path", null)-> "/path"
     *      getCanonicalPath("/path", "prefixForNonAbsolutePath")   -> "/path"
     */

    public void testgetCanonicalAbsolutePath(){

        //
        assertEquals(FileUtils.getCanonicalPath("/path",null), "/path");
        assertEquals(FileUtils.getCanonicalPath("/path","prefixForNonAbsolutePath"), "/path");

    }
    /**
     * Purpose: Check string is non-absolute Path
     * Input: getCanonicalPath("/path", null), getCanonicalPath("/path", "prefixForNonAbsolutePath")
     * Expected:
     *      getCanonicalPath("path", null)-> "/path"
     *      getCanonicalPath("path", "prefixForNonAbsolutePath")   -> "/Users/.../path"
     */

    public void testgetCanonicalNonAbsolutePath(){

       assertEquals(FileUtils.getCanonicalPath("path","prefixForNonAbsolute"), new File("prefixForNonAbsolute/path").getAbsolutePath());
       assertEquals(FileUtils.getCanonicalPath("path",null), "/path");

    }
    /**
     * Purpose: Check string path is null or ""
     * Input: normalizePath(null), getCanonicalPath("")
     * Expected:
     *      normalizePath(null) -> null
     *      getCanonicalPath("")   -> ""
     */

    public void testnormalizePathforNull(){
        assertNull(FileUtils.normalizePath(null));
    }
    /**
     * Purpose: Test path text
     * Input: normalizePath("////path"), getCanonicalPath("\./path"), , getCanonicalPath("///////$path/")
     * Expected:
     *      normalizePath("////path") -> "/path"
     *      getCanonicalPath("\./path") -> "\path"
     *      getCanonicalPath("///////$path/") -> "/$path"
     */

    public void testnormalizePath(){
        assertEquals(FileUtils.normalizePath("////path"), "/path");
        assertEquals(FileUtils.normalizePath("\\./path"), "\\path");
        assertEquals(FileUtils.normalizePath("///////$path/"), "/$path");
    }

    /**
     * Purpose:  Check string path is null or ""
     * Input: sanitizeFileName(null, true ,true), sanitizeFileName(null, false ,false), sanitizeFileName(null, false ,true), sanitizeFileName(null, true ,false)
     *        sanitizeFileName("", true ,true), sanitizeFileName("", false ,false), sanitizeFileName("", false ,true), sanitizeFileName("", true ,false)
     * Expected:
     *      sanitizeFileName(null, true ,true) -> null
     *      sanitizeFileName(null, false ,false) -> null
     *      sanitizeFileName(null, false ,true) -> null
     *      sanitizeFileName(null, true ,false) -> null
     *      sanitizeFileName("", true ,true) -> ""
     *      sanitizeFileName("", false ,false) -> ""
     *      sanitizeFileName("", false ,true) -> ""
     *      sanitizeFileName("", true ,false) -> ""
     */
    public void testsanitizeFileNameforNull(){
        assertEquals(FileUtils.sanitizeFileName(null, true, false), null);
        assertEquals(FileUtils.sanitizeFileName(null, false, true), null);
        assertEquals(FileUtils.sanitizeFileName(null, false, false), null);
        assertEquals(FileUtils.sanitizeFileName(null, true, true), null);
        assertEquals(FileUtils.sanitizeFileName("", true, false), "");
        assertEquals(FileUtils.sanitizeFileName("", false, true), "");
        assertEquals(FileUtils.sanitizeFileName("", false, false), "");
        assertEquals(FileUtils.sanitizeFileName("", true, true), "");

    }

    /**
     * Purpose:  Convert special characters and whitespaces to "_" and uppercase to lowercase
     * Input: sanitizeFileName("\\/:*?\"<>|path", false, false), sanitizeFileName("\n\tpath", true, false), sanitizeFileName("PATH", true, true)
     * Expected:
     *      sanitizeFileName("\\/:*?\"<>|path", false, false) -> "_________path"
     *      sanitizeFileName("\n\tpath", true, false) -> "__path"
     *      sanitizeFileName("PATH", true, true) -> "path"
     */
    public void testsanitizeFileName(){
        assertEquals(FileUtils.sanitizeFileName("\\/:*?\"<>|path", false, false), "_________path");
        assertEquals(FileUtils.sanitizeFileName("\n\tpath", true, false), "__path");
        assertEquals(FileUtils.sanitizeFileName("PATH", true, true), "path");
    }


}
