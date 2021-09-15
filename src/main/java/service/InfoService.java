package service;

import entity.Record;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InfoService {
    private List<Record> records;

    public InfoService(List<Record> records) {
        this.records = records;
    }

    public void printErrorStatisticsTotal() {
        double totalRequests = records.get(records.size() - 1).getId();
        records.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode))
                .forEach((key, value) -> printMessage(key + ": " + value.size() + " " + value.size() / totalRequests * 100 + "%\n"));

    }

    public void printErrorStatisticsByEachThread() {
        Map<Integer, List<Record>> collect = records.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode));
        collect.forEach((key, value) -> {
            printMessage(key.toString() + "\n");
            value.stream()
                    .collect(Collectors.groupingBy(Record::getThreadName))
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()))
                    .forEach(el -> printMessage(el.getKey() + ": " + el.getValue().size() + "\n"));
        });
    }

    public void printErrorStatisticsByThreadName() {
        Map<Integer, List<Record>> collect = records.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode));
        collect.forEach((key, value) -> {
            printMessage(key.toString() + "\n");
            value.stream()
                    .collect(Collectors.groupingBy(e -> e.getThreadName().substring(0, e.getThreadName().length()-4).trim()))
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()))
                    .forEach(el -> printMessage(el.getKey() + ": " + el.getValue().size() + "\n"));
        });
    }

    public void printErrorStatisticsByURL10Limit() {
        Map<Integer, List<Record>> collect = records.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode));
        collect.forEach((key, value) -> {
            printMessage(key.toString() + "\n");
            value.stream()
                    .collect(Collectors.groupingBy(Record::getURL))
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()))
                    .limit(10)
                    .forEach(el -> printMessage(el.getKey() + ": " + el.getValue().size() + "\n"));
        });
    }

    private void printMessage(String message) {
        System.out.print(message);
    }
}