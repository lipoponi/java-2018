package search;

public class BinarySearchMissing {

    //before:
    //data - integers in reverse order, query - integer
    //&& right == data length || (data[right] <= query && 0 <= right < data length)
    //&& left == -1 ||  (data[left] >= query && data length > left >=0)
    //&& left' - right' <= (right - left + 1) / 2
    //&& right - left >= 1
    public static int recursiveMissingSearch(int[] data, int query, int left, int right) {
        //before
        if (left >= right - 1) {
            //before && left >= right - 1
            //data[right] <= query && query - data[right + z] >= query - data[right] for all z > 0
            //&& data[right - z] > query for all z > 0
            //result == right
            if (right != data.length && right != -1 && data[right] == query) {
                //data[right] == query
                return right;
            } else {
                //data[right] most close over query || query > any data element
                // || query < any data element
                return (-right - 1);
            }
        } else {
            //before && left < right - 1
            int mid = (left + right) / 2;
            //left < mid < right && mid == (left + right) / 2
            if (data[mid] > query)
                //left' == mid
                //data[left'] > query && mid <= result <= right
                return recursiveMissingSearch(data, query, mid, right);
            else
                //right' == mid
                //data[right'] <= query && left <= result <= mid
                return recursiveMissingSearch(data, query, left, mid);
        }
    }
    // Post: (query' == query) && (data[i]' == data[i]) && (data length == 0 || (data[0] < query)) && result == -(-1) - 1) && (data[result] >= query && (result == -(data len - 1)-1 || data[result + 1] < query)

    //before: data - integers in reverse order, query - integer value
    public static int iterativeMissingSearch(int[] data, int query) {
        //before
        int left = -1;
        int right = data.length;
        //invariant:
        //(right' == data length || (right' < data length && data[right'] <= query)) &&
        //(left' == -1 || ( left' >= 0 && data[left'] >= query)) &&
        //(right'' - left'' <= (right' - left' + 1) / 2)  && (left' <= right' - 1)
        while (left < right - 1) {
            //invariant && before && right' > left' + 1
            int mid = (left + right) / 2;
            //before && invariant && right' <= mid <= left' && right' > left' + 1
            if (data[mid] > query)
                //invariant && before && right' > left' + 1 && data[mid] > query
                left = mid;
                //left'' == mid && right'' = right
                //data[left''] > query &&  left'' <= result <= right''
                //invariant && before
            else
                //invariant && before && right' > left' + 1 && data[mid] <=query
                //data[mid] <= query && left <= result <= mid
                right = mid;
                //right'' == mid && left'' == left
                //data[right''] <= query & left'' <= result <= right''
                //invariant and before
        }
        //before && invariant && (left' + 1 == right')
        //result == right' &&  ((data[right'] - most close over query || data[right'] == query) || (right == -1 && data[0] < query) || (right == data length && data[len] > query))
        if (right != data.length && data[right] == query) {
            //data[right'] == query && result == right'
            return right;
        } else {
            //data[right'] most close over query || data[0] < query || data[len] > query
            return (-right - 1);
        }
    }
    //after: (data[i]' == data[i]) && (result == 0 && data length == 0 ) || (data[len] > query && result == -(data length - 1)-1) || (result < data len && data[result] <= query && (result == 0  || data[result - 1] > query))

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("usage: BinarySearchMissing <query> [...]");
            return;
        }

        try {
            int query = Integer.parseInt(args[0]);
            int[] data = new int[args.length - 1];

            for (int i = 0; i < data.length; i++) {
                data[i] = Integer.parseInt(args[i + 1]);
            }

            int recursiveResult = recursiveMissingSearch(data, query, -1, data.length);
            int iterativeResult = iterativeMissingSearch(data, query);

            assert(recursiveResult == iterativeResult);

            System.out.println(iterativeResult);
        } catch (NumberFormatException e) {
            System.err.println("Invalid format of input numbers");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
