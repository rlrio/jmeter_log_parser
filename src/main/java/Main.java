import entity.Record;
import service.InfoService;
import service.PrintService;
import service.PrintServiceImpl;
import service.ReadService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import static service.PrintService.printMessage;
import static service.PrintServiceImpl.HELP_MESSAGE;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].contains("help")) {
                printMessage(HELP_MESSAGE);
            } else {
                String fileFrom = args[0];
                String fileTo = "0";
                String methodName = "0";
                int params = 0;
                int mode = 0;
                switch (args.length) {
                    case 2: {
                        if (args[1].contains("/") || args[1].contains("\\")) {
                            fileTo = args[1];
                            mode = 1;
                        } else {
                            methodName = args[1];
                        }
                        break;
                    }
                    case 3: {
                        if (args[1].contains("/") || args[1].contains("\\")) {
                            fileTo = args[1];
                            methodName = args[2];
                            mode = 1;
                        } else {
                            methodName = args[1];
                            params = Integer.parseInt(args[2]);
                        }
                        break;
                    }
                    case 4: {
                        fileTo = args[1];
                        methodName = args[2];
                        params = Integer.parseInt(args[3]);
                        mode = 1;
                        break;
                    }
                }
                printStats(fileFrom, fileTo, methodName, params, mode);
            }
        } else {
            printMessage("No args were found\n" + HELP_MESSAGE);
        }
    }

    private static void printStats(String fileFrom, String fileTo, String methodName, int params, int mode) {
        List<Record> records = ReadService.parseNotesFromFile(fileFrom);
        PrintService printService = new PrintServiceImpl(mode, fileTo);
        InfoService service = new InfoService(records);
        Method[] methodsArr = InfoService.class.getDeclaredMethods();
        for (Method m : methodsArr) {
            if (Modifier.toString(m.getModifiers()).equals("public")) {
                if (methodName.equals("0")) {
                    try {
                        m.invoke(service, printService, params);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else if (m.getName().toLowerCase().contains(methodName)) {
                    try {
                        m.invoke(service, printService, params);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    printMessage(String.format("Method with name %s has not been found", methodName));
                }
            }
        }
    }

}
