public class TrigonometryEvaluator {
    public static Double sec(double x, double eps) {
        return 1 / cos(x, eps);
    }

    public static Double csc(double x, double eps) {
        return 1 / SinFunction.sin(x, eps);
    }

    public static Double cot(double x, double eps) {
        return cos(x, eps) / SinFunction.sin(x, eps);
    }

    public static Double cos(double x, double eps) {
        return SinFunction.sin(Math.PI / 2 + x, eps);
    }
}