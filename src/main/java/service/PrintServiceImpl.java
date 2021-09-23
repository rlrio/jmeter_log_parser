package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintServiceImpl implements PrintService {
    private final int mode;
    private File file;

    public PrintServiceImpl(int mode, String path) {
        this.mode = mode;
        if (!path.equals("0")) {
            this.file = new File(path);
        }
    }

    @Override
    public void print(String message) {
        if (mode == 0) {
            printMessage(message);
        } else {
            printToFile(message);
        }
    }

    private void printToFile(String message) {
        try (FileWriter writer = new FileWriter(file, true)) {
            message = message.replace(":", "").replace(" ", ",");
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMessage(String message) {
        System.out.print(message);
    }

}
