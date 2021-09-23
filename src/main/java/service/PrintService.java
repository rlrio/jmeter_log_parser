package service;

public interface PrintService {
    void print(String message);
    static void printMessage(String message) {
        System.out.print(message);
    }
}
