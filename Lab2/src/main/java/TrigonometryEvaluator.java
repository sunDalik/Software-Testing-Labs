public class TrigonometryEvaluator implements ITrigonometryEvaluator{
    private ISinFunction baseFunction;

    public TrigonometryEvaluator(ISinFunction baseFunction) {
        this.baseFunction = baseFunction;
    }

    public Double sin(double x, double eps) {
        return baseFunction.sin(x, eps);
    }

    public Double sec(double x, double eps) {
        if (Math.abs(cos(x, eps) - 0) < eps || Double.isNaN(cos(x, eps))) {
            return Double.NaN;
        }
        return 1 / cos(x, eps);
    }

    public Double csc(double x, double eps) {
        if (Math.abs(sin(x, eps) - 0) < eps || Double.isNaN(sin(x, eps))) {
            return Double.NaN;
        }
        return 1 / sin(x, eps);
    }

    public Double cot(double x, double eps) {
        if (Math.abs(sin(x, eps) - 0) < eps || Double.isNaN(cos(x, eps)) || Double.isNaN(sin(x, eps))) {
            return Double.NaN;
        }
        return cos(x, eps) / sin(x, eps);
    }

    private Double cos(double x, double eps) {
        return baseFunction.sin(Math.PI / 2 + x, eps);
    }
}
