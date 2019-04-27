import java.util.ArrayList;
import java.util.Scanner;

public class ReverseMax {
    public static ArrayList< ArrayList<Integer> > readDynamic() {
        Scanner input = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        while (input.hasNextLine()) {
            Scanner lineScanner = new Scanner(input.nextLine());
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            
            while (lineScanner.hasNextInt()) {
                tmp.add(lineScanner.nextInt());
            }

            lineScanner.close();
            result.add(tmp);
        }

        input.close();
        return result;
    }

    public static Integer[][] dynamicToMatrix(ArrayList< ArrayList<Integer> > dynamic) {
        int h = dynamic.size(), w = 0;
        for (ArrayList<Integer> line : dynamic) {
            if (w < line.size()) 
                w = line.size();
        }

        Integer[][] result = new Integer[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < dynamic.get(i).size(); j++) {
                result[i][j] = dynamic.get(i).get(j);
            }
        }

        return result;
    }

    public static Integer[] getLinesMax(Integer[][] matrix) {
        Integer[] result = new Integer[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (Integer elem : matrix[i]) {
                if (elem != null && (result[i] == null || result[i] < elem)) {
                    result[i] = elem;
                }
            }
        }

        return result;
    }

    public static Integer[] getColumnsMax(Integer[][] matrix) {
        if (matrix.length == 0) return new Integer[0];

        Integer[] result = new Integer[matrix[0].length];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] != null && (result[i] == null || result[i] < matrix[j][i])) {
                    result[i] = matrix[j][i];
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList< ArrayList<Integer> > dynamicData = readDynamic();
        Integer[][] matrix = dynamicToMatrix(dynamicData);
        Integer[] linesMax = getLinesMax(matrix);
        Integer[] columnsMax = getColumnsMax(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null) {
                    System.out.printf("%d ", Math.max(linesMax[i], columnsMax[j]));
                }
            }

            System.out.println();
        }
    }
}
