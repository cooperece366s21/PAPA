package edu.cooper.ece366.util;

/******************************************************************************
 *  Compilation:  javac PhoneNumber.java
 *  Execution:    java PhoneNumber
 *
 *  Immutable data type for US phone numbers.
 *
 ******************************************************************************/

// https://introcs.cs.princeton.edu/java/32class/PhoneNumber.java

public final class PhoneNumber {
    private final int area;   // area code (3 digits)
    private final int exch;   // exchange  (3 digits)
    private final int ext;    // extension (4 digits)

    public PhoneNumber(int area, int exch, int ext) {
        this.area = area;
        this.exch = exch;
        this.ext  = ext;
    }

    // how you're supposed to implement equals
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        PhoneNumber that = (PhoneNumber) y;
        return (this.area == that.area) && (this.exch == that.exch) && (this.ext == that.ext);
    }

    // 0 for padding with leading 0s
    public String toString() {
        return String.format("(%03d) %03d-%04d", area, exch, ext);
    }

    // satisfies the hashCode contract
    public int hashCode() {
        return 10007 * (area + 1009 * exch) + ext;
    }

    /*
    public static void main(String[] args) {
        PhoneNumber a = new PhoneNumber(609, 258, 4455);
        PhoneNumber b = new PhoneNumber(609, 876, 5309);
        PhoneNumber c = new PhoneNumber(609, 203, 5309);
        PhoneNumber d = new PhoneNumber(215, 876, 5309);
        PhoneNumber e = new PhoneNumber(609, 876, 5309);
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("c = " + c);
        StdOut.println("d = " + d);
        StdOut.println("e = " + e);
        StdOut.println("b == b:      " + (b == b));
        StdOut.println("b == e:      " + (b == e));
        StdOut.println("b.equals(b): " + (b.equals(b)));
        StdOut.println("b.equals(e): " + (b.equals(e)));
    }
     */
}