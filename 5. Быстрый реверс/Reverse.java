import java.util.ArrayList;

public class Reverse {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();

        try (
                FastScanner scanner =
                        new FastScanner(System.in)
        ) {
            while (scanner.hasNextInt()) {
                int lines = scanner.getLines();
                while (data.size() <= lines) {
                    data.add(new ArrayList<>());
                }

                data.get(data.size() - 1).add(scanner.nextInt());
            }

            while (data.size() < scanner.getLines()) {
                data.add(new ArrayList<>());
            }
        } catch (Exception e) {
            System.err.print("Something went wrong.");
        }

        for (int i = data.size() - 1; 0 <= i; i--) {
            ArrayList<Integer> row = data.get(i);

            for (int j = row.size() - 1; 0 <= j; j--) {
                Integer number = row.get(j);

                System.out.printf("%d ", number);
            }

            System.out.println();
        }
    }
}