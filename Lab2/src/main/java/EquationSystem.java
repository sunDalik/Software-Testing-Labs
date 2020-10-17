public class EquationSystem {

    public Double solve(double x, double eps) {
        if (x <= 0) {
            double csc = TrigonometryEvaluator.csc(x, eps);
            double sin = SinFunction.sin(x, eps);
            double sec = TrigonometryEvaluator.sec(x, eps);
            double cot = TrigonometryEvaluator.cot(x, eps);
            return Math.pow(csc * sin / sec, 2) / Math.pow(cot, 3) * sin;
        } else {
            double ln = NaturalLogarithm.ln(x, eps);
            double log_3 = LogarithmEvaluator.log(x, 3, eps);
            double log_10 = LogarithmEvaluator.log(x, 10, eps);
            return Math.pow(Math.pow(ln, 2) / ln, 2) * log_3 + log_10 + ln / (ln + log_3);
        }
    }
}
