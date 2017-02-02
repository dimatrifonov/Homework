package com.epam.java.se;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Diamond on 2/2/2017.
 */
public class IntSetTest {
    @Test
    public void add () throws Exception {
        final IntSet set = new IntSet();

        assertFalse (set.contains(0));
        set.add(0);
        assertTrue (set.contains(0));

        assertFalse (set.contains(5));

        set.add(100);
        assertTrue (set.contains(100));
        assertFalse (set.contains(101));



    }

    @Test
    public void remove () throws Exception {
        final IntSet set = new IntSet();

        assertFalse (set.contains(1));
        set.add(1);
        assertTrue(set.contains(1));

        set.remove(1);
        assertFalse (set.contains(1));
    }

    @Test
    public void contains() throws Exception {
        final IntSet set = new IntSet();

        for (int i=0; i<1000; i++) {
            assertFalse(set.contains(i));
        }

        set.add(1);
        set.add(20);
        set.add(80);

        for (int i=0; i<1000; i++) {
            if (i==1 || i==20 || i==80) {
                assertTrue(set.contains(i));
            }
                else {
                    assertFalse(set.contains(i));
                }

            }
        }

    }
