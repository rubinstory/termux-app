package com.termux.shared.file.filesystem;

import junit.framework.TestCase;

public class FileTypeTest extends TestCase {
    /**
     * Purpose: Check get name
     * Input: base: FileType.NO_EXIST.getName(), FileType.REGULAR.getName()
     * Expected:
     *      FileType.NO_EXIST.getName() -> "no exist"
     *      FileType.REGULAR.getName() -> "regular"
     */
    public void testgetName(){
        assertEquals(FileType.NO_EXIST.getName(), "no exist");
        assertEquals(FileType.REGULAR.getName(), "regular");

    }
    /**
     * Purpose: Check get value
     * Input: base: FileType.NO_EXIST.getValue(), FileType.REGULAR.getValue()
     * Expected:
     *      FileType.NO_EXIST.getValue() -> 0
     *      FileType.REGULAR.getValue() -> 1
     */
    public void testgetValue(){
        assertEquals(FileType.NO_EXIST.getValue(), 0);
        assertEquals(FileType.REGULAR.getValue(), 1);

    }

}
