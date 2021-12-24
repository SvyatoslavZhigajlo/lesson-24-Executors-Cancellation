public class Fibonacci implements Runnable {

    Integer fibonacci;
    int f0 = 1;
    int f1 = 1;


    public Fibonacci(Integer fibonacci) {
        this.fibonacci = fibonacci;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int f2;
        System.out.printf("Результат вывода числа Фиббоначи для " + "\"" + fibonacci + "\" :" +
                f0 + " " + f1 + " ");
        for (int i = 3; i <= fibonacci; i++) {
            f2 = f0 + f1;
            System.out.printf(f2 + " ");
            f0 = f1;
            f1 = f2;
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }

        System.out.println();
    }
}
