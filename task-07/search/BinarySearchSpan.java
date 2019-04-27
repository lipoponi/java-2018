package search;

public class BinarySearchSpan {
    static class Span {
        public int left, right;

        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(left);
            builder.append(" ");
            builder.append(right - left + 1);
            return builder.toString();
        }

        public boolean equals(Span other) {
            if (other == null || !(other instanceof Span)) {
                return false;
            }

            return this.left == other.left && this.right == other.right;
        }
    }


    // Pre: data[i] >= data[i + 1] for all i even if empty
    static int iterativeLowerBound(int[] data, int query) {
        // Pre
        int l = -1, r = data.length;
        // Inv: (r' == data.size || (r' < data.size && data[r'] <= query)) && (l' == -1 || (0 <= l' < data.size && data[l'] > query)) &&
        //       && (r'' - l'' <= (r' - l' + 1) div 2) && (r' - l' >= 1)
        while (r - l > 1) {
            // Pre && Inv && r' > l' + 1
            int mid = (l + r) / 2;
            // Pre && Inv && r' > l' + 1 && mid == (r' + l') div 2 && l' < mid < r'
            if (data[mid] <= query) {
                // Pre && Inv && r' > l' + 1 && mid = (r' + l') div 2 && data[mid] <= query && l' < mid < r'
                r = mid;

                // r'' == mid && l'' = l'  ->  mid - l'' == (l' + r') div 2 - l' == (r' - l' + 1) div 2
                // l'' = l'  ->  (l'' == -1 || (0 <= l'' < data.size && data[l''] > query))
                // Pre && data[r'' == mid] <= query && l' < mid < r'  -> (r'' < data.size && data[r''] <= query)
                // r' > l' + 1 && r'' == mid && l' < mid < r'  ->   r'' - l'' >= 1

                // Pre && Inv
            } else {
                // Pre && Inv && r' > l' + 1 && mid = (r' + l') div 2 && data[mid] > query
                l = mid;

                // l'' == mid && r'' = r'  -> r'' - mid == r' - (r' + l') div 2 == (r' - l' + 1) div 2
                // r'' == r'  ->  ((r'' == data.size) || (r'' < data.size && data[r''] <= query))
                // Pre && data[l'' == mid] > query && l' < mid < r'  ->  (0 <= l'' < data.size && data[l''] > query)
                // r' > l' + 1 && l'' = mid && l' < mid < r'  ->  r'' - l'' >= 1

                // Pre && Inv
            }
        }
        // !((r' - l') > 1) && Inv(r' - l' >= 1) -> l' + 1 == r'
        // Pre && Inv && (l' + 1 == r')
        // 1) data.size == 0 -> never visited while ->  r' == 0 && l' == -1
        // 2) data.last > x -> never visited if() in while()  ->  r' == data.size() && l' = data.size() - 1
        // 3) r' < data.size && Inv ->  data[r'] <= query
        // 3a) r' == 0
        // 3b) r' > 0 && Inv && (l' + 1 == r') -> data[r' - 1] > query
        return r;
    }
    // Post: (data[i]' == data[i]) && (data.size == 0 && ans == 0) || (data.last > x && ans == data.size) || (ans < data.size && data[ans] <= query && (ans == 0 || data[ans - 1] > query))

    // Pre: data[i] >= data[i + 1] for all i even if empty
    static int iterativeUpperBound(int[] data, int query) {
        // Pre
        int l = -1, r = data.length;
        // Inv: (r' == data.size || (r' < data.size && data[r'] <= query)) && (l' == -1 || (0 <= l' < data.size && data[l'] > query)) &&
        //       && (r'' - l'' <= (r' - l' + 1) div 2) && (r' - l' >= 1)
        while (r - l > 1) {
            // Pre && Inv && r' > l' + 1
            int mid = (l + r) / 2;
            // Pre && Inv && r' > l' + 1 && mid == (r' + l') div 2 && l' < mid < r'
            if (data[mid] < query) {
                // Pre && Inv && r' > l' + 1 && mid = (r' + l') div 2 && data[mid] < query && l' < mid < r'
                r = mid;

                // r'' == mid && l'' = l'  ->  mid - l'' == (l' + r') div 2 - l' == (r' - l' + 1) div 2
                // l'' = l'  ->  (l'' == -1 || (0 <= l'' < data.size && data[l''] >= query))
                // Pre && data[r'' == mid] < query && l' < mid < r'  -> (r'' < data.size && data[r''] < query)
                // r' > l' + 1 && r'' == mid && l' < mid < r'  ->   r'' - l'' >= 1

                // Pre && Inv
            } else {
                // Pre && Inv && r' > l' + 1 && mid = (r' + l') div 2 && data[mid] >= query
                l = mid;

                // l'' == mid && r'' = r'  -> r'' - mid == r' - (r' + l') div 2 == (r' - l' + 1) div 2
                // r'' == r'  ->  ((r'' == data.size) || (r'' < data.size && data[r''] < query))
                // Pre && data[l'' == mid] >= query && l' < mid < r'  ->  (0 <= l'' < data.size && data[l''] >= query)
                // r' > l' + 1 && l'' = mid && l' < mid < r'  ->  r'' - l'' >= 1

                // Pre && Inv
            }
        }
        // !((r' - l') > 1) && Inv(r' - l' >= 1) -> l' + 1 == r'
        // Pre && l + 1 == r
        // 1) data.size == 0 -> it is first level of recursion ->  r == 0 && l == -1
        // 2) data[0] < query -> never visited else() in previous recursions  -> r == 0 && l == -1
        // 3) l != -1  ->  data[l] >= query
        // 3a) l == n - 1
        // 3b) l < n - 1 && Inv && (l + 1 == r) -> data[l + 1] < query
        return l;
    }
    // Post: (query' == query) && (data[i]' == data[i]) && (data.size == 0 || (data[0] < query)) && ans == -1) && (data[ans] >= query && (ans == data.size() - 1 || data[ans + 1] < query)

    // Pre: (data[i]' == data[i]) && (data[i] >= data[i + 1] for all i) && (r == data.size || (r < data.size && data[r] <= query)) &&
    // && (l == -1 || (0 <= l < data.size && data[l] > query)) && (r' - l' <= (r - l + 1) div 2) && (r - l >= 1)
    static int recursiveLowerBound(int[] data, int query, int l, int r) {
        if (r - l == 1) {
            // Pre && Inv && (l' + 1 == r')
            // 1) data.size == 0 -> never visited while ->  r' == 0 && l' == -1
            // 2) data.last > x -> never visited if() in while()  ->  r' == data.size() && l' = data.size() - 1
            // 3) r' < data.size && Inv ->  data[r'] <= query
            // 3a) r' == 0
            // 3b) r' > 0 && Inv && (l' + 1 == r') -> data[r' - 1] > query
            return r;
        } else {

            // Pre && r > l + 1
            int mid = (l + r) / 2;
            // Pre && r > l + 1 && mid == (l + r) div 2 && l < mid < r
            if (data[mid] <= query) {
                // Pre && r > l + 1 && mid == (l + r) div 2 && l < mid < r && data[mid] <= query
                // data[i]' == data[i] && data[i] >= data[i + 1] for all i && query' == query
                // l' == l  ->  (l == -1 || (0 <= l < data.size && data[l] > query))
                // Pre && data[mid] <= query && l < mid < r  ->  (r < data.size && data[r] <= query)
                // r' == mid && l' = l  ->  mid - l' == (l + r) div 2 - l == (r - l + 1) div 2
                // r > l + 1 && r' == mid && l < mid < r  ->   r' - l' >= 1
                // Pre for l' and r'
                return recursiveLowerBound(data, query, l, mid);
                // Post was OK before and data[i] hasn't changed for all i
            } else {
                // Pre && r > l + 1 && mid = (r + l) div 2 && data[mid] > query
                // data[i]' == data[i] && data[i] >= data[i + 1] for all i && query' = query
                // l' == mid && r' = r  -> r' - mid == r - (r + l) div 2 == (r - l + 1) div 2
                // r' == r  -> ((r == data.size) || (r' < data.size && data[r'] <= query))
                // Pre && data[l' == mid] > query && l < mid < r  ->  (0 <= l' < data.size && data[l'] > query)
                // r > l + 1 && l' = mid && l < mid < r  ->  r' - l' >= 1
                // Pre for l' and r'
                return recursiveLowerBound(data, query, mid, r);
                // Post was OK before and data[i] hasn't changed for all i
            }
        }
    }
    // Post: (data[i]' == data[i]) && (data.size == 0 && ans == 0) || (data.last > x && ans == data.size) || (ans < data.size && data[ans] <= query && (ans == 0 || data[ans - 1] > query))

    // Pre: (data[i]' == data[i]) && (data[i] >= data[i + 1] for all i) && (r == data.size || (r < data.size && data[r] < query)) &&
    // && (l == -1 || (0 <= l < data.size && data[l] >= query)) && (r' - l' <= (r - l + 1) div 2) && (r - l >= 1)
    static int recursiveUpperBound(int[] data, int query, int l, int r) {
        if (r - l == 1) {
            // Pre && l + 1 == r
            // 1) data.size == 0 -> it is first level of recursion ->  r == 0 && l == -1
            // 2) data[0] < query -> never visited else() in previous recursions  -> r == 0 && l == -1
            // 3) l != -1  ->  data[l] >= query
            // 3a) l == n - 1
            // 3b) l < n - 1 && Inv && (l + 1 == r) -> data[l + 1] < query
            return l;
        } else {

            // Pre && r > l + 1
            int mid = (l + r) / 2;
            // Pre && r > l + 1 && mid == (l + r) div 2 && l < mid < r
            if (data[mid] < query) {
                // Pre && r > l + 1 && mid == (l + r) div 2 && l < mid < r && data[mid] < query
                // data[i]' == data[i] && data[i] >= data[i + 1] for all i && query' == query
                // l' == l  ->  (l == -1 || (0 <= l < data.size && data[l] >= query))
                // Pre && data[mid] < query && l < mid < r  ->  (r < data.size && data[r] < query)
                // r' == mid && l' = l  ->  mid - l' == (l + r) div 2 - l == (r - l + 1) div 2
                // r > l + 1 && r' == mid && l < mid < r  ->   r' - l' >= 1
                // Pre for l' and r'
                return recursiveUpperBound(data, query, l, mid);
                // Post was OK before and data[i] hasn't changed for all i
            } else {
                // Pre && r > l + 1 && mid = (r + l) div 2 && data[mid] >= query
                // data[i]' == data[i] && data[i] >= data[i + 1] for all i && query' = query
                // l' == mid && r' = r  -> r' - mid == r - (r + l) div 2 == (r - l + 1) div 2
                // r' == r  -> ((r == data.size) || (r' < data.size && data[r'] < query))
                // Pre && data[l' == mid] >= query && l < mid < r  ->  (0 <= l' < data.size && data[l'] >= query)
                // r > l + 1 && l' = mid && l < mid < r  ->  r' - l' >= 1
                // Pre for l' and r'
                return recursiveUpperBound(data, query, mid, r);
                // Post was OK before and data[i] hasn't changed for all i
            }
        }
    }
    // Post: (query' == query) && (data[i]' == data[i]) && (data.size == 0 || (data[0] < query)) && ans == -1) && (data[ans] >= query && (ans == data.size() - 1 || data[ans + 1] < query)


    static Span iterativeSpanSearch(int[] data, int query) {
        Span result = new Span();

        result.left = iterativeLowerBound(data, query);
        result.right = iterativeUpperBound(data, query);

        return result;
    }

    static Span recursiveSpanSearch(int[] data, int query) {
        Span result = new Span();

        result.left = recursiveLowerBound(data, query, -1, data.length);
        result.right = recursiveUpperBound(data, query, -1, data.length);

        return result;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("usage: BinarySearchSpan <query> [...]");
            return;
        }

        try {
            int query = Integer.parseInt(args[0]);
            int[] data = new int[args.length - 1];

            for (int i = 0; i < data.length; i++) {
                data[i] = Integer.parseInt(args[i + 1]);
            }

            Span recursiveResult = recursiveSpanSearch(data, query);
            Span iterativeResult = iterativeSpanSearch(data, query);

            assert(recursiveResult == iterativeResult);

            System.out.println(iterativeResult);
        } catch (NumberFormatException e) {
            System.err.println("Invalid format of input numbers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}