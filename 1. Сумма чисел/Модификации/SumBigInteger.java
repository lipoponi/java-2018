import java.math.BigInteger;
import java.util.Scanner;

public class SumBigInteger {
    public static void main(String[] args) {
        String joint = String.join(" ", args);
        Scanner scan = new Scanner(joint);
        BigInteger ans = new BigInteger("0");

        while (scan.hasNextBigInteger()) {
            ans = ans.add(scan.nextBigInteger());
        }

        scan.close();
        System.out.println(ans);
    }
}
