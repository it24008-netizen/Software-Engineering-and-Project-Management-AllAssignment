import service.MathService;

/** Simple console tests (no JUnit needed). */
public class MathServiceTest {
    static int pass = 0, fail = 0;

    static void check(String name, boolean ok) {
        System.out.println((ok ? "PASS  " : "FAIL  ") + name);
        if (ok) pass++; else fail++;
    }

    public static void main(String[] args) {
        check("isPrime(7) = 1", MathService.isPrime(7) == 1);
        check("isPrime(8) = 0", MathService.isPrime(8) == 0);
        check("isPrime(1) = 0", MathService.isPrime(1) == 0);
        check("isPrime(2) = 1", MathService.isPrime(2) == 1);
        check("isPrime(0) = 0", MathService.isPrime(0) == 0);
        check("isPrime(-7) = 0", MathService.isPrime(-7) == 0);
        check("isPrime(97) = 1", MathService.isPrime(97) == 1);
        check("17 % 5 = 2", MathService.calculateMod(17, 5) == 2);
        check("-17 % 5 = -2", MathService.calculateMod(-17, 5) == -2);
        check("17 % -5 = 2", MathService.calculateMod(17, -5) == 2);
        check("0 % 5 = 0", MathService.calculateMod(0, 5) == 0);
        boolean threw = false;
        try { MathService.calculateMod(10, 0); } catch (ArithmeticException e) { threw = true; }
        check("Divisor 0 -> validation error", threw);
        check("sin(30) = 0.5", MathService.isClose(0.5, MathService.calculateSin(30)));
        check("cos(60) = 0.5", MathService.isClose(0.5, MathService.calculateCos(60)));
        check("tan(45) = 1.0", MathService.isClose(1.0, MathService.calculateTan(45)));
        check("sin(90) = 1", MathService.isClose(1.0, MathService.calculateSin(90)));
        check("cos(90) = 0", MathService.isClose(0.0, MathService.calculateCos(90)));
        check("tan(90) undefined (NaN)", Double.isNaN(MathService.calculateTan(90)));
        check("tan(270) undefined (NaN)", Double.isNaN(MathService.calculateTan(270)));
        double s = MathService.calculateSin(37), c = MathService.calculateCos(37);
        check("sin^2 + cos^2 = 1 (37 deg)", Math.abs(s * s + c * c - 1) < 1e-12);

        check("all correct = 10", score(true, true, true, true) == 10);
        check("Q1 wrong -> 8", score(false, true, true, true) == 8);
        check("Q3 wrong -> 7", score(true, true, false, true) == 7);
        check("all wrong -> 0", score(false, false, false, false) == 0);

        System.out.println("\n" + pass + " passed, " + fail + " failed");
        if (fail > 0) System.exit(1);
    }

    static int score(boolean p, boolean m, boolean t1, boolean t2) {
        int total = 0;
        if (p) total += 2;
        if (m) total += 2;
        if (t1) total += 3;
        if (t2) total += 3;
        return total;
    }
}
