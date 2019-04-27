import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class SumFile {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage java SumFile <inputFile> <outputFile>");
            System.exit(0);
        }

        try (
                FileInputStream inStream =
                        new FileInputStream(args[0]);
                FileOutputStream outStream =
                        new FileOutputStream(args[1]);
                Scanner inScanner =
                        new Scanner(inStream);
        ) {
            inScanner.useDelimiter("[^0-9+-]+");
            int ans = 0;

            while (inScanner.hasNext()) {
                String number = inScanner.next();
                ans += Integer.valueOf(number);
            }

            outStream.write(String.valueOf(ans).getBytes());
        } catch (Exception e) {
            System.err.println("Something went wrong...");
        }
    }
}
