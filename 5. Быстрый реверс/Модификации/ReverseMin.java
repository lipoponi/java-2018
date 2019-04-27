import java.util.ArrayList;

public class ReverseMin {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        try (
                FastScanner scanner =
                        new FastScanner(System.in)
        ) {
            while (scanner.hasNextInt()) {
                int countOfLines = scanner.getLinesCount();
                while (data.size() <= countOfLines) {
                    data.add(new ArrayList<>());
                }

                data.get(data.size() - 1).add(scanner.nextInt());
            }

            while (data.size() < scanner.getLinesCount()) {
                data.add(new ArrayList<>());
            }
        } catch (Exception e) {
            System.err.print("Something went wrong.");
        }

        int rowMaxSize = 0;
        for (ArrayList<Integer> row : data) {
            if (rowMaxSize < row.size()) {
                rowMaxSize = row.size();
            }
        }

        Integer[] minimumsForEachRow = new Integer[data.size()];
        Integer[] minimumsForEachColumn = new Integer[rowMaxSize];

        for (int i = 0; i < data.size(); i++) {
            ArrayList<Integer> row = data.get(i);

            for (int j = 0; j < row.size(); j++) {
                Integer value = row.get(j);

                if (minimumsForEachRow[i] == null || value < minimumsForEachRow[i]) {
                    minimumsForEachRow[i] = value;
                }
                if (minimumsForEachColumn[j] == null || value < minimumsForEachColumn[j]) {
                    minimumsForEachColumn[j] = value;
                }
            }
        }

        for (int i = 0; i < data.size(); i++) {
            ArrayList<Integer> row = data.get(i);

            for (int j = 0; j < row.size(); j++) {
                Integer value = Math.min(minimumsForEachRow[i], minimumsForEachColumn[j]);
                row.set(j, value);
            }
        }

        for (ArrayList<Integer> row : data) {
            for (Integer value : row) {
                System.out.printf("%d ", value);
            }

            System.out.println();
        }
    }
}
