import java.io.*;

public class FastScanner implements Closeable, AutoCloseable {
    private final Reader input;
    private final StringBuffer buffer = new StringBuffer();
    private int lines = 0;

    public FastScanner(Reader input) {
        this.input = input;
    }

    public FastScanner(String inputString) {
        char[] inputChars = inputString.toCharArray();
        this.input = new CharArrayReader(inputChars);
    }

    public FastScanner(char[] inputCharArray) {
        this.input = new CharArrayReader(inputCharArray);
    }

    public FastScanner(InputStream byteStream, String charset) throws UnsupportedEncodingException {
        this.input = new InputStreamReader(byteStream, charset);
    }

    public void close() throws IOException {
        this.input.close();
    }

    public int getLines() {
        return this.lines;
    }

    public boolean hasNextWord() throws IOException {
        if (this.buffer.length() != 0) {
            return true;
        }

        int tmp;

        while ((tmp = this.input.read()) != -1) {
            char ch = (char) tmp;

            if (Character.getType(ch) == Character.DASH_PUNCTUATION ||
                Character.isLetter(ch) ||
                ch == '\'') {
                this.buffer.append(ch);
                return true;
            } else if (ch == '\n') {
                this.lines++;
            }
        }

        this.buffer.setLength(0);
        return false;
    }

    public String nextWord() throws IOException {
        StringBuilder resultBuilder = new StringBuilder();
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
                this.lines++;
            }

            if (Character.isLetter(ch) ||
                Character.getType(ch) == Character.DASH_PUNCTUATION ||
                ch == '\'') {
                resultBuilder.append(ch);
            } else if (resultBuilder.length() != 0) {
                    return resultBuilder.toString();
            }
        }

        return resultBuilder.toString();
    }
}