package service;

import entity.Record;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InfoService {
    private List<Record> records;

    public InfoService(List<Record> records) {
        this.records = records;
    }

    public void printErrorStatisticsTotal(PrintService service, int limit) {
        service.print("Error Statistics Total\n");
        double totalRequests = records.get(records.size() - 1).getId();
        records.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode))
                .forEach((key, value) -> service.print(key + ": " + value.size() + " " + value.size() / totalRequests * 100 + "%\n"));
        service.print("\n");
    }

    public void printErrorStatisticsByEachThread(PrintService service, int limit) {
        service.print("Error Statistics By Each Thread\n");
        groupByResponseCode().forEach((key, value) -> {
            service.print(key.toString() + "\n");
            Stream<Map.Entry<String, List<Record>>> sorted = value.stream()
                    .collect(Collectors.groupingBy(Record::getThreadName))
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()));
            printLimit(service, sorted, limit);
        });
        service.print("\n");
    }

    public void printErrorStatisticsByThreadName(PrintService service, int limit) {
        service.print("Error Statistics By Thread Name\n");
        groupByResponseCode().forEach((key, value) -> {
            service.print(key.toString() + "\n");
            Stream<Map.Entry<String, List<Record>>> sorted = value.stream()
                    .collect(Collectors.groupingBy(e -> e.getThreadName().substring(0, e.getThreadName().length() - 4).trim()))
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()));
            printLimit(service, sorted, limit);
        });
        service.print("\n");
    }

    public void printErrorStatisticsByURL(PrintService service, int limit) {
        service.print("Error Statistics By URL\n");
        groupByResponseCode().forEach((key, value) -> {
            service.print(key.toString() + "\n");
            Stream<Map.Entry<String, List<Record>>> sorted = value.stream()
                    .collect(Collectors.groupingBy(Record::getURL))
                    .entrySet()
                    .stream()
                    .sorted((o1, o2) -> Integer.compare(o2.getValue().size(), o1.getValue().size()));
            printLimit(service, sorted, limit);
        });
        service.print("\n");
    }

    private Map<Integer, List<Record>> groupByResponseCode() {
        return records.stream()
                .filter(note -> !note.isSuccess())
                .collect(Collectors.groupingBy(Record::getResponseCode));
    }

    private void printLimit(PrintService service, Stream<Map.Entry<String, List<Record>>> stream, int limit) {
        if (limit > 0) {
            stream
                    .limit(limit)
                    .forEach(el -> service.print(el.getKey() + ": " + el.getValue().size() + "\n"));
        } else {
            stream.forEach(el -> service.print(el.getKey() + ": " + el.getValue().size() + "\n"));
        }
    }

}