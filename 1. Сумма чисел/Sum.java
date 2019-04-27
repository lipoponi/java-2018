public class Sum {
    public static void main(String[] args) {
        String joint = String.join(" ", args);
        
        int ans = 0;
        String buffer = "";

        for (int i = 0; i < joint.length(); i++) {
            if (!Character.isWhitespace(joint.charAt(i))) {
                buffer += joint.charAt(i);
            } else {
                if (!buffer.isEmpty()) {
                    ans += Integer.parseInt(buffer);
                    buffer = "";
                }
            }
        }

        if (!buffer.isEmpty()) ans += Integer.parseInt(buffer);

        System.out.println(ans);
    }
}
