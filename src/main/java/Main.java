import entity.Record;
import service.InfoService;
import service.ReadService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Record> records = ReadService.parseNotesFromFile(args[0]);
        InfoService infoService = new InfoService(records);
        infoService.printErrorStatistics();
        infoService.printErrorStatistics1();
    }
}
