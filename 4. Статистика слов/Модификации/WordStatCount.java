import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class WordStatCount {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage java WordStatCount <inputFile> <outputFile>");
            return;
        }

        try (
                Scanner inScanner =
                        new Scanner(new File(args[0]), "UTF-8")
        ) {
            inScanner.useDelimiter("[^\\p{L}\\p{Pd}']+");
            ArrayList<String> orderedKeys = new ArrayList<>();
            HashMap<String, Integer> wordStat = new HashMap<>();

            while (inScanner.hasNext()) {
                String currentWord = inScanner.next().toLowerCase();

                if (!wordStat.containsKey(currentWord)) {
                    orderedKeys.add(currentWord);
                    wordStat.put(currentWord, 0);
                }

                Integer prev = wordStat.get(currentWord);
                wordStat.put(currentWord, prev + 1);
            }

            orderedKeys.sort((a, b) -> {
                Integer aValue = wordStat.get(a);
                Integer bValue = wordStat.get(b);
                return aValue.compareTo(bValue);
            });

            try (
                    PrintStream printOut =
                            new PrintStream(new File(args[1]), "UTF-8")
            ) {
                for (String currentKey : orderedKeys) {
                    Integer currentCount = wordStat.get(currentKey);
                    printOut.printf("%s %d\n", currentKey, currentCount);
                }
            } catch (FileNotFoundException e) {
                System.err.println("Output file not found.");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file not found.");
        } catch (Exception e) {
            e.printStackTrace();
            //System.err.println("Something went wrong.");
        }
    }
}
