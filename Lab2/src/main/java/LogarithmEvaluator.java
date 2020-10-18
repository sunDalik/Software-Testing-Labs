import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

    public boolean writeToCSV(String fileName, double leftBound, double rightBound, double step, double eps) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            return false;
        }
        pw.write("x,ln,log3,log10\n");
        double x = leftBound;
        while (x <= rightBound) {
            pw.append(String.format("%s,%s,%s,%s\n", x, ln(x, eps), log(x, 3, eps), log(x, 10, eps)));
            x += step;
        }
        pw.flush();
        pw.close();
        return true;
    }
}
