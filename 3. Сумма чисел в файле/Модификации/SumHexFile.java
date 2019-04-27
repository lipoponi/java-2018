import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SumHexFile {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage java SumHexFile <inputFile> <outputFile>");
            return;
        }

        try (
                Scanner inScanner =
                        new Scanner(new File(args[0]))
        ) {
            int sum = 0, lineNo = 0;

            while (inScanner.hasNextLine()) {
                String line = inScanner.nextLine().toLowerCase();
                String[] numbers = line.split("[^0-9a-fx+-]+");
                lineNo++;

                for (String currentNumber : numbers) {
                    if (!currentNumber.isEmpty()) {
                        int radix = 10;

                        if (currentNumber.startsWith("0x")) {
                            radix = 16;
                            currentNumber = currentNumber.substring(2);
                        }

                        try {
                            sum += Long.parseLong(currentNumber, radix);
                        } catch (NumberFormatException e) {
                            System.err.printf("Invalid number format in line %d.\n", lineNo);
                            System.exit(0);
                        }
                    }
                }
            }

            try (
                    PrintStream outStream =
                            new PrintStream(args[1])
            ) {
                outStream.print(sum);
            } catch (FileNotFoundException e) {
                System.err.println("Output file not found.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found.");
        } catch (Exception e) {
            System.err.println("Something went wrong.");
        }
    }
}
