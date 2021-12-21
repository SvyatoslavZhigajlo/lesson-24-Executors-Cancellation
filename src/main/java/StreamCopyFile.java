import java.io.*;

public class StreamCopyFile implements Runnable {

    String fis;

    public StreamCopyFile(String fis) {
        this.fis = fis;
    }

    @Override
    public void run() {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream("F:\\IdeaCopy\\DobroPozhalovatVZombiljendBuffered.avi"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\nСтарт копирования файла!!");
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(fis))) {
            int len;
            byte[] buffer = new byte[4096];
            float sizeFile = 0f;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, len);
                System.out.println("Скопировано - " + (sizeFile = sizeFile + 0.005f) + " Mb");
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
