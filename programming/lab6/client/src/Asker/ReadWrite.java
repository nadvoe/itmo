package Asker;

public interface ReadWrite {
    int readInt();
    long readLong();
    double readDouble();
    String readLine();
    void write(String text);
    String toValidatedMessage();
}
