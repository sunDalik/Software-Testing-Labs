import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class EquationSystem {
    private ITrigonometryEvaluator trigonometryEvaluator;
    private ILogarithmEvaluator logarithmEvaluator;

    public EquationSystem(ITrigonometryEvaluator trigonometryEvaluator, ILogarithmEvaluator logarithmEvaluator) {
        this.trigonometryEvaluator = trigonometryEvaluator;
        this.logarithmEvaluator = logarithmEvaluator;
    }

    public Double solve(Double x, Double eps) {
        if (x.isInfinite() || x.isNaN() || eps.isInfinite() || eps.isNaN()) {
            return Double.NaN;
        }
        if (x <= 0) {
            double csc = trigonometryEvaluator.csc(x, eps);
            double sin = trigonometryEvaluator.sin(x, eps);
            double sec = trigonometryEvaluator.sec(x, eps);
            double cot = trigonometryEvaluator.cot(x, eps);
            return Math.pow(csc * sin / sec, 2) / Math.pow(cot, 3) * sin;
        } else {
            double ln = logarithmEvaluator.ln(x, eps);
            double log_3 = logarithmEvaluator.log(x, 3, eps);
            double log_10 = logarithmEvaluator.log(x, 10, eps);
            return Math.pow(Math.pow(ln, 2) / ln, 2) * log_3 + log_10 + ln / (ln + log_3);
        }
    }

    public boolean writeToCSV(String fileName, double leftBound, double rightBound, double step, double eps) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            return false;
        }
        pw.write("x,result\n");
        double x = leftBound;
        while (x <= rightBound) {
            pw.append(String.format("%s,%s\n", x, solve(x, eps)));
            x += step;
        }
        pw.flush();
        pw.close();
        return true;
    }
}
