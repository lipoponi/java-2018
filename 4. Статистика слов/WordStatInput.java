import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class WordStatInput {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage java WordStatInput <inputFile> <outputFile>");
            System.exit(0);
        }

        try (
                FileInputStream fiStream =
                        new FileInputStream(args[0]);
                Scanner scan =
                        new Scanner(fiStream, "UTF-8")
        ) {
            scan.useDelimiter("[^\\p{L}\\p{Pd}']+");
            ArrayList<String> orderedKeys = new ArrayList<String>();
            HashMap<String, Integer> wordStat = new HashMap<String, Integer>();

            while (scan.hasNext()) {
                String key = scan.next().toLowerCase();

                if (!wordStat.containsKey(key)) {
                    orderedKeys.add(key);
                    wordStat.put(key, 0);
                }

                Integer prev = wordStat.get(key);
                wordStat.put(key, prev + 1);
            }

            try (
                    FileOutputStream foStream =
                            new FileOutputStream(args[1]);
                    PrintStream printOut =
                            new PrintStream(foStream, true, "UTF-8")
            ) {
                for (String key : orderedKeys) {
                    printOut.printf("%s %d\n", key, wordStat.get(key));
                }
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
