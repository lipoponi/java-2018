import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class SumAbcFile {
    private static String abcToDec(String abcString) {
        char[] result = abcString.toCharArray();

        for (int i = 0; i < result.length; i++) {
            if ('a' <= result[i] && result[i] <= 'j') {
                result[i] -= 'a' - '0';
            }
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage java SumAbcFile <inputFile> <outputFile>");
            System.exit(0);
        }

        try (
                FileInputStream inStream =
                        new FileInputStream(args[0]);
                Scanner inScanner =
                        new Scanner(inStream)
        ) {
            StringBuffer buffer = new StringBuffer();

            while (inScanner.hasNextLine()) {
                String line = inScanner.nextLine();
                buffer.append(line).append(" ");
            }

            String lineOfNumbers = buffer.toString().toLowerCase();
            String[] seperated = lineOfNumbers.split("[^a-j+-]+");
            int ans = 0;

            for (String number : seperated) {
                if (!number.isEmpty()) {
                    number = abcToDec(number);
                    ans += (int) Long.parseLong(number, 10);
                }
            }

            try (
                    FileOutputStream outStream =
                            new FileOutputStream(args[1])
            ) {
                outStream.write(String.valueOf(ans).getBytes());
            } catch (FileNotFoundException e) {
                System.err.println("Output file not found.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found.");
        } catch (Exception e) {
            System.err.println("Something went wrong...");
        }
    }
}
