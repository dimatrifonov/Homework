package com.epam.java.se;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Diamond on 2/2/2017.
 */
public class IntSetTest {
    @Test
    public void add () throws Exception{
        final IntSet set = new IntSet();

        assertFalse (set.contains(0));
        set.add(0);
        assertTrue (set.contains(0));
        assertFalse (set.contains(5));

    }



}
