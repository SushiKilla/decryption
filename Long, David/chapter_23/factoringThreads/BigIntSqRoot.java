package factoringThreads;

import java.math.BigInteger;

/**
 * Code to find the square root of a BigInteger
 *
 */

public class BigIntSqRoot {

public static BigInteger bigIntSqRootFloor(BigInteger x)
        throws IllegalArgumentException {
    if (x.compareTo(BigInteger.ZERO) < 0) {
        throw new IllegalArgumentException("Negative argument.");
    }
    // square roots of 0 and 1 are trivial and
    // y == 0 will cause a divide-by-zero exception
    if (x == BigInteger.ZERO || x == BigInteger.ONE) {
        return x;
    } // end if
    BigInteger two = BigInteger.valueOf(2L);
    BigInteger y;
    // starting with y = x / 2 avoids magnitude issues with x squared
    for (y = x.divide(two);
            y.compareTo(x.divide(y)) > 0;
            y = ((x.divide(y)).add(y)).divide(two));
    return y;
} // end bigIntSqRootFloor

public static BigInteger bigIntSqRootCeil(BigInteger x)
        throws IllegalArgumentException {
    if (x.compareTo(BigInteger.ZERO) < 0) {
        throw new IllegalArgumentException("Negative argument.");
    }
    // square roots of 0 and 1 are trivial and
    // y == 0 will cause a divide-by-zero exception
    if (x == BigInteger.ZERO || x == BigInteger.ONE) {
        return x;
    } // end if
    BigInteger two = BigInteger.valueOf(2L);
    BigInteger y;
    // starting with y = x / 2 avoids magnitude issues with x squared
    for (y = x.divide(two);
            y.compareTo(x.divide(y)) > 0;
            y = ((x.divide(y)).add(y)).divide(two));
    if (x.compareTo(y.multiply(y)) == 0) {
        return y;
    } else {
        return y.add(BigInteger.ONE);
    }
} // end bigIntSqRootCeil
} // end class bigIntSqRoot