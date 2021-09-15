package service;

import entity.Record;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadService {

    public static List<Record> parseNotesFromFile(String filePath) {
        List<Record> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            int counter = 1;
            while (reader.ready()) {
                String line = reader.readLine();
                String[] arr = line.split(",");
                if (arr.length == 17) {
                    long timeStamp = Long.parseLong(arr[0]);
                    int elapsed = Integer.parseInt(arr[1]);
                    String label = arr[2];
                    int responseCode = Integer.parseInt(arr[3]);
                    String responseMessage = arr[4];
                    String threadName = arr[5];
                    String dataType = arr[6];
                    boolean success = Boolean.parseBoolean(arr[7]);
                    String failureMessage = arr[8];
                    int bytes = Integer.parseInt(arr[9]);
                    int sentBytes = Integer.parseInt(arr[10]);
                    int grpThreads = Integer.parseInt(arr[11]);
                    int allThreads = Integer.parseInt(arr[12]);
                    String URL = arr[13];
                    int latency = Integer.parseInt(arr[14]);
                    int idleTime = Integer.parseInt(arr[15]);
                    int connect = Integer.parseInt(arr[16]);
                    Record note = new Record(counter,
                            timeStamp,
                            elapsed,
                            label,
                            responseCode,
                            responseMessage,
                            threadName,
                            dataType,
                            success,
                            failureMessage,
                            bytes,
                            sentBytes,
                            grpThreads,
                            allThreads,
                            URL,
                            latency,
                            idleTime,
                            connect);
                    list.add(note);
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
