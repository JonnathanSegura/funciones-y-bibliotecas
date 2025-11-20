/******************************************************************************
 *  Compilation:  javac ActivationFunction.java
 *  Execution:    java ActivationFunction <x>
 *
 *  Prints several activation functions evaluated at x, one per line, in the
 *  order specified by the assignment:
 *  heaviside, sigmoid, tanh, softsign, sqnl.
 *
 *  The functions return NaN if the argument is NaN.
 ******************************************************************************/

public class ActivationFunction {

    // Returns the Heaviside step function of x.
    public static double heaviside(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        if (x < 0.0) return 0.0;
        if (x == 0.0) return 0.5;
        return 1.0;
    }

    // Returns the sigmoid function of x.
    public static double sigmoid(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        return 1.0 / (1.0 + Math.exp(-x));
    }

    // Returns the hyperbolic tangent of x.
    public static double tanh(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        // Use Math.tanh for numerical stability
        return Math.tanh(x);
    }

    // Returns the softsign function of x.
    public static double softsign(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        return x / (1.0 + Math.abs(x));
    }

    // Returns the square nonlinearity (SQNL) of x.
    public static double sqnl(double x) {
        if (Double.isNaN(x)) return Double.NaN;
        if (x <= -2.0) return -1.0;
        else if (x < 0.0)  return x + (x * x) / 4.0;
        else if (x < 2.0)  return x - (x * x) / 4.0;
        else               return 1.0;
    }

    // Takes a command-line double x and prints each activation function value.
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        System.out.println("heaviside(" + x + ") = " + heaviside(x));
        System.out.println("sigmoid("   + x + ") = " + sigmoid(x));
        System.out.println("tanh("      + x + ") = " + tanh(x));
        System.out.println("softsign("  + x + ") = " + softsign(x));
        System.out.println("sqnl("      + x + ") = " + sqnl(x));
    }
}
