import java.io.IOException;
import java.io.Closeable;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.ArrayDeque;

public class FastScanner implements Closeable, AutoCloseable {
    private final InputStream input;
    private final StringBuffer buffer = new StringBuffer();
    private int linesCount = 0;

    public FastScanner(InputStream input) {
        this.input = input;
    }

    public FastScanner(String inputString) {
        byte[] inputBytes = inputString.getBytes();
        this.input = new ByteArrayInputStream(inputBytes);
    }

    public FastScanner(byte[] inputByteArray) {
        this.input = new ByteArrayInputStream(inputByteArray);
    }

    public void close() throws IOException {
        this.input.close();
    }

    public int getLinesCount() {
        return this.linesCount;
    }

    public boolean hasNextInt() throws IOException {
        if (this.buffer.length() != 0) {
            return true;
        }

        int tmp;

        while ((tmp = this.input.read()) != -1) {
            char ch = (char) tmp;

            if (Character.isDigit(ch)) {
                this.buffer.append(ch);
                return true;
            } else if (ch == '-') {
                this.buffer.setLength(0);
                this.buffer.append(ch);
            } else if (ch == '\n') {
                this.linesCount++;
            }
        }

        this.buffer.setLength(0);
        return false;
    }

    public Integer nextInt() throws IOException {
        Integer result = null, sum = null;
        boolean positive = true;

        while (true) {
            int tmp;
            char ch;

            if (this.buffer.length() != 0) {
                ch = this.buffer.charAt(0);
                this.buffer.deleteCharAt(0);
            } else if ((tmp = this.input.read()) != -1) {
                ch = (char) tmp;
            } else {
                break;
            }

            if (ch == '\n') {
                this.linesCount++;
            }

            if (Character.isDigit(ch)) {
                sum = (sum == null) ? 0 : sum * 10;
                sum += ch - '0';
            } else {
                if (sum != null) {
                    result = (positive ? 1 : -1) * sum;
                }

                sum = null;
                positive = (ch != '-');

                if (result != null) {
                    return result;
                }
            }
        }

        if (sum != null) {
            result = (positive ? 1 : -1) * sum;
        }

        return result;
    }
}