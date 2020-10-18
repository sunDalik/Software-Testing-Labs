public class Main {
    public static void main(String[] args) {
        new EquationSystem(new TrigonometryEvaluator(new SinFunction()), new LogarithmEvaluator(new NaturalLogarithm())).writeToCSV("csv/equation_system.csv", -9.99, 10.0, 0.05, 1e-3);
        new TrigonometryEvaluator(new SinFunction()).writeToCSV("csv/trigonometry_evaluator.csv", -10.0, 10.0, 0.05, 1e-3);
        new LogarithmEvaluator(new NaturalLogarithm()).writeToCSV("csv/logarithm_evaluator.csv", 0.1, 10.0, 0.05, 1e-3);
    }
}
