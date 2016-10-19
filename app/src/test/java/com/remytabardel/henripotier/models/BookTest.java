package com.remytabardel.henripotier.models;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author Remy Tabardel
 */

public class BookTest {
    Book mBook1, mBook2, mBook3;

    @Before
    public void init() {
        mBook1 = new Book("isbn", "title1", 11, "cover1", 1);
        mBook2 = new Book("isbn", "title2", 12, "cover2", 2);
        mBook3 = new Book("another_isbn", "title3", 13, "cover3", 3);
    }

    @Test
    public void should_return_true_when_objects_are_equals() {
        assertEquals(true, mBook1.equals(mBook2));
    }

    @Test
    public void should_return_false_when_objects_are_differents() {
        assertEquals(false, mBook1.equals(mBook3));
    }
}
