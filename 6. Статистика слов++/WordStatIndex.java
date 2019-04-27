import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class WordStatIndex {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage java WordStatCount <inputFile> <outputFile>");
            System.exit(0);
        }

        try (
                Scanner inScanner =
                        new Scanner(new File(args[0]), "UTF-8")
        ) {
            inScanner.useDelimiter("[^\\p{L}\\p{Pd}']+");
            ArrayList<String> orderedKeys = new ArrayList<>();
            HashMap<String, ArrayList<Integer>> wordStat = new HashMap<>();
            int wordNo = 0;

            while (inScanner.hasNext()) {
                String currentWord = inScanner.next().toLowerCase();
                wordNo++;

                if (!wordStat.containsKey(currentWord)) {
                    orderedKeys.add(currentWord);
                    wordStat.put(currentWord, new ArrayList<Integer>());
                }

                wordStat.get(currentWord).add(wordNo);
            }
            
            try (
                    PrintStream printOut =
                            new PrintStream(new File(args[1]), "UTF-8")
            ) {
                for (String currentKey : orderedKeys) {
                    int countOfEntries = wordStat.get(currentKey).size();
                    printOut.printf("%s %d", currentKey, countOfEntries);
                    
                    for (Integer wordEntry : wordStat.get(currentKey)) {
                        printOut.printf(" %d", wordEntry);
                    }

                    printOut.println();
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