/******************************************************************************
 *  Compilation:  javac Divisors.java
 *  Execution:    java Divisors <a> <b>
 *
 *  Computes: gcd(a, b), lcm(a, b), areRelativelyPrime(a, b),
 *  and Euler's totient for a and b, printing each in the order required.
 *
 *  Conventions:
 *  - gcd(0, 0) = 0
 *  - lcm(a, 0) = lcm(0, b) = 0
 ******************************************************************************/

public class Divisors {

    // Returns the greatest common divisor of a and b (nonnegative).
    public static int gcd(int a, int b) {
        if (a == 0 && b == 0) return 0;
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    // Returns the least common multiple of a and b (nonnegative).
    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        int g = gcd(a, b);
        // divide before multiply to avoid overflow
        long l = (long)(a / g) * (long)b;
        if (l < 0) l = -l;
        // fits into 32-bit for inputs within assignment constraints
        return (int) l;
    }

    // Returns true if a and b are relatively prime; false otherwise.
    public static boolean areRelativelyPrime(int a, int b) {
        return gcd(a, b) == 1;
    }

    // Returns Euler's totient of n.
    public static int totient(int n) {
        if (n <= 0) return 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (gcd(i, n) == 1) count++;
        }
        return count;
    }

    // Takes two integers a and b as command-line arguments and prints results.
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println("gcd(" + a + ", " + b + ") = " + gcd(a, b));
        System.out.println("lcm(" + a + ", " + b + ") = " + lcm(a, b));
        System.out.println("areRelativelyPrime(" + a + ", " + b + ") = " + areRelativelyPrime(a, b));
        System.out.println("totient(" + a + ") = " + totient(a));
        System.out.println("totient(" + b + ") = " + totient(b));
    }
}