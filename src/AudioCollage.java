/******************************************************************************
 *  Compilation:  javac AudioCollage.java
 *  Execution:    java AudioCollage
 *
 *  Implements five audio processing functions that take and return
 *  arrays of doubles (samples), and a main() that creates a short collage.
 *
 *  Requires: StdAudio.java (from Princeton's library) at compile/run time.
 ******************************************************************************/

public class AudioCollage {

    // Clips a sample to the range [-1.0, +1.0].
    private static double clip(double x) {
        if (x > 1.0) return 1.0;
        if (x < -1.0) return -1.0;
        return x;
    }

    // Returns a new array that rescales a[] by the factor alpha.
    public static double[] amplify(double[] a, double alpha) {
        double[] out = new double[a.length];
        for (int i = 0; i < a.length; i++) out[i] = clip(alpha * a[i]);
        return out;
    }

    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
        double[] out = new double[a.length];
        for (int i = 0; i < a.length; i++) out[i] = a[a.length - 1 - i];
        return out;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
        double[] out = new double[a.length + b.length];
        for (int i = 0; i < a.length; i++) out[i] = a[i];
        for (int j = 0; j < b.length; j++) out[a.length + j] = b[j];
        return out;
    }

    // Returns a new array that is the sum of a[] and b[] (padding the shorter with zeros).
    public static double[] mix(double[] a, double[] b) {
        int n = Math.max(a.length, b.length);
        double[] out = new double[n];
        for (int i = 0; i < n; i++) {
            double av = (i < a.length) ? a[i] : 0.0;
            double bv = (i < b.length) ? b[i] : 0.0;
            out[i] = clip(av + bv);
        }
        return out;
    }

    // Returns a new array that changes the speed by the given factor.
    // If alpha > 1, speeds up (fewer samples). If alpha < 1, slows down (more samples).
    public static double[] changeSpeed(double[] a, double alpha) {
        int n = (int) Math.floor(a.length / alpha);
        if (n <= 0) n = 0;
        double[] out = new double[n];
        for (int i = 0; i < n; i++) {
            int idx = (int) Math.floor(i * alpha);
            if (idx >= a.length) idx = a.length - 1;
            out[i] = a[idx];
        }
        return out;
    }

    // Creates a collage and plays it. (No command-line arguments.)
    public static void main(String[] args) {
        // Read a few provided WAV files (expected to be in the working directory).
        double[] beatbox = StdAudio.read("beatbox.wav");
        double[] chimes  = StdAudio.read("chimes.wav");
        double[] cow     = StdAudio.read("cow.wav");
        double[] dialup  = StdAudio.read("dialup.wav");
        double[] piano   = StdAudio.read("piano.wav");

        // Build a simple collage:
        double[] part1 = amplify(beatbox, 0.7);
        double[] part2 = reverse(chimes);
        double[] part3 = changeSpeed(cow, 1.5);
        double[] part4 = mix(piano, dialup);
        double[] seq   = merge(merge(part1, part2), merge(part3, part4));

        // Play it.
        StdAudio.play(seq);
    }
}
