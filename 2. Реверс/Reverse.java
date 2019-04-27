import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Reverse {
    public static ArrayList< ArrayList<Integer> > readDynamic() {
        Scanner input = new Scanner(System.in);
        ArrayList< ArrayList<Integer> > result = new ArrayList< ArrayList<Integer> >();

        while (input.hasNextLine()) {
            Scanner lineScanner = new Scanner(input.nextLine());
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            
            while (lineScanner.hasNextInt()) {
                tmp.add(lineScanner.nextInt());
            }

            result.add(tmp);
            lineScanner.close();
        }

        input.close();
        return result;
    }
    public static void main(String[] args) {
        ArrayList< ArrayList<Integer> > dynamicData = readDynamic();

        Collections.reverse(dynamicData);
        for (ArrayList<Integer> line : dynamicData) {
            Collections.reverse(line);

            for (Integer elem : line) {
                System.out.printf("%d ", elem);
            }
            System.out.println();
        }
    }
}
