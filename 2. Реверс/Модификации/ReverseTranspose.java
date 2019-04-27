import java.util.ArrayList;
import java.util.Scanner;

public class ReverseTranspose {
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

    public static Integer[][] dynamicToMatrix(ArrayList< ArrayList<Integer> > dynamic) {
        int h = dynamic.size(), w = 0;
        for (ArrayList<Integer> line : dynamic) {
            if (w < line.size()) w = line.size();
        }

        Integer[][] result = new Integer[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < dynamic.get(i).size(); j++) {
                result[i][j] = dynamic.get(i).get(j);
            }
        }

        return result;
    }

    public static Integer[][] transposeMatrix(Integer[][] source) {
        int h = source[0].length, w = source.length;
        Integer[][] result = new Integer[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[i][j] = source[j][i];
            }
        }

        return result;
    }

    public static void printMatrix(Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    System.out.printf("%d ", matrix[i][j]);
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        ArrayList< ArrayList<Integer> > dynamicData = readDynamic();
        Integer[][] matrix = dynamicToMatrix(dynamicData);
        Integer[][] transposed = transposeMatrix(matrix);

        printMatrix(transposed);
    }
}
