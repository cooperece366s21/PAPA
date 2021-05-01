package edu.cooper.ece366.model;

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

    public static class PhoneNumberBuilder{
        private int area;   // area code (3 digits)
        private int exch;   // exchange  (3 digits)
        private int ext;    // extension (4 digits)

        public PhoneNumberBuilder area(int area){
            this.area = area;
            return this;
        }
        public PhoneNumberBuilder exch(int exch){
            this.exch = exch;
            return this;
        }
        public PhoneNumberBuilder ext(int ext){
            this.ext = ext;
            return this;
        }
        public PhoneNumber build(){
            return new PhoneNumber(area, exch, ext);
        }
    }


}