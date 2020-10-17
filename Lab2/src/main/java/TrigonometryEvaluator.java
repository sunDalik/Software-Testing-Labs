public class TrigonometryEvaluator {
    public static Double sec(double x, double precision) {
        return 1 / cos(x, precision);
    }

    public static Double csc(double x, double precision) {
        return 1 / SinFunction.sin(x, precision);
    }

    public static Double cot(double x, double precision) {
        return cos(x, precision) / SinFunction.sin(x, precision);
    }

    public static Double cos(double x, double precision) {
        return SinFunction.sin(Math.PI / 2 + x, precision);
    }
}
