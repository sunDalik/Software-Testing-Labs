public class LogarithmEvaluator implements ILogarithmEvaluator {
    private INaturalLogarithm baseLog;

    public LogarithmEvaluator(INaturalLogarithm baseLog) {
        this.baseLog = baseLog;
    }

    public Double ln(double x, double eps) {
        return baseLog.ln(x, eps);
    }

    public Double log(double x, double base, double eps) {
        if (x <= 0 || base <= 0 || base == 1 || Double.isNaN(x) || Double.isNaN(base) || Double.isNaN(eps) || Double.isInfinite(base) || Double.isInfinite(eps) || Double.isInfinite(x)) {
            return Double.NaN;
        }
        return baseLog.ln(x, eps) / baseLog.ln(base, eps);
    }
}
