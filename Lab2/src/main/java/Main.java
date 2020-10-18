public class Main {
    public static void main(String[] args) {
        new EquationSystem(new TrigonometryEvaluator(new SinFunction()), new LogarithmEvaluator(new NaturalLogarithm())).writeToCSV("csv/equation_system.csv", -10.0, 10.0, 0.25, 1e-6);
        new TrigonometryEvaluator(new SinFunction()).writeToCSV("csv/trigonometry_evaluator.csv", -10.0, 10.0, 0.25, 1e-6);
        new LogarithmEvaluator(new NaturalLogarithm()).writeToCSV("csv/logarithm_evaluator.csv", 0.1, 10.0, 0.1, 1e-6);
    }
}
