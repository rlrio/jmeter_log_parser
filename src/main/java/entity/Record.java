package entity;

public class Record {
    private static String SEPARATOR = "|";
    private static String HEADER = "id" + SEPARATOR +
            "timeStamp" + SEPARATOR +
            "elapsed" + SEPARATOR +
            "label" + SEPARATOR +
            "responseCode" + SEPARATOR +
            "responseMessage" + SEPARATOR +
            "threadName" + SEPARATOR +
            "dataType" + SEPARATOR +
            "success" + SEPARATOR +
            "failureMessage" + SEPARATOR +
            "bytes" + SEPARATOR +
            "sentBytes" + SEPARATOR +
            "grpThreads" + SEPARATOR +
            "allThreads" + SEPARATOR +
            "URL" + SEPARATOR +
            "Latency" + SEPARATOR +
            "IdleTime" + SEPARATOR +
            "Connect\n";
    private long id;
    private long timeStamp;
    private int elapsed;
    private String label;
    private int responseCode;
    private String responseMessage;
    private String threadName;
    private String dataType;
    private boolean success;
    private String failureMessage;
    private int bytes;
    private int sentBytes;
    private int grpThreads;
    private int allThreads;
    private String URL;
    private int latency;
    private int idleTime;
    private int connect;


    public Record(long id, long timeStamp, int elapsed, String label, int responseCode, String responseMessage, String threadName, String dataType, boolean success, String failureMessage, int bytes, int sentBytes, int grpThreads, int allThreads, String url, int latency, int idleTime, int connect) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.elapsed = elapsed;
        this.label = label;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.threadName = threadName;
        this.dataType = dataType;
        this.success = success;
        this.failureMessage = failureMessage;
        this.bytes = bytes;
        this.sentBytes = sentBytes;
        this.grpThreads = grpThreads;
        this.allThreads = allThreads;
        URL = url;
        this.latency = latency;
        this.idleTime = idleTime;
        this.connect = connect;
    }

    public long getId() {
        return id;
    }

    public String getDataType() {
        return dataType;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getElapsed() {
        return elapsed;
    }

    public String getLabel() {
        return label;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getThreadName() {
        return threadName;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public int getBytes() {
        return bytes;
    }

    public int getSentBytes() {
        return sentBytes;
    }

    public int getGrpThreads() {
        return grpThreads;
    }

    public int getAllThreads() {
        return allThreads;
    }

    public String getURL() {
        return URL;
    }

    public int getLatency() {
        return latency;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public int getConnect() {
        return connect;
    }

    @Override
    public String toString() {
        return id + SEPARATOR +
                timeStamp + SEPARATOR +
                elapsed + SEPARATOR +
                label + SEPARATOR +
                responseCode + SEPARATOR +
                responseMessage + SEPARATOR +
                threadName + SEPARATOR +
                dataType + SEPARATOR +
                success + SEPARATOR +
                failureMessage + SEPARATOR +
                bytes + SEPARATOR +
                sentBytes + SEPARATOR +
                grpThreads + SEPARATOR +
                allThreads + SEPARATOR +
                URL + SEPARATOR +
                latency + SEPARATOR +
                idleTime + SEPARATOR +
                connect;
    }
}
