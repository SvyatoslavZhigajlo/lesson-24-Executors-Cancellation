import java.util.concurrent.Callable;

public class Stopwatch implements Callable {
    int second;

    public Stopwatch(int second) {
        this.second = second;
    }

    @Override
    public Integer call() throws InterruptedException {
        int i = 0;
        while (i <= second) {
            System.out.println("Прошло: " + i + " секунд");
            Thread.sleep(1000);
            i++;
        }
        return i;
    }
}
