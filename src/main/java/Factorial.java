import java.util.concurrent.Callable;

public class Factorial implements Callable<Double> {

    public double factorial;

    public Factorial(double factorial) {
        this.factorial = factorial;
    }

    @Override
    public Double call() {
        System.out.println("Запуск расчета Факториала для числа - \"" + factorial + "\"");
        double resultFactorial = 1;
        for (int i = 1; i <= factorial; i++) {
            resultFactorial = resultFactorial * i;
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println("Вычесление факториала закончено");
        return resultFactorial;
    }

}
