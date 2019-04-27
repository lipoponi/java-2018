import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class WordStatLineIndex {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("usage java WordStatCount <inputFile> <outputFile>");
            return;
        }

        try (
                FileInputStream inStream = 
                        new FileInputStream(args[0]);
                FastScanner inScanner =
                        new FastScanner(inStream, "UTF-8");
        ) {
            ArrayList<String> orderedKeys = new ArrayList<>();
            HashMap<String, ArrayList<String>> wordStat = new HashMap<>();

            int wordNo = 0, lastLineNo = 0;
            while (inScanner.hasNextWord()) {
                if (inScanner.getLines() != lastLineNo) {
                    wordNo = 0;
                }

                lastLineNo = inScanner.getLines();
                String currentWord = inScanner.nextWord().toLowerCase();
                wordNo++;

                if (!wordStat.containsKey(currentWord)) {
                    orderedKeys.add(currentWord);
                    wordStat.put(currentWord, new ArrayList<String>());
                }

                wordStat.get(currentWord).add(lastLineNo + 1 + ":" + wordNo);
            }

            orderedKeys.sort(String.CASE_INSENSITIVE_ORDER);
            
            try (
                    PrintStream printOut =
                            new PrintStream(new File(args[1]), "UTF-8")
            ) {
                for (String currentKey : orderedKeys) {
                    int countOfEntries = wordStat.get(currentKey).size();
                    printOut.printf("%s %d", currentKey, countOfEntries);
                    
                    for (String wordEntry : wordStat.get(currentKey)) {
                        printOut.printf(" %s", wordEntry);
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