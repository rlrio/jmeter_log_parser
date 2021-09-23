package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrintServiceImpl implements PrintService {
    public static final String HELP_MESSAGE = "For the program to work properly you have to add program arguments:\n " +
            "<pathToJMeterCSVFile> <pathToOutputFile> <methodName or its part> <number to limit the amount of printed lines>\n" +
            "Except for the path to JMeter file the other arguments are optional.\n" +
            "If you do not add path to output file the program will print the result to the Console.\n" +
            "If you do not add the name or part of the name of the method all methods will be invoked.\n" +
            "If you do not add the number to limit the amount of lines of the output, all lines will be printed.\n" +
            "\nFor now there only 4 methods available in this app:\n" +
            "- printErrorStatisticsTotal\n" +
            "- printErrorStatisticsByEachThread\n" +
            "- printErrorStatisticsByThreadName\n" +
            "- printErrorStatisticsByURL";
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
            PrintService.printMessage(message);
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

}
