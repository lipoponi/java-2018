import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class WordStatInput {
    private static String readFileToLine(String filePath, String charset) throws FileNotFoundException {
        try (
            Scanner scan =
                new Scanner(new FileInputStream(filePath), charset);
        ) {
            StringBuffer buffer = new StringBuffer();

            while (scan.hasNextLine()) {
                buffer.append(scan.nextLine()).append(" ");
            }

            return buffer.toString();
        } catch (Exception e) {
            throw new FileNotFoundException();
        }
    }

    private static String[] splitToWordArray(String text) {
        String[] unfiltred = text.split("[^\\p{L}\\p{Pd}']+");
        String[] filtred = Arrays.stream(unfiltred).filter(x -> !x.isEmpty()).toArray(String[]::new);
        return filtred;
    }

    public static void main(String[] args) {
        try (
            FileOutputStream foStream =
                new FileOutputStream(args[1]);
            PrintStream out =
                new PrintStream(foStream, true, "UTF-8");
        ) {
            String text = readFileToLine(args[0], "UTF-8").toLowerCase();
            String[] words = splitToWordArray(text);

            ArrayList<String> orderedKeys = new ArrayList<String>();
            HashMap<String, Integer> stats = new HashMap<String, Integer>();
            
            for (String word : words) {
                if (!stats.containsKey(word)) {
                    orderedKeys.add(word);
                    stats.put(word, 0);
                }
                
                Integer prev = stats.get(word);
                stats.put(word, prev + 1);
            }

            for (String key : orderedKeys) {
                out.println(key + " " + stats.get(key));
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}
