import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {

//      ДЗ 26 Задание 1 -Реализовать подсчет факториала в отдельном классе с поддержкой отмены.
//      Подробно выводить каждый шаг программы.
        Factorial factorial = new Factorial(10);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> result = executor.submit(factorial);

//      ДЗ26 Задание 2 -Реализовать подсчет чисел Фибоначчи с сохранением последней пары чисел в полях класса
//      задачи (отдельный класс для задачи; поддержка отмены). Подробно выводить каждый шаг программы.
        Fibonacci fibonacci = new Fibonacci(7);
        Future<?> resultFibonacci = executor.submit(fibonacci);

//      ДЗ 26 Задание 3 - Реализовать секундомер (отдельный класс для задачи; поддержка отмены).
//      Подробно выводить каждый шаг программы.
        Stopwatch stopwatch = new Stopwatch(5);
        Future<Integer> resultStopwatch = executor.submit(stopwatch);

//        ДЗ 26 Задание 4 - Реализовать копирование файловых потоков (отдельный класс для задачи; поддержка отмены).
//        Подробно выводить каждый шаг программы.
        StreamCopyFile copyFile = new StreamCopyFile("F:\\Dobro pozhalovat v Zombiljend.avi");
        Future<?> streamCopy = executor.submit(copyFile);

        //закрытие всех потоков
        executor.shutdown();

        //блок ожидания результата по заданию 1
        try {
            Double resultFactorial = result.get(1, TimeUnit.SECONDS);
            System.out.println("Факториал числа \"" + factorial.factorial + "\" равен - " + resultFactorial + "\n");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException | TimeoutException e) {
            result.cancel(true);
        }

//        Блок ожидания результата по заданию 2
        try {
            resultFibonacci.get(5, TimeUnit.SECONDS);
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException | TimeoutException e1) {
            resultFibonacci.cancel(true);
            System.out.println("Расчет числа фибоначи отменен по длительности времени");
        }

//      Блок ожилания результата по заданию 3
        System.out.println("\nСтарт секундомера");
        try {
            Integer returnResultStopwatch = resultStopwatch.get(4, TimeUnit.SECONDS);
            System.out.println("Секундомер остановлен на - " + returnResultStopwatch + " секунде");
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        } catch (InterruptedException | TimeoutException e2) {
            resultStopwatch.cancel(true);
            System.out.println("\nСекундомер остановлен принудительно ");
        }

//        Блок ожидания результата по заданию 4
        try {
            streamCopy.get(5, TimeUnit.SECONDS);
            System.out.println();
        } catch (ExecutionException e3) {
            e3.printStackTrace();
        }catch (InterruptedException | TimeoutException e3){
            streamCopy.cancel(true);
            System.out.println("\nКопирование отменено cистемно" );
        }
    }

}
