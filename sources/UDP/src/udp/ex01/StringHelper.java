package udp.ex01;

public class StringHelper {
    public static String toUpperCase(String str) {
        return str.toUpperCase();
    }

    public static String toLowerCase(String str) {
        return str.toLowerCase();
    }

    public static int countWordNum(String str) {
        char array[] = str.toCharArray();
        String word = "";
        int count = 0;

        for (char c : array) {
            if (c != ' ') {
                word += c;
            } else if (word != "") {
                ++count;
                word = "";
            }
        }

        if (word != "") {
            ++count;
        }

        return count;
    }
}
