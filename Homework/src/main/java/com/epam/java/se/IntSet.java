package com.epam.java.se;

/**
 * Created by Diamond on 2/2/2017.
 */
public class IntSet {

    private int currSize = 1;
    private long[] data = new long[currSize];
    private long[] newArray;
    private int modulus;
    private int indexToPut;

    public IntSet() {
        this.data[0] = 0b0;
    }

    private IntSet(long[] data) {
        this.data = data;
    }

    public void add(int value) {

        if (value < 0)
            return;
        else if (value >= 0 && value < (64 * currSize)) {
            modulus = value;
            indexToPut = 0;

            while (modulus >= 64) {
                modulus %= 64;
                indexToPut++;
            }

            data[indexToPut] |= 1L << modulus;
        } else {
            int newSize = value / 64 + 1;
            newArray = new long[newSize];
            System.arraycopy(data, 0, newArray, 0, data.length);
            //newArray[newSize-1] |= 1L << (value % (64 * (newSize - 1)));
            modulus = value;
            while (modulus >= 64) {
                modulus %= 64;
            }

            newArray[newSize - 1] |= 1L << modulus;

            data = newArray;
            currSize = newSize;
            newArray = null;
        }
    }


    public boolean contains(int value) {

        if (value < 0)
            return false;

        else if ((value / 64 + 1) > data.length)
            return false;

        else {

            modulus = value;
            indexToPut = 0;

            while (modulus >= 64) {
                modulus %= 64;
                indexToPut++;
            }

            final long mask = 1L << modulus;
            final long res = data[indexToPut] & mask;
            return res != 0;
        }

    }

    public void remove(int value) {

        if (value < 0)
            return;

        else {
            modulus = value;
            indexToPut = 0;

            while (modulus >= 64) {
                modulus %= 64;
                indexToPut++;
            }

            data[indexToPut] &= ~(1L << modulus);
        }
    }

    public IntSet union(IntSet other) {

        int minLength;
        if (this.data.length < other.data.length)
            minLength = this.data.length;
        else
            minLength = other.data.length;

        final long[] result = new long[minLength];

        for (int i = 0; i < minLength; i++) {
            result[i] = this.data[i] | other.data[i];
        }

        return new IntSet(result);


    }

    public IntSet intersect(IntSet other) {
        int minLength;
        if (this.data.length < other.data.length)
            minLength = this.data.length;
        else
            minLength = other.data.length;

        final long[] result = new long[minLength];

        for (int i = 0; i < minLength; i++) {
            result[i] = this.data[i] & other.data[i];
        }

        return new IntSet(result);
    }

    public IntSet difference(IntSet other) {
        int minLength;
        if (this.data.length < other.data.length)
            minLength = this.data.length;
        else
            minLength = other.data.length;

        final long[] result = new long[minLength];

        for (int i = 0; i < minLength; i++) {
            result[i] = this.data[i] ^ other.data[i];
        }

        return new IntSet(result);
    }

    public boolean isSubsetOf(IntSet other) {
        int minLength;
        if (this.data.length < other.data.length)
            minLength = this.data.length;
        else
            minLength = other.data.length;

        boolean result = true;

        for (int i = 0; i < minLength; i++) {
            if (this.data[i] != other.data[i]) {
                return false;
            }
        }
        return result;
    }
}
