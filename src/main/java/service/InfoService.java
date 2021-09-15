package service;

import entity.Record;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InfoService {
    private List<Record> notes;

    public InfoService(List<Record> notes) {
        this.notes = notes;
    }

    public void printErrorStatistics() {
        double totalRequests = notes.get(notes.size() - 1).getId();
        notes.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode))
                .forEach((key, value) -> printMessage(key + ": " + value.size() + " " + value.size() / totalRequests * 100 + "%\n"));

    }

    public void printErrorStatistics1() {
        Map<Integer, List<Record>> collect = notes.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode));
        collect.forEach((key, value) -> {
            printMessage(key.toString() + "\n");
            value.stream()
                    //.collect(Collectors.groupingBy(LineFromJMeterLog::getURL))
                    //.collect(Collectors.groupingBy(e -> e.getThreadName().substring(0, e.getThreadName().length()-4).trim()))
                    .collect(Collectors.groupingBy(Record::getThreadName))
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()))
                    //.limit(10)
                    .forEach(el -> printMessage(el.getKey() + ": " + el.getValue().size() + "\n"));
        });


    }

    private void printMessage(String message) {
        System.out.print(message);
    }
}