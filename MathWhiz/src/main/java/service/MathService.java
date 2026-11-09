package service;

/** Reusable math functions. Answers are always computed here, never stored. */
public class MathService {

    public static final double TOLERANCE = 0.01;

    /** Returns 1 if number is prime, otherwise 0. Numbers below 2 are not prime. */
    public static int isPrime(int number) {
        if (number < 2) return 0;
        if (number == 2) return 1;
        if (number % 2 == 0) return 0;
        for (int i = 3; (long) i * i <= number; i += 2) {
            if (number % i == 0) return 0;
        }
        return 1;
    }

    /** Returns a % b. Throws ArithmeticException if b is zero. */
    public static int calculateMod(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divisor must not be zero.");
        }
        return a % b;
    }

    public static double calculateSin(double degree) {
        return clean(Math.sin(Math.toRadians(degree)));
    }

    public static double calculateCos(double degree) {
        return clean(Math.cos(Math.toRadians(degree)));
    }

    /** Returns Double.NaN when tangent is undefined (90, 270, ... degrees). */
    public static double calculateTan(double degree) {
        double cos = Math.cos(Math.toRadians(degree));
        if (Math.abs(cos) < 1e-10) return Double.NaN;
        return clean(Math.tan(Math.toRadians(degree)));
    }

    /** Dispatches to sin / cos / tan by name. */
    public static double calculateTrig(String function, double degree) {
        switch (function.toLowerCase()) {
            case "sin": return calculateSin(degree);
            case "cos": return calculateCos(degree);
            case "tan": return calculateTan(degree);
            default: throw new IllegalArgumentException("Unknown function: " + function);
        }
    }

    /** True if the two decimals are equal within the tolerance. */
    public static boolean isClose(double expected, double actual) {
        if (Double.isNaN(expected) || Double.isNaN(actual)) return false;
        return Math.abs(expected - actual) <= TOLERANCE;
    }

    // Removes -0.0 and floating-point noise such as 6.1e-17
    private static double clean(double v) {
        return Math.abs(v) < 1e-12 ? 0.0 : v;
    }
}
