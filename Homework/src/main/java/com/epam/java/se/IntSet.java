package com.epam.java.se;

/**
 * Created by Diamond on 2/2/2017.
 */
public class IntSet {

    private int currSize = 1;
    private long [] data = new long [currSize];
    private long [] newArray;

    public IntSet () {
        this(0b0);
    }

    private IntSet (long data) {
        this.data[currSize-1] = data;
    }

    public void add (int value) {

        if (value>=0 && value<(64*currSize)) {
            data[value/64] |= 1L <<value; }

            else if (value<0)
                return;

            else {
                int newSize = value/64 + 1;
                newArray = new long[newSize];
                System.arraycopy(data, 0, newArray, 0, data.length);
                newArray[newSize-1] |= 1L << (value % (64 * (newSize - 1)));
                data = newArray;
                newArray = null;
            }

        }


    public boolean contains (int value) {

        if (value < 0)
            return false;

        else if ((value/64 +1) > data.length)
            return false;

        else {

            int tempValue = value;
            int index = 0;

            while (tempValue >= 64) {
                tempValue %= 64; index++;
            }

            final long mask = 1L <<tempValue;
            final long res = data[index] & mask;
            return res != 0;
        }

    }
}
