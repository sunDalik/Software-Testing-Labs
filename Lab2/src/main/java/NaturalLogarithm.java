public class NaturalLogarithm {
    public static Double getNaturalLogarithm(double x, double precision) {
        double sum = 0;
        double term = 1;
        double n = 1;
        while (Math.abs(term) > precision) {
            sum += term;
            term = 1 / (2 * n + 1) * Math.pow(Math.pow(x - 1, 2) / Math.pow(x + 1, 2), n);
            n++;
        }
        return sum * 2 * (x - 1) / (x + 1);
    }
}
