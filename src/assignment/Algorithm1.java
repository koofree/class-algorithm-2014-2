package assignment;

/**
 * Created by Koo Lee on 10/4/2014.
 */
public class Algorithm1 {

    public static long F(int n) {
        if (n == 1) return n;
        else return F(n - 1) + n;
    }

    public static double Random() {
        long n = 100000;
        return System.nanoTime() % (double) n / (double) n;
    }

    public static double Random2() {
        long n = 1000;
        double p = 0.5;
        double result = 0;
        for (long i = 0; i < n; i++) {
            if (Random() < p) {
                result += 1;
            }
        }
        return (result - (n / 2));
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i > 0; i--) System.out.println(Random2());
    }
}
