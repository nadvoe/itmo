package Console;

import java.io.PushbackReader;

public interface ReadWrite {
    int readInt();
    long readLong();
    double readDouble();
    String readLine();
    void write(String text);
    String toValidatedMessage();

    void selectFileReader(PushbackReader reader);
}
