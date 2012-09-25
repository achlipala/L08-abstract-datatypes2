package rational;

/** Ratnum is an immutable type representing rational numbers
 */
public class RatNum {
    private final int numer;
    private final int denom;

    // Rep invariant:
    //   denom > 0
    //   numer/denom is in reduced form

    // Abstraction Function:
    //   represents the rational number numer / denom

    /** Make a new Ratnum == n. */
    public RatNum(int n) {
        numer = n;
        denom = 1;
        checkRep();
    }

    /**
     * Make a new RatNum == (n / d).
     * @param n numerator
     * @param d denominator
     * @throws ArithmeticException if d == 0
     */
    public RatNum(int n, int d) throws ArithmeticException {
        // reduce ratio to lowest terms
        int g = gcd(n, d);
        n = n / g;
        d = d / g;

        // make denominator positive
        if (d < 0) {
            numer = -n;
            denom = -d;
        } else {
            numer = n;
            denom = d;
        }
        checkRep();
    }

    /////////////////////////////////////////
    // other methods should go here
    //    producers: add(), subtract(), multiply(), divide(), etc.
    //    observers: isPositive(), intValue(), etc.
    //    mutators: none

    // Check that the rep invariant is true
    // *** Warning: this does nothing unless you turn on assertion checking
    // by passing -enableassertions to Java
    private void checkRep() {
        assert denom > 0;
        assert gcd(numer, denom) == 1;
    }
    
    /**
     * @return a string representation of this rational number
     */
    // This effectively implements the abstraction function
    public String toString() {
        return (denom > 1) ? (numer + "/" + denom) : (numer + "");
    }

    // compute greatest common denominator of a and b
    private static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}
